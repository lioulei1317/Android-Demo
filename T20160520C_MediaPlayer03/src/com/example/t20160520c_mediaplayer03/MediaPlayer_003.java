package com.example.t20160520c_mediaplayer03;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MediaPlayer_003 extends Activity {
	Button btn1, btn2;
	MediaPlayer mediaPlayer1, mediaPlayer2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mediaplayer_003);
		btn1 = (Button) findViewById(R.id.mdaolayer001);
		btn2 = (Button) findViewById(R.id.mdaolayer002);
		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mediaPlayer1 == null) {
					btn1.setText("1.暂停播放");
					// 使用MediaPlayer根据Uri来装载指定的声音文件
					mediaPlayer1 = new MediaPlayer().create(
							MediaPlayer_003.this,
							Uri.parse("http://up.haoduoge.com/mp3/2016-05-03/1462260302.mp3"));
					// 启动
					mediaPlayer1.start();
				} else {
					if (mediaPlayer1.isPlaying()) {// 判断是否在播放
						// 暂停播放
						mediaPlayer1.pause();
						btn1.setText("1.开始播放");
					} else {
						// 继续播放
						mediaPlayer1.start();
						btn1.setText("1.暂停播放");
					}
				}
			}
		});
		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mediaPlayer2 == null) {
					btn2.setText("2.暂停播放");
					// 设置要加载的网络mp3地址
					Uri uri = Uri
							.parse("http://up.haoduoge.com/mp3/2016-05-03/1462260302.mp3");
					mediaPlayer2 = new MediaPlayer();
					try {
						// 使用MediaPlayer根据Uri来装载指定的声音文件
						mediaPlayer2.setDataSource(MediaPlayer_003.this, uri);
						// 准备声音
						mediaPlayer2.prepare();
						// 播放声音
						mediaPlayer2.start();

					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				} else {
					if (mediaPlayer2.isPlaying()) {// 判断是否在播放
						// 暂停播放
						mediaPlayer2.pause();
						btn2.setText("2.开始播放");

					} else {
						// 继续播放
						mediaPlayer2.start();
						btn2.setText("2.暂停播放");
					}

				}
			}
		});
	}

}
