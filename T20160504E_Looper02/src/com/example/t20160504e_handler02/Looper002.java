package com.example.t20160504e_handler02;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Looper002 extends Activity {
	static final String UPPER_NUM = "looper";
	EditText etNum;
	CalThread calThread;

	// 定义一个线程类
	class CalThread extends Thread {
		public Handler mHandler;

		@Override
		public void run() {
			// 调用创建一个Looper实例
			Looper.prepare();
			mHandler = new Handler() {
				// 定义处理消息的方法
				@Override
				public void handleMessage(Message msg) {
					if (msg.what == 0x123) {
						// getData()获取所携带的数据包
						int upper = msg.getData().getInt(UPPER_NUM);
						List<Integer> nums = new ArrayList<Integer>();
						for (int i = 101; i <= upper; i += 2) {
							boolean f = true;
							for (int j = 2; j < i; j++) {
								if (i % j == 0) {
									f = false;
									break;
								}
							}
							if (!f) {
								continue;
							}
							nums.add(i);
						}
						// 使用Toast显示统计出来的所有质数
						Toast.makeText(Looper002.this, nums.toString(),
								Toast.LENGTH_LONG).show();
					}
				}
			};
			// 启动looper
			Looper.loop();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.looper002);
		etNum = (EditText) findViewById(R.id.handleret001);
		calThread = new CalThread();
		// 启动新线程
		calThread.start();
	}

	// 为按钮的点击事件提供事件处理的函数
	public void cal(View source) {
		// 创建消息
		Message msg = new Message();
		msg.what = 0x123;
		// 数据包Bundle
		Bundle bundle = new Bundle();
		bundle.putInt(UPPER_NUM, Integer.parseInt(etNum.getText().toString()));
		msg.setData(bundle);
		// 向新线程中Handler发送消息
		calThread.mHandler.sendMessage(msg);
	}

}
