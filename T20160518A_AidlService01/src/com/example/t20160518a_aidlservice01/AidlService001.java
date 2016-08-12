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
	String colors[] = new String[] { "红色", "黄色", "黑色" };
	double weights[] = new double[] { 2.5, 3.13, 1.58 };
	private String color;
	private double weight;

	@Override
	public IBinder onBind(Intent intent) {
		/*
		 * 返回catBinder对象 在绑定本地Service的情况下，该catBinder对象会直接
		 * 传给客户端的ServiceConnection对象 的onServiceConnected方法的第二个参数；
		 * 在绑定远程Service的情况下，只将catBinder对象的代理 传给客户端的ServiceConnection对象
		 * 的onServiceConnected方法的第二个参数；
		 */
		return catBinder;
	}

	// 继承Stub，也就是实现ICat接口，并实现IBinder接口
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
				// 随机地改变Service组件内color、weight属性的值。
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