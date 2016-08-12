package com.example.t20160517b_bindservice01;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BindService002 extends Service {
	private int count;
	private boolean quit;
	// ����onBind�������صĶ���
	private MyBinder binder = new MyBinder();

	// ͨ���̳�Binder��ʵ��IBinder��
	public class MyBinder extends Binder {
		public int getCount() {
			// ��ȡService������״̬:count
			return count;
		}
	}

	// ����ʵ�ֵķ������󶨸�Serviceʱ�ص��÷���
	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("Service is Binded");
		return binder;
	}

	// Service������ʱ�ص��÷���
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
	//Service�Ͽ�����ʱ�ص��ķ���
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("Service is Unbinded");
		return true;
	}
	//Service���ر�֮ǰ���õķ���
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		this.quit=true;
		System.out.println("Service is destroyed");
	}

}
