package com.example.t20160519a_boradcastreceiver01;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BroadcastReceiver001 extends Activity {
	Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.broadcastreceiver001);
		btn=(Button) findViewById(R.id.boradcastreceivebtn001);
	
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setAction("com.intent.action.RECRIVER");
				intent.putExtra("msg", "这是一条简单的消息");
				sendBroadcast(intent);
			}
		});
	}

}
