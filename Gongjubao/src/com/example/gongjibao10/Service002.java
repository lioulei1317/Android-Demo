package com.example.gongjibao10;

import android.os.IBinder;
import android.app.Service;
import android.content.Intent;

public class Service002 extends Service{
	// ����ʵ�ֵķ���
		@Override
		public IBinder onBind(Intent intent) {
			// TODO Auto-generated method stub
			return null;
		}

		// Service������ʱ�ص��ķ���
		@Override
		public void onCreate() {
			// TODO Auto-generated method stub
			super.onCreate();
			System.out.println("Service is Created");
		}

		// Service������ʱ�ص��ķ���
		@Override
		public int onStartCommand(Intent intent, int flags, int startId) {
			System.out.println("Service is Started");
			return START_NOT_STICKY;

		}
		//Service���ر�ǰ�ص��ķ���
		@Override
		public void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			System.out.println("Service is Destroyd");
		}

}

