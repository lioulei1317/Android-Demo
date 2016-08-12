package com.example.gongjibao10;
import com.example.gongjibao.R;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class BindService001 extends Activity {
	Button bind, unbind, getservicestatus;
	// ������������Service��Ibinder����
	BindService002.MyBinder binder;
	// ����һ��ServiceConnection����
	private ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			System.out.println("--Service Disconnected--");
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			System.out.println("--Service Connected--");
			// ��ȡService��onBinde�������ص�MyBinder����
			binder = (BindService002.MyBinder) service;// ��
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bindservice001);
		bind = (Button) findViewById(R.id.bindservice001);
		unbind = (Button) findViewById(R.id.bindservice002);
		getservicestatus = (Button) findViewById(R.id.bindservice003);
		// ��������Service��Intent
		final Intent intent = new Intent();
		// ΪIntent����Action����
		intent.setAction("SERVICE");
		bind.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ��ָ����Service
				bindService(intent, conn, Service.BIND_AUTO_CREATE);
			}
		});
		unbind.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// �����Service
				unbindService(conn);
			}
		});
		getservicestatus.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//��ȡ������ʾSrevice��countֵ
				Toast.makeText(BindService001.this,"Service��countֵΪ��"+binder.getCount(), Toast.LENGTH_LONG).show();//��
			}
		});
	}
}
