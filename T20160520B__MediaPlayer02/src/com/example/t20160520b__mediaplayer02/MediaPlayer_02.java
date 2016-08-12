package com.example.t20160520b__mediaplayer02;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class MediaPlayer_02 extends Activity {
	private ImageButton Start_Button, Shang_Button, Xia_Button;
	// 播放工具类，系统类
	private MediaPlayer mediaPlayer = null;
	private TextView textView;
	
	private static int current = -1;
	
	// 存放音乐资源的ID
	final int[] musics = { R.raw.qiyou, R.raw.choubaguai, R.raw.daoxiang };
	final String[] name = { "七友-梁汉文 ", "丑八怪-薛之谦 ", "稻香-周杰伦" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mediaplayer_02);
		
		// 通过findViewById找到资源
		Start_Button = (ImageButton) findViewById(R.id.imagebutton002);
		Shang_Button = (ImageButton) findViewById(R.id.imagebutton001);
		Xia_Button = (ImageButton) findViewById(R.id.imagebutton003);
		textView = (TextView) findViewById(R.id.mediaplaytext001);
		
		// 开始按钮监听器
		Start_Button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mediaPlayer == null) {
					startPlayVideo();// 开始播放初始文件
					Start_Button.setImageResource(R.drawable.zanting);// 更改为暂停图标
				} else if (mediaPlayer.isPlaying()) {
					pausePlayMusic();// 调用暂停方法
					Start_Button.setImageResource(R.drawable.bofang);// 更改为播放图标
				} else {
					continuePlay();// 调用继续播放
					Start_Button.setImageResource(R.drawable.zanting);// 更改为暂停图标
				}
			}
		});

		// 上一首按钮监听器
		Shang_Button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				playBackMusic();// 调用上一首方法
			}
		});
		
		// 下一首按钮监听器
		Xia_Button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				playNextMusic();// 调用下一首方法
			}
		});
	}

	// 开始播放
	public void startPlayVideo() {
		// 因为currentFilePosition的初始值是-1，所以此处强制赋值为0，即播放第一个音频文件
		current = 0;
		playMusic();

	}

	// 播放音乐
	public void playMusic() {
		if (current >= musics.length) {// 首先判断当前播放的文件是否超多了列表
			current = 0; // 返回到最低了，这样就能循环播放列表了
		}
		if (current < 0) {
			current = musics.length - 1;// 返回到顶了，这样反过来循环播放列表
		}
		// 每次开始播放列表时，都要将mediaPlayer释放掉，这样一边准备下一首或者上一首
		releaseMedia();
		try {
			mediaPlayer = new MediaPlayer().create(MediaPlayer_02.this,
					musics[current]);
			
			textView.setText(name[current]);
			
			mediaPlayer.start();// 开始播放
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// 当Mediaplayer的播放完成事件绑定事件监听器
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				playNextMusic(); // 在每次播放完成之后都用播放下一首
			}
		});
	}

	// 暂停
	public void pausePlayMusic() {
		if (mediaPlayer != null) {
			mediaPlayer.pause();
			// 设置不可获取焦点（文字不滚动）
			textView.setFocusable(false);
		}
	}

	// 继续
	public void continuePlay() {
		if (mediaPlayer != null) {
			mediaPlayer.start();
			// 设置可以获取焦点，（使文字滚动起来）
			textView.setFocusableInTouchMode(true);
			textView.requestFocus();
		}
	}

	// 播放下一首音乐
	public void playNextMusic() {
		current++;// 播放下一首时当前播放文件加1
		playMusic();
	}

	// 播放上一首音乐
	public void playBackMusic() {
		current--;// 减1，播放上一首
		playMusic();
	}

	// 这里是释放mediaPlayer播放对象
	public void releaseMedia() {
		if (mediaPlayer != null) {
			try {
				mediaPlayer.release();
				mediaPlayer = null;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

}
