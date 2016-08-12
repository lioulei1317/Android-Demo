package com.example.liulei10_02;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	public static final String ACTIVITY = "COM.SET.ACTIVITY";
	public static final String SERVICE = "COM.SET.SERVITY";
	TextView tv;
	Button btn;
	BroadService broadservice;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.textview);
		btn = (Button) findViewById(R.id.button001);
		broadservice=new BroadService();
		IntentFilter filter = new IntentFilter();
		filter.addAction(ACTIVITY);
		registerReceiver(broadservice, filter);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,Service001.class);
				startService(intent);
				System.out.println("Activity----------onclick");
			}
		});
	}

	public class BroadService extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			int number=intent.getIntExtra("number", -1);
			System.out.println("Activity-------------onReceive");
			if(number>=0){
				tv.setText(number+"s");
			}else{
				Intent in=new Intent();
				in.setAction(MainActivity.SERVICE);
				in.putExtra("bool", false);
				sendBroadcast(in);
				tv.setText("10s");
				System.out.println("11111111111111111111111111111");
			}
			
		}

	}
}
