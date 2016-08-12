package com.example.yiqugou;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class Splash_Screen extends Activity {
	private SharedPreferences preferences;
	// 用SharedPreferences记录是首次运行还是多次运行

	int count;
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x123) {
				if (count == 0) {
					Intent intent = new Intent(Splash_Screen.this, Yindaoye.class);
					startActivity(intent);
				} else {
					Intent intent = new Intent(Splash_Screen.this,
							Home_Page.class);
					startActivity(intent);
				}
				// editor对象方法如下
				// public abstractSharedPreferences.Editor putString(String
				// key,String value)
				// 通过执行commit（）或是apply（）方法，将会应用更改。
				Editor editor = preferences.edit();
				// 存入数据
				editor.putInt("count", ++count);
				// 提交修改
				editor.commit();
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash_screen);
		// getSharedPreferences(Sring name,int mode)方法得到一个Preferences的
		// 对象，参数name是preference文件的名字，mode则是方式，默认为0
		preferences = getSharedPreferences("count", MODE_WORLD_READABLE);
		count = preferences.getInt("count", 0);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				handler.sendEmptyMessage(0x123);
			}
		}, 3000);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
	}

}
