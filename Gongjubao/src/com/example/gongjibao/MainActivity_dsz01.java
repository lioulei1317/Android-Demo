package com.example.gongjibao;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity_dsz01 extends Activity {
	Button DrawViewbtn01, Configurationbtn02, Hander001, Hander002,
			Bunderbtn003, AsyncTaskbtn004, ActivityForResult005,
			Fragmentbtn006,cmxyy01;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_dsz01);
		DrawViewbtn01 = (Button) findViewById(R.id.DrawViewbtn01);
		Configurationbtn02 = (Button) findViewById(R.id.Configurationbtn02);
		Hander001 = (Button) findViewById(R.id.Handerbtn001);
		Hander002 = (Button) findViewById(R.id.Handerbtn002);
		Bunderbtn003 = (Button) findViewById(R.id.Bunderbtn003);
		AsyncTaskbtn004 = (Button) findViewById(R.id.AsyncTaskbtn004);
		ActivityForResult005 = (Button) findViewById(R.id.ActivityForResult005);
		Fragmentbtn006 = (Button) findViewById(R.id.Fragmentbtn006);
		cmxyy01 = (Button) findViewById(R.id.cmxyy01);

		DrawViewbtn01.setOnClickListener(drawview1);
		Configurationbtn02.setOnClickListener(Configuration2);
		Hander001.setOnClickListener(Hander3);
		Hander002.setOnClickListener(Hander4);
		Bunderbtn003.setOnClickListener(Bunder5);
		AsyncTaskbtn004.setOnClickListener(AsyncTask6);
		ActivityForResult005.setOnClickListener(ActivityForResult7);
		Fragmentbtn006.setOnClickListener(Fragment8);
		cmxyy01.setOnClickListener(xyy);

	}

	OnClickListener drawview1 = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity_dsz01.this,
					DrawView001.class);
			startActivity(intent);
		}
	};
	OnClickListener Configuration2 = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity_dsz01.this,
					Configuration001.class);
			startActivity(intent);
		}
	};
	OnClickListener Hander3 = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity_dsz01.this, Handler001.class);
			startActivity(intent);
		}
	};
	OnClickListener Hander4 = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity_dsz01.this, Handler002.class);
			startActivity(intent);
		}
	};
	OnClickListener Bunder5 = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity_dsz01.this, Bunder001.class);
			startActivity(intent);
		}
	};
	OnClickListener AsyncTask6 = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity_dsz01.this,
					AsyncTask001.class);
			startActivity(intent);
		}
	};
	OnClickListener ActivityForResult7 = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity_dsz01.this,
					ActivityForResult001.class);
			startActivity(intent);
		}
	};
	OnClickListener Fragment8 = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity_dsz01.this,
					Fragment001.class);
			startActivity(intent);
		}
	};
	OnClickListener xyy = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity_dsz01.this,
					MainActivity_dsz02.class);
			startActivity(intent);
		}
	};

}
