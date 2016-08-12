package com.example.t20160517b_bindservice01;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BindService002 extends Service {
	private int count;
	private boolean quit;
	// 定义onBind方法返回的对象
	private MyBinder binder = new MyBinder();

	// 通过继承Binder类实现IBinder类
	public class MyBinder extends Binder {
		public int getCount() {
			// 获取Service的运行状态:count
			return count;
		}
	}

	// 必须实现的方法，绑定该Service时回调该方法
	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("Service is Binded");
		return binder;
	}

	// Service被创建时回调该方法
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		System.out.println("Service is created");
		new Thread() {
			@Override
			public void run() {
				while (!quit) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					count++;
				}
			};
		}.start();
	}
	//Service断开连接时回调的方法
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("Service is Unbinded");
		return true;
	}
	//Service被关闭之前调用的方法
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		this.quit=true;
		System.out.println("Service is destroyed");
	}

}
