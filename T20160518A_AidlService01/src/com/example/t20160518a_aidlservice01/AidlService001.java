package com.example.t20160518a_aidlservice01;

import java.util.Timer;
import java.util.TimerTask;

import com.example.t20160518a_aidlservice01.ICat.Stub;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AidlService001 extends Service {
	private CatBinder catBinder;
	Timer timer = new Timer();
	String colors[] = new String[] { "��ɫ", "��ɫ", "��ɫ" };
	double weights[] = new double[] { 2.5, 3.13, 1.58 };
	private String color;
	private double weight;

	@Override
	public IBinder onBind(Intent intent) {
		/*
		 * ����catBinder���� �ڰ󶨱���Service������£���catBinder�����ֱ��
		 * �����ͻ��˵�ServiceConnection���� ��onServiceConnected�����ĵڶ���������
		 * �ڰ�Զ��Service������£�ֻ��catBinder����Ĵ��� �����ͻ��˵�ServiceConnection����
		 * ��onServiceConnected�����ĵڶ���������
		 */
		return catBinder;
	}

	// �̳�Stub��Ҳ����ʵ��ICat�ӿڣ���ʵ��IBinder�ӿ�
	public class CatBinder extends Stub {

		@Override
		public String getColor() throws RemoteException {
			// TODO Auto-generated method stub
			return color;
		}

		@Override
		public double getWeight() throws RemoteException {
			// TODO Auto-generated method stub
			return weight;
		}

	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		catBinder = new CatBinder();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				// ����ظı�Service�����color��weight���Ե�ֵ��
				int rand = (int) (Math.random() * 3);
				color = colors[rand];
				weight = weights[rand];
				System.out.println("----------" + rand);
			}
		}, 0, 800);
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		timer.cancel();
	}
}