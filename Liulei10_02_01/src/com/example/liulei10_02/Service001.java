package com.example.liulei10_02;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class Service001 extends Service {
	Myclass myclass;
	int number = 10;
	boolean flag = true;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		System.out.println("Service----------onCreate");
		myclass = new Myclass();
		IntentFilter filter = new IntentFilter();
		filter.addAction(MainActivity.SERVICE);
		registerReceiver(myclass, filter);
		new Thread() {
			public void run() {
				while (flag) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Intent intent = new Intent();
					intent.setAction(MainActivity.ACTIVITY);
					intent.putExtra("number", number);
					sendBroadcast(intent);
					if (number >=0) {
						number--;
						System.out.println(number);
					} else {
						number = 10;
						System.out.println("6000000000000000");
					}
					
				}
			};
		}.start();
	}

	public class Myclass extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			boolean b = intent.getBooleanExtra("bool", false);
			System.out.println("b--------------"+b);
			flag = b;
			// service自身结束的方法
			stopSelf();
			System.out.println("Service-----------onReceive");
		}

	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println("Service========onDestroy");
	}

}
