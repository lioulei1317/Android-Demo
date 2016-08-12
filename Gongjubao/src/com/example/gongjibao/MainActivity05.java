package com.example.gongjibao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity05 extends Activity {
	Button hoverbtn001, progressdlgbtn002, menubtn003, menubtn004,
			contextmenubtn005,menubtn006,menubtn007,alertdialogbtn008,xyyactivity09;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main05);
		hoverbtn001 = (Button) findViewById(R.id.hoverbtn001);
		progressdlgbtn002 = (Button) findViewById(R.id.progressdlgbtn002);
		menubtn003 = (Button) findViewById(R.id.menubtn003);
		menubtn004 = (Button) findViewById(R.id.menubtn004);
		contextmenubtn005 = (Button) findViewById(R.id.contextmenubtn005);
		menubtn006 = (Button) findViewById(R.id.menubtn006);
		menubtn007 = (Button) findViewById(R.id.menubtn007);
		alertdialogbtn008=(Button) findViewById(R.id.alertdialogbtn008);
		xyyactivity09=(Button) findViewById(R.id.xyyactivity09);

		hoverbtn001.setOnClickListener(hover1);
		progressdlgbtn002.setOnClickListener(progressdlg2);
		menubtn003.setOnClickListener(menu3);
		menubtn004.setOnClickListener(menu4);
		contextmenubtn005.setOnClickListener(contextmun5);
		menubtn006.setOnClickListener(menubtn6);
		menubtn007.setOnClickListener(menubtn7);
		alertdialogbtn008.setOnClickListener(alerdialog8);
		xyyactivity09.setOnClickListener(xyy);
	}

	OnClickListener hover1 = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity05.this,
					HoverDialog001.class);
			startActivity(intent);

		}
	};
	OnClickListener progressdlg2 = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity05.this,
					ProgressDialog001.class);
			startActivity(intent);

		}
	};
	OnClickListener menu3 = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity05.this, Menu001.class);
			startActivity(intent);

		}
	};
	OnClickListener menu4 = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity05.this, Menu003.class);
			startActivity(intent);

		}
	};
	OnClickListener contextmun5 = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity05.this,
					ContextMenu001.class);
			startActivity(intent);

		}
	};
	OnClickListener menubtn6 = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity05.this,
					Menu004.class);
			startActivity(intent);
			
		}
	};
	OnClickListener menubtn7 = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity05.this,
					Menu005.class);
			startActivity(intent);
			
		}
	};
	OnClickListener alerdialog8 = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity05.this,
					AlertDialog001.class);
			startActivity(intent);
			
		}
	};
	OnClickListener xyy = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity05.this,
					MainActivity06.class);
			startActivity(intent);
			
		}
	};

}
