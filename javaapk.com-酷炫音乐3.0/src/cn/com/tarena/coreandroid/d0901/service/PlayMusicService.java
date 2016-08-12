package cn.com.tarena.coreandroid.d0901.service;

import java.io.IOException;
import java.util.ArrayList;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import cn.com.tarena.coreandroid.d0901.R;
import cn.com.tarena.coreandroid.d0901.application.PlayMusicApplication;
import cn.com.tarena.coreandroid.d0901.entity.Music;
import cn.com.tarena.coreandroid.d0901.myinterface.IMusicPlay;
import cn.com.tarena.coreandroid.d0901.utils.ApplicationConsts;
import cn.com.tarena.coreandroid.d0901.utils.TextFormatter;
import cn.com.tarena.coreandroid.d0901.widget.PlayMusicWidget;

public class PlayMusicService extends Service implements ApplicationConsts {

	private IBinder binder;
	/**
	 * 播放器
	 */
	private MediaPlayer player;
	/**
	 * Application对象
	 */
	private PlayMusicApplication app;
	/**
	 * 歌曲的List集合
	 */
	private ArrayList<Music> musics;
	/**
	 * 是否正在播放
	 */
	private boolean isPlaying;
	/**
	 * 暂停时的进度
	 */
	private int currentPosition;
	/**
	 * 是否由用户操作开始播放歌曲
	 */
	private boolean isStarted = false;
	/**
	 * 线程是否工作
	 */
	private boolean isThreadWorking;
	/**
	 * 更新Widget的子线程
	 */
	private UpdateWidgetThread widgetThread;
	/**
	 * 广播接收者
	 */
	private BroadcastReceiver receiver;
	/**
	 * 广播接收者的意图过滤器
	 */
	private IntentFilter filter;
	AppWidgetManager manager;
	ComponentName provider;
	RemoteViews views;

	@Override
	public void onCreate() {
		// 获取Application对象
		Log.d("", "PlayMusicService.onCreate()");
		app = (PlayMusicApplication) getApplication();
		// 获取歌曲的List集合
		musics = app.getMusicList();

		manager = AppWidgetManager.getInstance(PlayMusicService.this);
		provider = new ComponentName(PlayMusicService.this,
				PlayMusicWidget.class);

		views = new RemoteViews(getPackageName(), R.layout.play_music_widget);
		// 初始化播放器
		player = new MediaPlayer();
		player.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				if (isStarted) {
					// 播放下一首

					Log.i("onCompletion", mp.getDuration() + "getDuration");
					Log.i("onCompletion", mp.getCurrentPosition()
							+ "getCurrentPosition");
					Log.i("onCompletion", app.getCurrentMusicIndex() + "");
					nextMusic();
				}

			}
		});
		// 注册广播接收者
		initReceiver();
		// 设置线程启动标示
		isThreadWorking = true;
		// 启动更新桌面小部件线程
		widgetThread = new UpdateWidgetThread();
		widgetThread.start();
		super.onCreate();
	}

	@Override
	public IBinder onBind(Intent intent) {
		binder = new PlayMusicBinder();
		return binder;
	}

	/**
	 * 播放
	 */
	private void playMusic() {
		try {
			if (player.isPlaying()) {
				player.stop(); // 停止当前音频的播放
			}
			player.reset();
			
			player.setDataSource(musics.get(app.getCurrentMusicIndex())
					.getData());
			Log.i("", "getCurrentMusicIndex==" + app.getCurrentMusicIndex());
			Log.i("music---", musics.get(app.getCurrentMusicIndex()).getData()
					.toString());
			player.prepare();
			player.seekTo(currentPosition);
			player.start();
app.setAudioSessionId(player.getAudioSessionId());
			// 清空变量中记录的当前播放进度
			currentPosition = 0;
			// 更新播放状态
			isPlaying = true;
			// 更新状态为：用户操作后开始播放歌曲
			isStarted = true;
			// 发送广播：正在播放歌曲
			Intent intent = new Intent();
			intent.setAction(SERVICE_PLAYER_PLAY);
			sendBroadcast(intent);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 暂停
	 */
	private void pauseMusic() {
		Log.i("", "pauseMusic==" + app.getCurrentMusicIndex());
		if (isPlaying) {
			// 暂停
			player.pause();
			// 记录暂停的位置
			currentPosition = player.getCurrentPosition();
			// 更新播放状态
			isPlaying = false;

		}
	}

	/**
	 * 上一首
	 */
	private void previousMusic() {
		Log.i("", "previousMusic==" + app.getCurrentMusicIndex());
		int index = app.getCurrentMusicIndex();
		index--;
		if (index < 0) {
			index = musics.size() - 1;
		}
		app.setCurrentMusicIndex(index);
		playMusic();
	}

	/**
	 * 下一首
	 */
	private void nextMusic() {
		Log.i("", "nextMusic==" + app.getCurrentMusicIndex());
		int index = app.getCurrentMusicIndex();
		index++;
		if (index >= musics.size()) {
			index = 0;
		}
		app.setCurrentMusicIndex(index);
		playMusic();

	}

	public class PlayMusicBinder extends Binder implements IMusicPlay {
		@Override
		public void play() {

			Log.i("", "PlayMusicBinder---play()");
			playMusic();
		}

		@Override
		public void stop() {
			Log.i("", "PlayMusicBinder---stop()");
			pauseMusic();
		}

		@Override
		public int getProgress() {

			return player.getCurrentPosition();
		}

		@Override
		public void playSeekTo(int progress) {
			Log.i("", "playSeekTo---()");
			currentPosition = (int) (musics.get(app.getCurrentMusicIndex())
					.getDuration() * progress / 100);
			playMusic();
		}

		@Override
		public void playNext() {
			nextMusic();

		}

		@Override
		public void playPrevious() {
			previousMusic();

		}

		@Override
		public int getAudioSessionId() {

			return player.getAudioSessionId();
		}

	}

	/**
	 * 初始化并注册广播接收者
	 */
	private void initReceiver() {
		// 初始化广播接收者
		receiver = new InnerReceiver();
		// 初始化意图过滤器
		filter = new IntentFilter();
		// 添加接收的广播类型
		filter.addAction(ACTIVITY_PLAY_BUTTON_CLICK);
		filter.addAction(ACTIVITY_PREVIOUS_BUTTON_CLICK);
		filter.addAction(ACTIVITY_NEXT_BUTTON_CLICK);
		filter.addAction(ACTIVITY_START_MAIN);
		// 注册广播接收者
		registerReceiver(receiver, filter);
	}

	/**
	 * 广播接收者
	 * 
	 * @author tarena
	 * 
	 */
	private class InnerReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			// 当播放按钮被点击时

			if (ACTIVITY_PLAY_BUTTON_CLICK.equals(action)) {

				if (isPlaying) {
					pauseMusic();
					views.setImageViewResource(R.id.ib_widget_play,
							R.drawable.button_play);
				} else {
					playMusic();

					views.setImageViewResource(R.id.ib_widget_play,
							R.drawable.button_pause);
				}
			} else if (ACTIVITY_PREVIOUS_BUTTON_CLICK.equals(action)) {
				previousMusic();
			} else if (ACTIVITY_NEXT_BUTTON_CLICK.equals(action)) {
				nextMusic();
			}
			// 更新
			manager.updateAppWidget(provider, views);
		}
	}

	/**
	 * 更新桌面小部件
	 * 
	 * @author Tarena
	 * 
	 */
	private class UpdateWidgetThread extends Thread {
		@Override
		public void run() {
			Music music;
			int progress;
			while (isThreadWorking) {
				// 获取歌曲对象
				music = app.getMusicList().get(app.getCurrentMusicIndex());
				views.setTextViewText(R.id.tv_widget_title, music.getTitle());
				views.setTextViewText(R.id.tv_widget_album, music.getAlbum());
				views.setTextViewText(R.id.tv_widget_artist, music.getArtist());
				views.setTextViewText(R.id.tv_widget_position,
						TextFormatter.getMusicTime(player.getCurrentPosition()));
				views.setTextViewText(R.id.tv_widget_duration,
						TextFormatter.getMusicTime(music.getDuration()));
				// 设置ImageView

				// 计算并设置进度条
				progress = (int) (player.getCurrentPosition() * 100 / music
						.getDuration());
				progress = progress > 100 ? 0 : progress;
				views.setProgressBar(R.id.sb_widget_progress, 100, progress,
						false);
				// 更新
				manager.updateAppWidget(provider, views);

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			super.run();
		}

	}

	@Override
	public void onDestroy() {
		// 停止线程
		isThreadWorking = false;
		// 停止接收广播
		unregisterReceiver(receiver);
		if (player != null) {
			// 释放播放器资源

			if (player.isPlaying()) {
				player.stop(); // 停止音乐的播放
			}
			player.release();
			player = null;
		}
		super.onDestroy();
	}
}
