package com.best.heart.service;

import java.util.List;

import com.best.heart.MusicListActivity;
import com.best.heart.tool.MusicLoader;
import com.best.heart.tool.MusicLoader.MusicInfo;
import com.example.nature.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

public class NatureService extends Service{

	public static final String MUSICS = "com.example.nature.MUSIC_LIST";
	public static final String NATURE_SERVICE = "com.example.nature.NatureService";	
	public static  MediaPlayer mediaPlayer;
	private boolean isPlaying = false;
	private List<MusicInfo> musicList;
	private Binder natureBinder = new NatureBinder();
	private int currentMusic;
	private int currentPosition;
	private static final int updateProgress = 1;
	private static final int updateCurrentMusic = 2;
	private static final int updateDuration = 3;
	public static final String ACTION_UPDATE_PROGRESS = "com.example.nature.UPDATE_PROGRESS";
	public static final String ACTION_UPDATE_DURATION = "com.example.nature.UPDATE_DURATION";
	public static final String ACTION_UPDATE_CURRENT_MUSIC = "com.example.nature.UPDATE_CURRENT_MUSIC";
	private int currentMode = 3; //默认顺序播放
	public static final String[] MODE_DESC = {"Single Loop", "List Loop", "Random", "Sequence"};
	public static final int MODE_ONE_LOOP = 0;
	public static final int MODE_ALL_LOOP = 1;
	public static final int MODE_RANDOM = 2;
	public static final int MODE_SEQUENCE = 3; 
	private Notification notification; 
	//接受子线程发送的数据, 并用此数据配合主线程更新UI
	private Handler handler = new Handler(){
		//接受子线程传过来的(子线程用sedMessage()方法传递)Message对象，(里面包含数据) 
		// 把这些消息放入主线程队列中，配合主线程进行更新UI
		public void handleMessage(Message msg){
			switch(msg.what){
			case updateProgress:				
				toUpdateProgress();
				break;
			case updateDuration:				
				toUpdateDuration();
				break;
			case updateCurrentMusic:
				toUpdateCurrentMusic();
				break;
			}
		}
	};
	
	private void toUpdateProgress(){
		if(mediaPlayer != null && isPlaying){					
			int progress = mediaPlayer.getCurrentPosition();					
			Intent intent = new Intent();
			intent.setAction(ACTION_UPDATE_PROGRESS);
			intent.putExtra(ACTION_UPDATE_PROGRESS,progress);
			sendBroadcast(intent);
			handler.sendEmptyMessageDelayed(updateProgress, 1000);	//设置延迟					
		}
	}
	
	private void toUpdateDuration(){
		if(mediaPlayer != null){					
			int duration = mediaPlayer.getDuration();					
			Intent intent = new Intent();
			intent.setAction(ACTION_UPDATE_DURATION);
			intent.putExtra(ACTION_UPDATE_DURATION,duration);
			sendBroadcast(intent);									
		}
	}
	
	private void toUpdateCurrentMusic(){
		Intent intent = new Intent();
		intent.setAction(ACTION_UPDATE_CURRENT_MUSIC);
		intent.putExtra(ACTION_UPDATE_CURRENT_MUSIC,currentMusic);
		sendBroadcast(intent);				
	}
	
	@SuppressWarnings({ "static-access", "deprecation" })
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	public void onCreate(){
		initMediaPlayer();
		musicList = MusicLoader.instance(getContentResolver()).getMusicList();
		
		super.onCreate();
		
		Intent intent = new Intent(this, MusicListActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		//外部执行 pendingintent时，调用intent的，也就是在通知栏到MainActivity
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
		notification = new Notification.Builder(this)
					.setTicker("Heart")//
					.setSmallIcon(R.drawable.iuni)//设置通知小ICON  
					.setContentTitle("Playing")//设置通知栏标题  
					.setContentText(musicList.get(currentMusic).getTitle())//设置通知栏显示内容
					.setContentIntent(pendingIntent)// //设置通知栏点击意图 
					.setDefaults(Notification.DEFAULT_ALL)////向通知添加声音,振动和三色灯提醒
					.setVibrate(new long[]{0,300,500,700})//延迟0ms，然后振动300ms，在延迟500ms，接着在振动700ms。
					.setWhen(System.currentTimeMillis())//通知产生的时间
					.setPriority(Notification.PRIORITY_DEFAULT) //设置该通知优先级 
					.getNotification();	
		notification.flags |= Notification.FLAG_NO_CLEAR;
		/*
		 * 使用android 4.1以上系统的时候，发现在手机休眠一段时间后（1-2小时），
		 * 后台运行的服务被强行kill掉，有可能是系统回收内存的一种机制，
		 * 要想避免这种情况可以通过startForeground让服务前台运行，
		 * 当stopservice的时候通过stopForeground去掉。
		 * 只需要在onStartCommand里面调用 startForeground，
		 * 然后再onDestroy里面调用stopForeground即可！  
		 * 不管手机如何休眠，只要开始播放音乐了，就不会kill掉这个服务，
		 * 一旦停止播放音乐，服务就可能被清掉。
		 * */
		startForeground(1, notification);
		
	}	
	
	public void onDestroy(){
		if(mediaPlayer != null){
			mediaPlayer.release();
			mediaPlayer = null;
		}
	}	
	
	/**
	 * 初始化 MediaPlayer
	 */
	
	private void initMediaPlayer(){
		mediaPlayer = new MediaPlayer();
		//指定流媒体的类型,不能再onCreate中设置
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		//监听一个回调函数去执行音乐已经准备好并且可以开始时调用
		mediaPlayer.setOnPreparedListener(new OnPreparedListener() {				
			@Override
			public void onPrepared(MediaPlayer mp) {				
				mediaPlayer.start();
				mediaPlayer.seekTo(currentPosition);
				handler.sendEmptyMessage(updateDuration);
			}
		});
		//监听播放完成的事件。
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {			
			@Override
			public void onCompletion(MediaPlayer mp) {
				if(isPlaying){
					switch (currentMode) {
					case MODE_ONE_LOOP:
						mediaPlayer.start();
						break;					
					case MODE_ALL_LOOP:
						play((currentMusic + 1) % musicList.size(), 0);
						break;
					case MODE_RANDOM:
						play(getRandomPosition(), 0);
						break;
					case MODE_SEQUENCE:
						if(currentMusic < musicList.size() - 1){						
							playNext();
						}
						break;
					default:
						break;
					}
				}
			}
		});
	}
	
	private void setCurrentMusic(int pCurrentMusic){
		currentMusic = pCurrentMusic;
		handler.sendEmptyMessage(updateCurrentMusic);
	}
	
	private int getRandomPosition(){
		int random = (int)(Math.random() * (musicList.size() - 1));
		return random;
	}
	
	private void play(int currentMusic, int pCurrentPosition) {
		currentPosition = pCurrentPosition;
		setCurrentMusic(currentMusic);
		mediaPlayer.reset();
		try {
			mediaPlayer.setDataSource(musicList.get(currentMusic).getUrl());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//开始准备
		//在OnPreparedListener的onPrepared的方法调用start()。
		mediaPlayer.prepareAsync();
		handler.sendEmptyMessage(updateProgress);

		isPlaying = true;
	}
	
	private void stop(){
		mediaPlayer.stop();
		isPlaying = false;
		
	}
	
	private void playNext(){
		switch(currentMode){
		case MODE_ONE_LOOP:
			play(currentMusic, 0);
			break;
		case MODE_ALL_LOOP:
			if(currentMusic + 1 == musicList.size()){
				play(0,0);
			}else{
				play(currentMusic + 1, 0);
			}
			break;
		case MODE_SEQUENCE:
			if(currentMusic + 1 == musicList.size()){
				Toast.makeText(this, "No more song.", Toast.LENGTH_SHORT).show();
			}else{
				play(currentMusic + 1, 0);
			}
			break;
		case MODE_RANDOM:
			play(getRandomPosition(), 0);
			break;
		}
	}
	
	private void playPrevious(){		
		switch(currentMode){
		case MODE_ONE_LOOP:
			play(currentMusic, 0);
			break;
		case MODE_ALL_LOOP:
			if(currentMusic - 1 < 0){
				play(musicList.size() - 1, 0);
			}else{
				play(currentMusic - 1, 0);
			}
			break;
		case MODE_SEQUENCE:
			if(currentMusic - 1 < 0){
				Toast.makeText(this, "No previous song.", Toast.LENGTH_SHORT).show();
			}else{
				play(currentMusic - 1, 0);
			}
			break;
		case MODE_RANDOM:
			play(getRandomPosition(), 0);
			break;
		}
	}
	

	@Override	
	public IBinder onBind(Intent intent) {		
		return natureBinder;
	}	
	
	public class NatureBinder extends Binder{
		
		public void startPlay(int currentMusic, int currentPosition){
			play(currentMusic,currentPosition);
		}
		
		public void stopPlay(){
			stop();
		}
		
		public void toNext(){
			//在这里应该把播放按钮设置成||
			//DetailActivity.btnStartStop.performClick();
			playNext();
		}
		
		public void toPrevious(){
			playPrevious();
		}
		
		/**
		 * MODE_ONE_LOOP = 1;
		 * MODE_ALL_LOOP = 2;
		 * MODE_RANDOM = 3;
		 * MODE_SEQUENCE = 4; 
		 */		
		public void changeMode(){			
			currentMode = (currentMode + 1) % 4;
			Toast.makeText(NatureService.this, MODE_DESC[currentMode], Toast.LENGTH_SHORT).show();
		}
		
		/**
		 * 返回当前模式
		 * MODE_ONE_LOOP = 1;
		 * MODE_ALL_LOOP = 2;
		 * MODE_RANDOM = 3;
		 * MODE_SEQUENCE = 4; 
		 */
		public int getCurrentMode(){
			return currentMode; 
		}
		
		/**
		 * 这个服务用来播放音乐
		 */
		public boolean isPlaying(){
			return isPlaying;
		}
		
		/**
		 * 通知Activities更新当前的音乐和时间的变化。
		 */
		public void notifyActivity(){
			toUpdateCurrentMusic();
			toUpdateDuration();			
		}
		
		/**
		 * 进度条改变时
		 */
		//进度调整后的播放
		public void changeProgress(int progress){
			if(mediaPlayer != null){
				currentPosition = progress * 1000;
				if(isPlaying){
					mediaPlayer.seekTo(currentPosition);
				}else{
					play(currentMusic, currentPosition);
				}
			}
		}
	}

}