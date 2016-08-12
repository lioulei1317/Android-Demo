package com.example.t20160412;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Test030 extends Activity{
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test040);
		tv=(TextView) findViewById(R.id.tv);
		Intent i=getIntent();
		String s=i.getStringExtra("key");
		tv.setText(s);
	}
}
