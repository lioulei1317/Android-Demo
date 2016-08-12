package com.example.t20160524b_urlconnection01;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class URLConnection001 extends Activity {
	Button get, post;
	TextView show;
	String response;
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x123) {
				show.setText(response);
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.urlconnection001);
		get = (Button) findViewById(R.id.urlget);
		post = (Button) findViewById(R.id.urlpost);
		show = (TextView) findViewById(R.id.urlshow);
		get.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread() {
					public void run() {
						response = URLConnection_GetPostUtil
								.sendGet(
										"https://www.oschina.net/home/login?goto_page=http%3A%2F%2Fwww.oschina.net%2F",
										null);
						// 发送消息通知UI线程更新UI组件
						handler.sendEmptyMessage(0x123);
					};
				}.start();
			}
		});
		post.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread() {
					public void run() {
						response = URLConnection_GetPostUtil.sendPost(
								"http://mai.sogou.com/tejia/9kuai9/",
								"fr=123newmz");
					};
				}.start();
				// 发送消息通知UI线程更新UI组件
				handler.sendEmptyMessage(0x123);
			}
		});
	}

}
