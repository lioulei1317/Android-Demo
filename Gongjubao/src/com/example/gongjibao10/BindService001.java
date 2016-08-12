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
	// 保持所启动的Service的Ibinder对象
	BindService002.MyBinder binder;
	// 定义一个ServiceConnection对象
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
			// 获取Service的onBinde方法返回的MyBinder对象
			binder = (BindService002.MyBinder) service;// ①
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bindservice001);
		bind = (Button) findViewById(R.id.bindservice001);
		unbind = (Button) findViewById(R.id.bindservice002);
		getservicestatus = (Button) findViewById(R.id.bindservice003);
		// 创建启动Service的Intent
		final Intent intent = new Intent();
		// 为Intent设置Action属性
		intent.setAction("SERVICE");
		bind.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 绑定指定的Service
				bindService(intent, conn, Service.BIND_AUTO_CREATE);
			}
		});
		unbind.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 解除绑定Service
				unbindService(conn);
			}
		});
		getservicestatus.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//获取、冰显示Srevice的count值
				Toast.makeText(BindService001.this,"Service的count值为："+binder.getCount(), Toast.LENGTH_LONG).show();//②
			}
		});
	}
}
