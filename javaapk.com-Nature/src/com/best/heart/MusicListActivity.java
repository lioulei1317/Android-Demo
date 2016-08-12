package com.best.heart;

import java.util.List;
import com.best.heart.service.NatureService;
import com.best.heart.service.NatureService.NatureBinder;
import com.best.heart.tool.FormatHelper;
import com.best.heart.tool.MusicLoader;
import com.best.heart.tool.MusicLoader.MusicInfo;
import com.best.heart.tool.TvOff;
import com.example.nature.R;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


public class MusicListActivity extends Activity implements OnClickListener{

	public static final String TAG = "com.example.nature.MAIN_ACTIVITY";
	
	private ListView lvSongs;	
	private SeekBar pbDuration;
	private TextView tvCurrentMusic;
	private ImageView pic;
	private List<MusicInfo> musicList;
	private int currentMusic; // 正在播放的音乐
	private int currentPosition; //正在播放的音乐的位置
	private int currentMax;
		
	private Button btnStartStop;
	private Button btnNext;
	private Button btnDetail;
	
	private ProgressReceiver progressReceiver;	
	private NatureService.NatureBinder natureBinder;	
	
	private ServiceConnection serviceConnection = new ServiceConnection() {
		@Override
		public void onServiceDisconnected(ComponentName name) {
		}
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			natureBinder = (NatureBinder) service;
		}
	};
	private void connectToNatureService(){		
		Intent intent = new Intent(MusicListActivity.this, NatureService.class);	
		bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);	
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);	
		pic = (ImageView) findViewById(R.id.pic);
		MusicLoader musicLoader = MusicLoader.instance(getContentResolver());		
		musicList = musicLoader.getMusicList();
		connectToNatureService();
		initComponents();		
	}
	
	public void onResume(){
		super.onResume();	
		registerReceiver();
		if(natureBinder != null){
			if(natureBinder.isPlaying()){
				btnStartStop.setBackgroundResource(R.drawable.pause);
			}else{
				btnStartStop.setBackgroundResource(R.drawable.play);
			}
			natureBinder.notifyActivity();
		}
	}
	@Override
	protected void onRestart() {
		super.onRestart();
		pic.setImageResource(R.drawable.post);
	}
	public void onPause(){
		super.onPause();
		unregisterReceiver(progressReceiver);
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
	public void back(View v){
		finish();
	}
	private void initComponents(){		
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
		
		tvCurrentMusic = (TextView) findViewById(R.id.tvCurrentMusic);				
		
		btnStartStop = (Button)findViewById(R.id.btnStartStop);
		btnStartStop.setOnClickListener(this);
		
		btnNext = (Button)findViewById(R.id.btnNext);
		btnNext.setOnClickListener(this);
		
		btnDetail = (Button) findViewById(R.id.btnDetail);
		btnDetail.setOnClickListener(this);
		
		MusicAdapter adapter = new MusicAdapter();
		
		lvSongs = (ListView) findViewById(R.id.lvSongs);	
		lvSongs.setAdapter(adapter);
		Log.i("LOG", "lvSongs...."+lvSongs.getCount());
		lvSongs.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.i("LOG", "进入点击事件");
				currentMusic = position;
				natureBinder.startPlay(currentMusic,0);
				if(natureBinder.isPlaying()){					
					btnStartStop.setBackgroundResource(R.drawable.pause);		
				}
			}
		});
		Log.i("LOG", "完成点击事件");
	}
	
	private void registerReceiver(){
		progressReceiver = new ProgressReceiver();	
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(NatureService.ACTION_UPDATE_PROGRESS);
		intentFilter.addAction(NatureService.ACTION_UPDATE_DURATION);
		intentFilter.addAction(NatureService.ACTION_UPDATE_CURRENT_MUSIC);
		registerReceiver(progressReceiver, intentFilter);
	}
	
	class MusicAdapter extends BaseAdapter{
		@Override 
		public int getCount() {
			return musicList.size();
		}
		@Override
		public Object getItem(int position) {
			return musicList.get(position);
		}
		@Override
		public long getItemId(int position) {
			return musicList.get(position).getId();
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder; 
			if(convertView == null){
				convertView = LayoutInflater.from(MusicListActivity.this).inflate(R.layout.music_item, null);
				ImageView pImageView = (ImageView) convertView.findViewById(R.id.albumPhoto);
				TextView pTitle = (TextView) convertView.findViewById(R.id.title);
				TextView pDuration = (TextView) convertView.findViewById(R.id.duration);
				TextView pArtist = (TextView) convertView.findViewById(R.id.artist);
				viewHolder = new ViewHolder(pImageView, pTitle, pDuration, pArtist);
				convertView.setTag(viewHolder);
			}else{
				viewHolder = (ViewHolder) convertView.getTag();
			}
			
			viewHolder.imageView.setImageResource(R.drawable.music_icon);
			viewHolder.title.setText(musicList.get(position).getTitle());
			viewHolder.duration.setText(FormatHelper.formatDuration(musicList.get(position).getDuration()));
			viewHolder.artist.setText(musicList.get(position).getArtist());
			
			return convertView;
		}		
	}
	
	class ViewHolder{
		public ViewHolder(ImageView pImageView, TextView pTitle, TextView pDuration, TextView pArtist){
			imageView = pImageView;
			title = pTitle;
			duration = pDuration;
			artist = pArtist;
		}
		
		ImageView imageView;
		TextView title;
		TextView duration;
		TextView artist;
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
		case R.id.btnDetail:						
			Intent intent = new Intent(MusicListActivity.this,DetailActivity.class);
			intent.putExtra(DetailActivity.MUSIC_LENGTH, currentMax);
			intent.putExtra(DetailActivity.CURRENT_MUSIC, currentMusic);
			intent.putExtra(DetailActivity.CURRENT_POSITION, currentPosition);			
			startActivity(intent);
			if(NatureService.mediaPlayer.isPlaying()){
				
			}
			//pic.startAnimation(new TvOff()); 
			break;
		}		
	}
	
	private void play(int position, int resId){		
		if(natureBinder.isPlaying()){
			natureBinder.stopPlay();
			btnStartStop.setBackgroundResource(R.drawable.play);
		}else{
			natureBinder.startPlay(position,currentPosition);
			btnStartStop.setBackgroundResource(R.drawable.pause);
		}
	}

	

	class ProgressReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if(NatureService.ACTION_UPDATE_PROGRESS.equals(action)){
				int progress = intent.getIntExtra(NatureService.ACTION_UPDATE_PROGRESS, 0);
				if(progress > 0){
					currentPosition = progress; // Remember the current position
					pbDuration.setProgress(progress / 1000);
				}
			}else if(NatureService.ACTION_UPDATE_CURRENT_MUSIC.equals(action)){
				//Retrive the current music and get the title to show on top of the screen.
				currentMusic = intent.getIntExtra(NatureService.ACTION_UPDATE_CURRENT_MUSIC, 0);				
				tvCurrentMusic.setText(musicList.get(currentMusic).getTitle());
			}else if(NatureService.ACTION_UPDATE_DURATION.equals(action)){
				//Receive the duration and show under the progress bar
				//Why do this ? because from the ContentResolver, the duration is zero.
				currentMax = intent.getIntExtra(NatureService.ACTION_UPDATE_DURATION, 0);				
				int max = currentMax / 1000;
				pbDuration.setMax(currentMax / 1000);						
			}
		}
		
	}
	
}
