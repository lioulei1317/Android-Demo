package com.example.yindaoye1;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;

public class Shanping001 extends Activity {
	private SharedPreferences preferences;
	// 用SharedPreferences记录是首次运行还是多次运行

	int count;
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x123) {
				if (count == 0) {
					Intent intent = new Intent(Shanping001.this, Yindaoye.class);
					startActivity(intent);
				} else {
					Intent intent = new Intent(Shanping001.this,
							Denglujiemian001.class);
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
		setContentView(R.layout.shanping001);
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
