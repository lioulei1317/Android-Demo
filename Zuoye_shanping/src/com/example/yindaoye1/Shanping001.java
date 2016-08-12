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
	// ��SharedPreferences��¼���״����л��Ƕ������

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
				// editor���󷽷�����
				// public abstractSharedPreferences.Editor putString(String
				// key,String value)
				// ͨ��ִ��commit��������apply��������������Ӧ�ø��ġ�
				Editor editor = preferences.edit();
				// ��������
				editor.putInt("count", ++count);
				// �ύ�޸�
				editor.commit();
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shanping001);
		// getSharedPreferences(Sring name,int mode)�����õ�һ��Preferences��
		// ���󣬲���name��preference�ļ������֣�mode���Ƿ�ʽ��Ĭ��Ϊ0
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
