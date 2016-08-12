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
	 * 公共音乐接口
	 */
	private IMusicPlay binder;
	/**
	 * 与service连接对象
	 */
	private ServiceConnection conn;
	/**
	 * handlerCallback处理消息
	 */
	private Handler.Callback handlerCallback;
	/**
	 * 更新线程进度
	 */
	private Thread updateProgressThread;
	/**
	 * 消息持有
	 */
	private Handler handler;
	/**
	 * 当前进度(毫秒)
	 */
	private int currentProgress;
	/**
	 * 线程是否工作
	 */
	private boolean isThreadWorking;
	/**
	 * Application对象
	 */
	private PlayMusicApplication app;
	/**
	 * 显示控件ListView
	 */
	private ListView listview;
	/**
	 * 歌曲的List集合
	 */
	private ArrayList<Music> musics;
	/**
	 * ListView的适配器
	 */
	private MusicListAdapter adapter;
	/**
	 * 按钮：播放/暂停，上一首，下一首
	 */
	private Button ibPlay, ibPrevious, ibNext;
	/**
	 * 按钮的点击监听器
	 */
	private ButtonClickListener buttonClickListener;
	/**
	 * 显示歌曲信息的TextView
	 */
	private TextView tvTitle, tvArtist, tvAlbum, tvDuration;
	/**
	 * ListView的监听器
	 */
	private OnItemClickListener listItemClickListener;
	/**
	 * 进度条
	 */
	private SeekBar sbProgress;
	/**
	 * 进度条拖动监听器
	 */
	private OnSeekBarChangeListener seekBarChangeListener;
	/**
	 * 是否由用户操作开始播放歌曲
	 */
	private boolean isStarted;
	/**
	 * 是否正在播放歌曲
	 */
	private boolean isPlaying;
	/**
	 * 广播接收者
	 */
	private BroadcastReceiver receiver;
	/**
	 * 广播接收者的意图过滤器
	 */
	private IntentFilter filter;
	private int currentID = -1;
	private long exitTime = 0;
	// ViewPager
	private ViewPager viewpager;
	// 子级页面的View数组
	private View[] childViews;
	// ViewPager的适配器
	private PagerAdapter pageradapter;
	private VisualizerView visualizer;// 均衡器视图
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		// 获取Application对象
		Log.d("", "MainActivity.onCreate()");
		app = (PlayMusicApplication) getApplication();
		// 界面的初始化
		initViews();

		// 创建意图对象 显式启动
		Intent intent = new Intent(this, PlayMusicService.class);
		// 建立连接
		conn = new InnerServiceConnection();
		// 绑定service
		bindService(intent, conn, BIND_AUTO_CREATE);
		// 初始化广播接收者
		initReceiver();
	}

	/**
	 * 初始化
	 */
	private void initViews() {
		// 初始化ViewPager
		viewpager = (ViewPager) findViewById(R.id.viewPager);
		// 初始化子级页面
		childViews = new View[2];
		childViews[0] = View.inflate(this, R.layout.listview_frarement, null);
		childViews[1] = View.inflate(this, R.layout.visualizer_frarement, null);
		// 初始化ViewPager的adapter
		pageradapter = new InnerPagerAdapter();
		// 为ViewPager配置adapter
		viewpager.setAdapter(pageradapter);
		
		
		visualizer=(VisualizerView)childViews[1].findViewById(R.id.activity_player_visualizer);
		// 初始化消息持有者
		
		handlerCallback = new HandlerCallBack();
		handler = new Handler(handlerCallback);
		// 初始化ListView相关
		listview = (ListView) childViews[0].findViewById(R.id.lv_music_list);
		musics = app.getMusicList();
		adapter = new MusicListAdapter(this, musics);
		adapter.setMusicId(app.getCurrentMusicIndex());
		listview.setAdapter(adapter);

		listItemClickListener = new InnerListViewItemClickListener();
		listview.setOnItemClickListener(listItemClickListener);
		// 初始化歌曲信息的显示控件
		tvTitle = (TextView) findViewById(R.id.tv_title);
		tvArtist = (TextView) findViewById(R.id.tv_artist);
		tvAlbum = (TextView) findViewById(R.id.tv_album);
		tvDuration = (TextView) findViewById(R.id.tv_duration);
		// 初始化进度条
		sbProgress = (SeekBar) findViewById(R.id.sb_progress);
		seekBarChangeListener = new InnerSeekBarChangeListener();
		sbProgress.setOnSeekBarChangeListener(seekBarChangeListener);
		// 初始化按钮
		ibPlay = (Button) findViewById(R.id.ib_play);
		ibPrevious = (Button) findViewById(R.id.ib_previous);
		ibNext = (Button) findViewById(R.id.ib_next);
		// 为按钮添加点击监听器
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
				Toast.makeText(this, "再按一次退出", Toast.LENGTH_LONG).show();
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
	 * 初始化广播接收者
	 */
	private void initReceiver() {
		// 初始化广播接收者
		receiver = new InnerReceiver();
		// 初始化广播接收者的意图过滤器
		filter = new IntentFilter();
		// 添加接收的广播类型
		filter.addAction(SERVICE_PLAYER_PLAY);

		// 注册广播接收者
		registerReceiver(receiver, filter);
	}

	// --------------------------------内部类--------------------------------------
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
	 * 按钮的点击监听器
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
					ibPlay.setBackgroundResource(R.drawable.button_play); // 切换按钮图片
					isPlaying = false;
				} else {
					Log.i("", "binder.play()" + isPlaying);
					binder.play();
					ibPlay.setBackgroundResource(R.drawable.button_pause); // 切换按钮图片
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
	 * 点击ListView的选项时的监听器
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
			ibPlay.setBackgroundResource(R.drawable.button_pause); // 切换按钮图片
			isPlaying = true;

		}
	}

	/**
	 * 进度条拖动监听器
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
	 * 建立ServiceConnection连接
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
	 * 处理消息
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
	 * 更新进度线程
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
	 * ViewPager的adapter
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
