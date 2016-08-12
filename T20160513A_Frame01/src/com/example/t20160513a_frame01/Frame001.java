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
		// ��ȡ������ť
		Button play = (Button) findViewById(R.id.framebtn001);
		Button stop = (Button) findViewById(R.id.framebtn002);
		ImageView imageView = (ImageView) findViewById(R.id.frameimv001);
		// ��ȡAnimationDrawable��������
		final AnimationDrawable anim = (AnimationDrawable) imageView
				.getBackground();
		play.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// ��ʼ���Ŷ���
				anim.start();
			}
		});
		stop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// ֹͣ���Ŷ���
				anim.stop();
			}
		});
	}
}
