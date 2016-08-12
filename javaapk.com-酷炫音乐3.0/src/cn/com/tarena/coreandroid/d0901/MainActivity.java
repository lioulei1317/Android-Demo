package cn.com.tarena.coreandroid.d0901;

import java.util.ArrayList;



import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import cn.com.tarena.coreandroid.d0901.adapter.MusicListAdapter;
import cn.com.tarena.coreandroid.d0901.application.PlayMusicApplication;
import cn.com.tarena.coreandroid.d0901.custom.VisualizerView;
import cn.com.tarena.coreandroid.d0901.entity.Music;
import cn.com.tarena.coreandroid.d0901.myinterface.IMusicPlay;
import cn.com.tarena.coreandroid.d0901.service.PlayMusicService;
import cn.com.tarena.coreandroid.d0901.utils.ApplicationConsts;
import cn.com.tarena.coreandroid.d0901.utils.TextFormatter;

public class MainActivity extends Activity implements ApplicationConsts {

	public static final int UPDATE_PROGRESS_OK = 32;

	/**
	 * �������ֽӿ�
	 */
	private IMusicPlay binder;
	/**
	 * ��service���Ӷ���
	 */
	private ServiceConnection conn;
	/**
	 * handlerCallback������Ϣ
	 */
	private Handler.Callback handlerCallback;
	/**
	 * �����߳̽���
	 */
	private Thread updateProgressThread;
	/**
	 * ��Ϣ����
	 */
	private Handler handler;
	/**
	 * ��ǰ����(����)
	 */
	private int currentProgress;
	/**
	 * �߳��Ƿ���
	 */
	private boolean isThreadWorking;
	/**
	 * Application����
	 */
	private PlayMusicApplication app;
	/**
	 * ��ʾ�ؼ�ListView
	 */
	private ListView listview;
	/**
	 * ������List����
	 */
	private ArrayList<Music> musics;
	/**
	 * ListView��������
	 */
	private MusicListAdapter adapter;
	/**
	 * ��ť������/��ͣ����һ�ף���һ��
	 */
	private Button ibPlay, ibPrevious, ibNext;
	/**
	 * ��ť�ĵ��������
	 */
	private ButtonClickListener buttonClickListener;
	/**
	 * ��ʾ������Ϣ��TextView
	 */
	private TextView tvTitle, tvArtist, tvAlbum, tvDuration;
	/**
	 * ListView�ļ�����
	 */
	private OnItemClickListener listItemClickListener;
	/**
	 * ������
	 */
	private SeekBar sbProgress;
	/**
	 * �������϶�������
	 */
	private OnSeekBarChangeListener seekBarChangeListener;
	/**
	 * �Ƿ����û�������ʼ���Ÿ���
	 */
	private boolean isStarted;
	/**
	 * �Ƿ����ڲ��Ÿ���
	 */
	private boolean isPlaying;
	/**
	 * �㲥������
	 */
	private BroadcastReceiver receiver;
	/**
	 * �㲥�����ߵ���ͼ������
	 */
	private IntentFilter filter;
	private int currentID = -1;
	private long exitTime = 0;
	// ViewPager
	private ViewPager viewpager;
	// �Ӽ�ҳ���View����
	private View[] childViews;
	// ViewPager��������
	private PagerAdapter pageradapter;
	private VisualizerView visualizer;// ��������ͼ
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		// ��ȡApplication����
		Log.d("", "MainActivity.onCreate()");
		app = (PlayMusicApplication) getApplication();
		// ����ĳ�ʼ��
		initViews();

		// ������ͼ���� ��ʽ����
		Intent intent = new Intent(this, PlayMusicService.class);
		// ��������
		conn = new InnerServiceConnection();
		// ��service
		bindService(intent, conn, BIND_AUTO_CREATE);
		// ��ʼ���㲥������
		initReceiver();
	}

	/**
	 * ��ʼ��
	 */
	private void initViews() {
		// ��ʼ��ViewPager
		viewpager = (ViewPager) findViewById(R.id.viewPager);
		// ��ʼ���Ӽ�ҳ��
		childViews = new View[2];
		childViews[0] = View.inflate(this, R.layout.listview_frarement, null);
		childViews[1] = View.inflate(this, R.layout.visualizer_frarement, null);
		// ��ʼ��ViewPager��adapter
		pageradapter = new InnerPagerAdapter();
		// ΪViewPager����adapter
		viewpager.setAdapter(pageradapter);
		
		
		visualizer=(VisualizerView)childViews[1].findViewById(R.id.activity_player_visualizer);
		// ��ʼ����Ϣ������
		
		handlerCallback = new HandlerCallBack();
		handler = new Handler(handlerCallback);
		// ��ʼ��ListView���
		listview = (ListView) childViews[0].findViewById(R.id.lv_music_list);
		musics = app.getMusicList();
		adapter = new MusicListAdapter(this, musics);
		adapter.setMusicId(app.getCurrentMusicIndex());
		listview.setAdapter(adapter);

		listItemClickListener = new InnerListViewItemClickListener();
		listview.setOnItemClickListener(listItemClickListener);
		// ��ʼ��������Ϣ����ʾ�ؼ�
		tvTitle = (TextView) findViewById(R.id.tv_title);
		tvArtist = (TextView) findViewById(R.id.tv_artist);
		tvAlbum = (TextView) findViewById(R.id.tv_album);
		tvDuration = (TextView) findViewById(R.id.tv_duration);
		// ��ʼ��������
		sbProgress = (SeekBar) findViewById(R.id.sb_progress);
		seekBarChangeListener = new InnerSeekBarChangeListener();
		sbProgress.setOnSeekBarChangeListener(seekBarChangeListener);
		// ��ʼ����ť
		ibPlay = (Button) findViewById(R.id.ib_play);
		ibPrevious = (Button) findViewById(R.id.ib_previous);
		ibNext = (Button) findViewById(R.id.ib_next);
		// Ϊ��ť��ӵ��������
		buttonClickListener = new ButtonClickListener();
		ibPlay.setOnClickListener(buttonClickListener);
		ibPrevious.setOnClickListener(buttonClickListener);
		ibNext.setOnClickListener(buttonClickListener);
	}

	@Override
	protected void onDestroy() {
		unbindService(conn);
		isThreadWorking = false;
		unregisterReceiver(receiver);
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if (System.currentTimeMillis() - exitTime > 2000) {
				Toast.makeText(this, "�ٰ�һ���˳�", Toast.LENGTH_LONG).show();
				exitTime = System.currentTimeMillis();
			} else {
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * ��ʼ���㲥������
	 */
	private void initReceiver() {
		// ��ʼ���㲥������
		receiver = new InnerReceiver();
		// ��ʼ���㲥�����ߵ���ͼ������
		filter = new IntentFilter();
		// ��ӽ��յĹ㲥����
		filter.addAction(SERVICE_PLAYER_PLAY);

		// ע��㲥������
		registerReceiver(receiver, filter);
	}

	// --------------------------------�ڲ���--------------------------------------
	private class InnerReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (SERVICE_PLAYER_PLAY.equals(action)) {
				if (app.getCurrentMusicIndex() != currentID) {
					adapter.setMusicId(app.getCurrentMusicIndex());
					adapter.notifyDataSetChanged();
				}
				currentID = app.getCurrentMusicIndex();

				tvTitle.setText(musics.get(app.getCurrentMusicIndex())
						.getTitle());
				tvArtist.setText(musics.get(app.getCurrentMusicIndex())
						.getArtist());
				tvAlbum.setText(musics.get(app.getCurrentMusicIndex())
						.getAlbum());
				Log.i("getAudioSessionId", app.getAudioSessionId()+"");
				visualizer.setupVisualizerFx(app.getAudioSessionId());
			}
		}
	}

	/**
	 * ��ť�ĵ��������
	 * 
	 */
	private class ButtonClickListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			if (!isStarted) {

				isThreadWorking = true;
				updateProgressThread = new updateProgressThread();
				updateProgressThread.start();
				isStarted = true;
			}

			switch (v.getId()) {
			
			case R.id.ib_play:

				if (isPlaying) {
					Log.i("", "binder.stop()" + isPlaying);
					binder.stop();
					ibPlay.setBackgroundResource(R.drawable.button_play); // �л���ťͼƬ
					isPlaying = false;
				} else {
					Log.i("", "binder.play()" + isPlaying);
					binder.play();
					ibPlay.setBackgroundResource(R.drawable.button_pause); // �л���ťͼƬ
					isPlaying = true;
				}

				break;

			case R.id.ib_previous:
				Log.i("", "binder.playPrevious()" + isPlaying);
				binder.playPrevious();
			
				break;

			case R.id.ib_next:
				Log.i("", "binder.playNext()" + isPlaying);
				binder.playNext();
	
				break;
			}

		}
	}

	/**
	 * ���ListView��ѡ��ʱ�ļ�����
	 * 
	 * @author tarena
	 * 
	 */
	private class InnerListViewItemClickListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			if (!isStarted) {

				isThreadWorking = true;
				updateProgressThread = new updateProgressThread();
				updateProgressThread.start();
				isStarted = true;
			}

			Log.i("", " position====" + position + "id======" + id);
			app.setCurrentMusicIndex(position);

			binder.play();
			ibPlay.setBackgroundResource(R.drawable.button_pause); // �л���ťͼƬ
			isPlaying = true;

		}
	}

	/**
	 * �������϶�������
	 */
	private class InnerSeekBarChangeListener implements OnSeekBarChangeListener {
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			binder.playSeekTo(seekBar.getProgress());
		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}

	}

	/**
	 * ����ServiceConnection����
	 */
	private class InnerServiceConnection implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			binder = (IMusicPlay) service;

		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			binder = null;
		}

	}

	/**
	 * ������Ϣ
	 */
	private class HandlerCallBack implements Handler.Callback {

		@Override
		public boolean handleMessage(Message msg) {
			if (msg.what == UPDATE_PROGRESS_OK) {

				currentProgress = currentProgress < 0 ? 0 : currentProgress;
				long totalduration = musics.get(app.getCurrentMusicIndex())
						.getDuration();
				int progress = (int) (currentProgress * 100 / totalduration);

				progress = progress > 100 || progress < 0 ? 0 : progress;

				sbProgress.setProgress(progress);

				String timeStr = TextFormatter.getMusicTime(currentProgress)
						+ "/" + TextFormatter.getMusicTime(totalduration);
				tvDuration.setText(timeStr);
			}
			return false;
		}

	}

	/**
	 * ���½����߳�
	 */
	private class updateProgressThread extends Thread {
		@Override
		public void run() {
			while (isThreadWorking) {

				currentProgress = binder.getProgress();

				handler.sendEmptyMessage(UPDATE_PROGRESS_OK);
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}

				super.run();

			}

		}
	}

	/**
	 * ViewPager��adapter
	 * 
	 * @author tarena
	 * 
	 */
	private class InnerPagerAdapter extends PagerAdapter {

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(childViews[position]);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(childViews[position]);
			return childViews[position];
		}

		@Override
		public int getCount() {
			return childViews.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

	}
}
