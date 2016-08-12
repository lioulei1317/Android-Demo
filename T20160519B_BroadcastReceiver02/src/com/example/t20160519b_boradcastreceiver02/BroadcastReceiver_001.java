package com.example.t20160519b_boradcastreceiver02;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BroadcastReceiver_001 extends Activity {
	Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.broadcastreceiver_001);
		btn=(Button) findViewById(R.id.boradcastbtn001);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setAction("com.kk.sky.RECEIVER");
				intent.putExtra("msg", "这是第一条广播消息");
				// 发送有序广播
				sendOrderedBroadcast(intent, null);
			}
		});
	}

}
