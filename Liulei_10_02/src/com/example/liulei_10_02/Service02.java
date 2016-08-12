package com.example.liulei_10_02;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class Service02 extends Service {
	private ServiceReceiver servicereceiver;
	private int shu;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		servicereceiver=new ServiceReceiver();
		IntentFilter filter=new IntentFilter();
		filter.addAction("com.set.service");
		registerReceiver(servicereceiver, filter);
		

	}

	public class ServiceReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String f = intent.getStringExtra("ff");
			int s = Integer.parseInt(f);
			if (s > 0) {

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				s--;
				Intent sendIntent = new Intent("com.set.activity");
				sendIntent.putExtra("gg", s);
				sendBroadcast(sendIntent);
			}

		}

	}
}
