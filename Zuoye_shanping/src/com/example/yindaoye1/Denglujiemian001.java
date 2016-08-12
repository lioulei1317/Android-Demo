package com.example.yindaoye1;

import com.example.zuoye_shanping_aidlservice.ICat;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Denglujiemian001 extends Activity {
	private ICat catService;
	private Button denglu, zhuce;
	EditText yym, mm;
	private ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			catService = null;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			catService = ICat.Stub.asInterface(service);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.denglujiemian1);
		denglu = (Button) findViewById(R.id.denglubtn001);
		yym = (EditText) findViewById(R.id.yhmedt001);
		mm = (EditText) findViewById(R.id.mmedt001);
		zhuce = (Button) findViewById(R.id.zhucebtn001);
		Intent intent = new Intent();
		intent.setAction("xxxxxxxxx");
		bindService(intent, conn, Service.BIND_AUTO_CREATE);
		denglu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					if (yym.getText().toString().equals(catService.getName())
							&& mm.getText().toString()
									.equals(catService.getPassword())) {
						Toast.makeText(Denglujiemian001.this, "µÇÂ½³É¹¦!",
								Toast.LENGTH_SHORT).show();
						 Intent intent=new
						 Intent(Denglujiemian001.this,Firstjiemian001.class);
						 startActivity(intent);

					} else {
						Toast.makeText(Denglujiemian001.this, "µÇÂ½Ê§°Ü!",
								Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
		zhuce.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Denglujiemian001.this,
						Zuchejiemian.class);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		this.unbindService(conn);
	}


}
