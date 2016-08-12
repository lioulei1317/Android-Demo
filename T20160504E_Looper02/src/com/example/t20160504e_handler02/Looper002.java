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

	// ����һ���߳���
	class CalThread extends Thread {
		public Handler mHandler;

		@Override
		public void run() {
			// ���ô���һ��Looperʵ��
			Looper.prepare();
			mHandler = new Handler() {
				// ���崦����Ϣ�ķ���
				@Override
				public void handleMessage(Message msg) {
					if (msg.what == 0x123) {
						// getData()��ȡ��Я�������ݰ�
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
						// ʹ��Toast��ʾͳ�Ƴ�������������
						Toast.makeText(Looper002.this, nums.toString(),
								Toast.LENGTH_LONG).show();
					}
				}
			};
			// ����looper
			Looper.loop();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.looper002);
		etNum = (EditText) findViewById(R.id.handleret001);
		calThread = new CalThread();
		// �������߳�
		calThread.start();
	}

	// Ϊ��ť�ĵ���¼��ṩ�¼�����ĺ���
	public void cal(View source) {
		// ������Ϣ
		Message msg = new Message();
		msg.what = 0x123;
		// ���ݰ�Bundle
		Bundle bundle = new Bundle();
		bundle.putInt(UPPER_NUM, Integer.parseInt(etNum.getText().toString()));
		msg.setData(bundle);
		// �����߳���Handler������Ϣ
		calThread.mHandler.sendMessage(msg);
	}

}
