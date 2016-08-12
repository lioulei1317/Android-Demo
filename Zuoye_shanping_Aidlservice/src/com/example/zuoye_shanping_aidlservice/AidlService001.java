package com.example.zuoye_shanping_aidlservice;

import com.example.zuoye_shanping_aidlservice.ICat.Stub;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;


public class AidlService001 extends Service{
	private Myclass myclass;
	private String names;
	private String passwords;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return myclass;
	}
	public class Myclass extends Stub{

		@Override
		public String getName() throws RemoteException {
			// TODO Auto-generated method stub
			return names;
		}

		@Override
		public String getPassword() throws RemoteException {
			// TODO Auto-generated method stub
			return passwords;
		}

		@Override
		public String setName(String name) throws RemoteException {
			AidlService001.this.names=name;
			return null;
		}

		@Override
		public String setPassword(String password) throws RemoteException {
			AidlService001.this.passwords=password;
			return null;
		}


	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		myclass=new Myclass();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
