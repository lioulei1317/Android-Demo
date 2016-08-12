package com.example.volleytest;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;

/**
 * ����������Ҫ�Զ���һ��Application���ڴ���һ��ȫ�ֵ��������
 * 
 * @author Administrator
 */
public class MyApplication extends Application {
	// ����һ��ȫ���������
	private static RequestQueue queues;

	@Override
	public void onCreate() {
		super.onCreate();
		queues = Volley.newRequestQueue(getApplicationContext());
	}

	public static RequestQueue getHttpQueues() {
		return queues;
	}
}
