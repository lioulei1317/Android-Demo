package com.example.gongjibao10;

import android.os.IBinder;
import android.app.Service;
import android.content.Intent;

public class Service002 extends Service{
	// 必须实现的方法
		@Override
		public IBinder onBind(Intent intent) {
			// TODO Auto-generated method stub
			return null;
		}

		// Service被创建时回调的方法
		@Override
		public void onCreate() {
			// TODO Auto-generated method stub
			super.onCreate();
			System.out.println("Service is Created");
		}

		// Service被启动时回调的方法
		@Override
		public int onStartCommand(Intent intent, int flags, int startId) {
			System.out.println("Service is Started");
			return START_NOT_STICKY;

		}
		//Service被关闭前回调的方法
		@Override
		public void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			System.out.println("Service is Destroyd");
		}

}

