package com.example.yindaoye1;

import com.example.zuoye_shanping_aidlservice.ICat;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Zuchejiemian extends Activity {
	ICat icatservice;
	EditText yhm, mm;
	Button queding;
	private ServiceConnection conn=new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			icatservice=null;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			icatservice=ICat.Stub.asInterface(service);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zhucejiemian2);
		yhm = (EditText) findViewById(R.id.yhmedt002);
		mm = (EditText) findViewById(R.id.mmedt002);
		queding = (Button) findViewById(R.id.quedingbtn001);
		Intent intent=new Intent();
		intent.setAction("xxxxxxxxx");
		bindService(intent, conn, Service.BIND_AUTO_CREATE);
		queding.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String name = yhm.getText().toString();
				String password = mm.getText().toString();
				System.out.println(icatservice==null);
				if(name.equals("")||password.equals("")){
					Toast.makeText(Zuchejiemian.this, "用户名或密码不能为空!", Toast.LENGTH_SHORT).show();
				}else{
						try {
							icatservice.setName(name);
							icatservice.setPassword(password);
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Intent intent = new Intent(Zuchejiemian.this, Denglujiemian001.class);
						startActivity(intent);
						finish();
				
				}
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.unbindService(conn);
	}

}
