package com.example.t20160518a_aidlclient01;

import com.example.t20160518a_aidlservice01.ICat;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AidlClient001 extends Activity {
	private ICat catService;
	private Button get;
	EditText color, weight;
	private ServiceConnection conn = new ServiceConnection() {
		// ��activity��service�Ͽ�����ʱ�ص��÷���
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			catService = null;

		}

		// ��activity��service�ɹ�����ʱ�ص��÷���
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			catService = ICat.Stub.asInterface(service);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aidlclient001);
		get = (Button) findViewById(R.id.getbtn001);
		color = (EditText) findViewById(R.id.coloredt001);
		weight = (EditText) findViewById(R.id.weightedt001);
		// ��������󶨵�Service��Intent
		Intent intent = new Intent();
		intent.setAction("SERVICE002");
		// ��Զ��Service
		bindService(intent, conn, Service.BIND_AUTO_CREATE);
		get.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					// ��ȡ������ʾԶ��Service��״̬
					color.setText(catService.getColor());
					weight.setText(catService.getWeight() + "");
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// �����
		this.unbindService(conn);
	}
}
