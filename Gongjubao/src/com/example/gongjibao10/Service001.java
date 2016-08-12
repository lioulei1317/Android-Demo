package com.example.gongjibao10;

import com.example.gongjibao.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Service001 extends Activity {
	Button start, stop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service001);
		start = (Button) findViewById(R.id.servicebtn001);
		stop = (Button) findViewById(R.id.servicebtn002);
		final Intent intent = new Intent();
		intent.setAction("SERVICE");
		start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startService(intent);
			}
		});
		stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stopService(intent);
			}
		});
	}
}
