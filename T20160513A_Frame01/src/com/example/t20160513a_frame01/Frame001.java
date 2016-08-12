package com.example.t20160513a_frame01;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Frame001 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame001);
		// 获取两个按钮
		Button play = (Button) findViewById(R.id.framebtn001);
		Button stop = (Button) findViewById(R.id.framebtn002);
		ImageView imageView = (ImageView) findViewById(R.id.frameimv001);
		// 获取AnimationDrawable动画对象
		final AnimationDrawable anim = (AnimationDrawable) imageView
				.getBackground();
		play.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 开始播放动画
				anim.start();
			}
		});
		stop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 停止播放动画
				anim.stop();
			}
		});
	}
}
