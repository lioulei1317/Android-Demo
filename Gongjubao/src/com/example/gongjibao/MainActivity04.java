package com.example.gongjibao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity04 extends Activity {
	Button gridviewbtn001, spinnerbtn002, spinnerbtn003, spinnerbtn004,
			gallerybtn005, tabhstbtn006,scrollviewbtn007,notifibtn008,cjjmxyy009;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main04);
		gridviewbtn001 = (Button) findViewById(R.id.gridviewbtn001);
		spinnerbtn002 = (Button) findViewById(R.id.spinnerbtn002);
		spinnerbtn003 = (Button) findViewById(R.id.spinnerbtn003);
		spinnerbtn004 = (Button) findViewById(R.id.spinnerbtn004);
		gallerybtn005 = (Button) findViewById(R.id.gallerybtn005);
		tabhstbtn006 = (Button) findViewById(R.id.tabhstbtn006);
		scrollviewbtn007=(Button) findViewById(R.id.scrollviewbtn007);
		notifibtn008=(Button) findViewById(R.id.notifibtn008);
		cjjmxyy009=(Button) findViewById(R.id.cjjmxyy009);

		gridviewbtn001.setOnClickListener(gridview);
		spinnerbtn002.setOnClickListener(spinner);
		spinnerbtn003.setOnClickListener(spinner3);
		spinnerbtn004.setOnClickListener(spinner4);
		gallerybtn005.setOnClickListener(gallery5);
		tabhstbtn006.setOnClickListener(tabhst6);
		scrollviewbtn007.setOnClickListener(scrollview7);
		notifibtn008.setOnClickListener(notifi8);
		cjjmxyy009.setOnClickListener(xiayy);
	}

	OnClickListener gridview = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity04.this, GridView001.class);
			startActivity(intent);

		}
	};
	OnClickListener spinner = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity04.this, Spinner001.class);
			startActivity(intent);

		}
	};
	OnClickListener spinner3 = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity04.this, Spinner002.class);
			startActivity(intent);

		}
	};
	OnClickListener spinner4 = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity04.this, Spinner003.class);
			startActivity(intent);

		}
	};
	OnClickListener gallery5 = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity04.this, Gallery001.class);
			startActivity(intent);

		}
	};
	OnClickListener tabhst6 = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity04.this, TabHost001.class);
			startActivity(intent);

		}
	};
	OnClickListener scrollview7 = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity04.this, ScrollView001.class);
			startActivity(intent);
			
		}
	};
	OnClickListener notifi8 = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity04.this, Notification001.class);
			startActivity(intent);
			
		}
	};
	OnClickListener xiayy = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity04.this, MainActivity05.class);
			startActivity(intent);
			
		}
	};

}
