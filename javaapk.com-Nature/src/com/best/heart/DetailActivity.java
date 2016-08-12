package com.best.heart;

import java.util.List;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.Window;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.best.heart.service.NatureService;
import com.best.heart.service.NatureService.NatureBinder;
import com.best.heart.tool.CustomAudioIcon;
import com.best.heart.tool.FormatHelper;
import com.best.heart.tool.MusicLoader;
import com.best.heart.tool.MusicLoader.MusicInfo;
import com.example.nature.R;

public class DetailActivity extends Activity implements OnClickListener{
	
	public static final String MUSIC_LENGTH = "com.example.nature.DetailActivity.MUSIC_LENGTH";
	public static final String CURRENT_POSITION = "com.example.nature.DetailActivity.CURRENT_POSITION";
	public static final String CURRENT_MUSIC = "com.example.nature.DetailActivity.CURRENT_MUSIC";
	private SeekBar pbDuration;
	private TextView tvTitle,tvTimeElapsed, tvDuration,tvsinger;
	public static ImageView infoOperatingIV;
	public static CustomAudioIcon btnStartStop;
	private List<MusicInfo> musicList;
	private int currentMusic;
	private int currentPosition;
	private ProgressReceiver progressReceiver;	
	private NatureBinder natureBinder;
	private int[] btnResIds = new int[] {
			R.id.btnMode,
			R.id.btnPrevious, 
			R.id.btnStartStop, 			
			R.id.btnNext,
			R.id.btnExit 
	};
	
	private ServiceConnection serviceConnection = new ServiceConnection() {
		@Override
		public void onServiceDisconnected(ComponentName name) {
		}
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			natureBinder = (NatureBinder) service;	
			if(natureBinder.isPlaying()){
				CustomAudioIcon btnStartStop = (CustomAudioIcon)findViewById(R.id.btnStartStop);
				btnStartStop.setFlagStart(false);		
			}
			CustomAudioIcon btnMode = (CustomAudioIcon)findViewById(R.id.btnMode);
			btnMode.setCurrentMode(natureBinder.getCurrentMode());
		}
	};
	
	private void connectToNatureService(){		
		Intent intent = new Intent(DetailActivity.this, NatureService.class);				
		bindService(intent, serviceConnection, BIND_AUTO_CREATE);				
	}
	@Override	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.push_right_in,R.anim.hold);
		MusicLoader musicLoader = MusicLoader.instance(getContentResolver());		
		musicList = musicLoader.getMusicList();	
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.detail_layout);	
		
		infoOperatingIV = (ImageView) findViewById(R.id.infoOperating);

		connectToNatureService();
		initComponents();	
	}
	@Override
	public void onResume(){
		super.onResume();				
		initReceiver();		
	}
	@Override
	public void onPause(){
		super.onPause();		
		unregisterReceiver(progressReceiver);
		overridePendingTransition(R.anim.hold, R.anim.push_right_out);
	}
	public void onStop(){
		super.onStop();		
	}
	public void onDestroy(){
		super.onDestroy();
		if(natureBinder != null){
			unbindService(serviceConnection);
		}
	}	
	private void initComponents(){		
		tvTitle = (TextView) findViewById(R.id.musicname);
		tvsinger = (TextView) findViewById(R.id.singer);
		currentMusic = getIntent().getIntExtra(CURRENT_MUSIC,0);
		tvTitle.setText(musicList.get(currentMusic).getTitle());	
		tvsinger.setText(musicList.get(currentMusic).getArtist());
		tvDuration = (TextView) findViewById(R.id.tvDuration);
		int max = getIntent().getIntExtra(MUSIC_LENGTH, 0);
		tvDuration.setText(FormatHelper.formatDuration(max));
		pbDuration = (SeekBar) findViewById(R.id.pbDuration);
		pbDuration.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {}
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {		
				if(fromUser){
					natureBinder.changeProgress(progress);
				}
			}
		});
		pbDuration.setMax(max/1000);
		currentPosition = getIntent().getIntExtra(CURRENT_POSITION,0);
		pbDuration.setProgress(currentPosition / 1000);
		tvTimeElapsed = (TextView) findViewById(R.id.tvTimeElapsed);
		tvTimeElapsed.setText(FormatHelper.formatDuration(currentPosition));
		for(int resId : btnResIds){
			CustomAudioIcon icon = (CustomAudioIcon)findViewById(resId);
			icon.setOnClickListener(this);
		}				
	}	
	
	private void initReceiver(){
		progressReceiver = new ProgressReceiver();	
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(NatureService.ACTION_UPDATE_PROGRESS);
		intentFilter.addAction(NatureService.ACTION_UPDATE_DURATION);
		intentFilter.addAction(NatureService.ACTION_UPDATE_CURRENT_MUSIC);
		registerReceiver(progressReceiver, intentFilter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {		
		case R.id.btnStartStop:		
			play(currentMusic,R.id.btnStartStop);
			break;		
		case R.id.btnNext:
			natureBinder.toNext();
			break;
		case R.id.btnPrevious:
			natureBinder.toPrevious();
			break;
		case R.id.btnExit:	
			finish();
			break;
		case R.id.btnMode:						
			natureBinder.changeMode();
			break;
		
		default:
			break;
		}			
	}
	public void back(View v){
		finish();
	}
	private void play(int currentMusic, int resId){
		Animation operatingAnim = AnimationUtils.loadAnimation(this, R.anim.tip);
		LinearInterpolator lin = new LinearInterpolator();
		operatingAnim.setInterpolator(lin);
		if (operatingAnim != null) {
			DetailActivity.infoOperatingIV.startAnimation(operatingAnim);
		}
		btnStartStop = (CustomAudioIcon) findViewById(resId);
		//如果显示启动三角形，返回true，否则返回false
		if(btnStartStop.isStartStatus() && NatureService.mediaPlayer.isPlaying()){
			natureBinder.stopPlay();
			DetailActivity.infoOperatingIV.clearAnimation();
		}else{
			natureBinder.startPlay(currentMusic,currentPosition);
		}
	}
	class ProgressReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if(NatureService.ACTION_UPDATE_PROGRESS.equals(action)){
				int progress = intent.getIntExtra(NatureService.ACTION_UPDATE_PROGRESS, currentPosition);				
				if(progress > 0){
					currentPosition = progress; //记住当前位置
					tvTimeElapsed.setText(FormatHelper.formatDuration(progress));
					pbDuration.setProgress(progress / 1000);
				}
			}else if(NatureService.ACTION_UPDATE_CURRENT_MUSIC.equals(action)){
				//检索当前的音乐，标题和歌手显示在屏幕顶部的。
				currentMusic = intent.getIntExtra(NatureService.ACTION_UPDATE_CURRENT_MUSIC, 0);					
				tvTitle.setText(musicList.get(currentMusic).getTitle());
				tvsinger.setText(musicList.get(currentMusic).getArtist());
			}else if(NatureService.ACTION_UPDATE_DURATION.equals(action)){
				//进度条在收到时间和显示
				//为什么要做这个？因为来自ContentResolver，持续时间为零。
				int duration = intent.getIntExtra(NatureService.ACTION_UPDATE_DURATION, 0);
				tvDuration.setText(FormatHelper.formatDuration(duration));
				pbDuration.setMax(duration / 1000);						
			}
		}
		
	}
}