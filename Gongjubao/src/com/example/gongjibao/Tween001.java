package com.example.gongjibao;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Tween001 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tween001);
		final ImageView flower = (ImageView) findViewById(R.id.tweenflower001);
		// ���ص�һ�ݶ�����Դ
		final Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim);
		// ���ö���������������״̬
		anim.setFillAfter(true);
		// ���صڶ��ݶ�����Դ
		final Animation reverse = AnimationUtils.loadAnimation(this,
				R.anim.reverse);
		// ���ö���������������״̬
		reverse.setFillAfter(true);
		Button bn = (Button) findViewById(R.id.tweenbn001);
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0x123) {
					flower.startAnimation(reverse);
				}
			}
		};
		bn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				flower.startAnimation(anim);
				// ����3.5��������ڶ�������
				new Timer().schedule(new TimerTask() {
					@Override
					public void run() {
						handler.sendEmptyMessage(0x123);

					}
				}, 3500);
			}
		});
	}


}
