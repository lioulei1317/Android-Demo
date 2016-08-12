package com.example.volleytest;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;

/**
 * 首先我们需要自定义一个Application用于创建一个全局的请求队列
 * 
 * @author Administrator
 */
public class MyApplication extends Application {
	// 定义一个全局请求队列
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
