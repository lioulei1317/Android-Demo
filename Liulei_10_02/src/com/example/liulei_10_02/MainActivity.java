package com.example.liulei_10_02;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView tv;
	Button btn;
	ActivityReceiver activityreceiver;
	int shu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.textview);
		btn = (Button) findViewById(R.id.button001);
		activityreceiver = new ActivityReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.set.activity");
		registerReceiver(activityreceiver, filter);
		Intent intent = new Intent(MainActivity.this, Service02.class);
		startService(intent);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tv.setText("60");
				Intent intent = new Intent("com.set.service");
				intent.putExtra("ff", tv.getText().toString());
				sendBroadcast(intent);
			}
		});

	}

	public class ActivityReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			 shu=intent.getIntExtra("gg", -1);
			tv.setText(shu+"");
			Intent intent2=new Intent("com.set.service");
			intent2.putExtra("ff", tv.getText().toString());
			sendBroadcast(intent2);

		}

	}
}
