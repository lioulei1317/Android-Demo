package com.example.gongjibao;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button dyizbun, derzbun, dsanzbun, dsizbun, dwuzbun, dliuzbun, dqizbun,
			dbazbun, zjmxiayiye1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dyizbun = (Button) findViewById(R.id.dyizbun);
		derzbun = (Button) findViewById(R.id.derzbun);
		dsanzbun = (Button) findViewById(R.id.dsanzbun);
		dwuzbun = (Button) findViewById(R.id.dwuzbun);
		dliuzbun = (Button) findViewById(R.id.dliuzbun);
		dqizbun = (Button) findViewById(R.id.dqizbun);
		dbazbun = (Button) findViewById(R.id.dbazbun);
		zjmxiayiye1 = (Button) findViewById(R.id.zjmxyy1);
//		dyizbun.setOnClickListener(dyz);
		derzbun.setOnClickListener(dez);
		dsanzbun.setOnClickListener(dsz);
		zjmxiayiye1.setOnClickListener(zjmxyy);
	}

//	OnClickListener dyz = new OnClickListener() {
//
//		@Override
//		public void onClick(View arg0) {
//			// TODO Auto-generated method stub
//			Intent intent = new Intent();
//			startActivity(intent);
//		}
//	};

	OnClickListener dez = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity.this, MainActivity01.class);
			startActivity(intent);
		}
	};
	OnClickListener dsz = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity.this,MainActivity_dsz01 .class);
			startActivity(intent);
		}
	};

	OnClickListener zjmxyy = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity.this, MainActivity2.class);
			startActivity(intent);
		}
	};

}
