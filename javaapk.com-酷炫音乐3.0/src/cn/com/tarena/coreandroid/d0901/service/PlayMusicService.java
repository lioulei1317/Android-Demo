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
	 * ������
	 */
	private MediaPlayer player;
	/**
	 * Application����
	 */
	private PlayMusicApplication app;
	/**
	 * ������List����
	 */
	private ArrayList<Music> musics;
	/**
	 * �Ƿ����ڲ���
	 */
	private boolean isPlaying;
	/**
	 * ��ͣʱ�Ľ���
	 */
	private int currentPosition;
	/**
	 * �Ƿ����û�������ʼ���Ÿ���
	 */
	private boolean isStarted = false;
	/**
	 * �߳��Ƿ���
	 */
	private boolean isThreadWorking;
	/**
	 * ����Widget�����߳�
	 */
	private UpdateWidgetThread widgetThread;
	/**
	 * �㲥������
	 */
	private BroadcastReceiver receiver;
	/**
	 * �㲥�����ߵ���ͼ������
	 */
	private IntentFilter filter;
	AppWidgetManager manager;
	ComponentName provider;
	RemoteViews views;

	@Override
	public void onCreate() {
		// ��ȡApplication����
		Log.d("", "PlayMusicService.onCreate()");
		app = (PlayMusicApplication) getApplication();
		// ��ȡ������List����
		musics = app.getMusicList();

		manager = AppWidgetManager.getInstance(PlayMusicService.this);
		provider = new ComponentName(PlayMusicService.this,
				PlayMusicWidget.class);

		views = new RemoteViews(getPackageName(), R.layout.play_music_widget);
		// ��ʼ��������
		player = new MediaPlayer();
		player.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				if (isStarted) {
					// ������һ��

					Log.i("onCompletion", mp.getDuration() + "getDuration");
					Log.i("onCompletion", mp.getCurrentPosition()
							+ "getCurrentPosition");
					Log.i("onCompletion", app.getCurrentMusicIndex() + "");
					nextMusic();
				}

			}
		});
		// ע��㲥������
		initReceiver();
		// �����߳�������ʾ
		isThreadWorking = true;
		// ������������С�����߳�
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
	 * ����
	 */
	private void playMusic() {
		try {
			if (player.isPlaying()) {
				player.stop(); // ֹͣ��ǰ��Ƶ�Ĳ���
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
			// ��ձ����м�¼�ĵ�ǰ���Ž���
			currentPosition = 0;
			// ���²���״̬
			isPlaying = true;
			// ����״̬Ϊ���û�������ʼ���Ÿ���
			isStarted = true;
			// ���͹㲥�����ڲ��Ÿ���
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
	 * ��ͣ
	 */
	private void pauseMusic() {
		Log.i("", "pauseMusic==" + app.getCurrentMusicIndex());
		if (isPlaying) {
			// ��ͣ
			player.pause();
			// ��¼��ͣ��λ��
			currentPosition = player.getCurrentPosition();
			// ���²���״̬
			isPlaying = false;

		}
	}

	/**
	 * ��һ��
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
	 * ��һ��
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
	 * ��ʼ����ע��㲥������
	 */
	private void initReceiver() {
		// ��ʼ���㲥������
		receiver = new InnerReceiver();
		// ��ʼ����ͼ������
		filter = new IntentFilter();
		// ��ӽ��յĹ㲥����
		filter.addAction(ACTIVITY_PLAY_BUTTON_CLICK);
		filter.addAction(ACTIVITY_PREVIOUS_BUTTON_CLICK);
		filter.addAction(ACTIVITY_NEXT_BUTTON_CLICK);
		filter.addAction(ACTIVITY_START_MAIN);
		// ע��㲥������
		registerReceiver(receiver, filter);
	}

	/**
	 * �㲥������
	 * 
	 * @author tarena
	 * 
	 */
	private class InnerReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			// �����Ű�ť�����ʱ

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
			// ����
			manager.updateAppWidget(provider, views);
		}
	}

	/**
	 * ��������С����
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
				// ��ȡ��������
				music = app.getMusicList().get(app.getCurrentMusicIndex());
				views.setTextViewText(R.id.tv_widget_title, music.getTitle());
				views.setTextViewText(R.id.tv_widget_album, music.getAlbum());
				views.setTextViewText(R.id.tv_widget_artist, music.getArtist());
				views.setTextViewText(R.id.tv_widget_position,
						TextFormatter.getMusicTime(player.getCurrentPosition()));
				views.setTextViewText(R.id.tv_widget_duration,
						TextFormatter.getMusicTime(music.getDuration()));
				// ����ImageView

				// ���㲢���ý�����
				progress = (int) (player.getCurrentPosition() * 100 / music
						.getDuration());
				progress = progress > 100 ? 0 : progress;
				views.setProgressBar(R.id.sb_widget_progress, 100, progress,
						false);
				// ����
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
		// ֹͣ�߳�
		isThreadWorking = false;
		// ֹͣ���չ㲥
		unregisterReceiver(receiver);
		if (player != null) {
			// �ͷŲ�������Դ

			if (player.isPlaying()) {
				player.stop(); // ֹͣ���ֵĲ���
			}
			player.release();
			player = null;
		}
		super.onDestroy();
	}
}
