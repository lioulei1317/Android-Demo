package com.example.gongjibao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity03 extends Activity {
	Button listvbtn001, listvbtn002, cjlistviewbtn001, cjlistviewbtn002,
			beseadapterbtn001, baseadapterbtn002, baseadapterbtn005,
			listviewadapterbtn001, auotobtn001, cjmxyy04;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main03);
		listvbtn001 = (Button) findViewById(R.id.listvbtn001);
		listvbtn002 = (Button) findViewById(R.id.listvbtn002);
		cjlistviewbtn001 = (Button) findViewById(R.id.cjlistviewbtn001);
		cjlistviewbtn002 = (Button) findViewById(R.id.cjlistviewbtn002);
		beseadapterbtn001 = (Button) findViewById(R.id.beseadapterbtn001);
		baseadapterbtn002 = (Button) findViewById(R.id.baseadapterbtn002);
		baseadapterbtn005 = (Button) findViewById(R.id.baseadapterbtn005);
		listviewadapterbtn001 = (Button) findViewById(R.id.listviewadapterbtn001);
		auotobtn001 = (Button) findViewById(R.id.auotobtn001);
		cjmxyy04 = (Button) findViewById(R.id.cjmxyy04);

		listvbtn001.setOnClickListener(list1);
		listvbtn002.setOnClickListener(list2);
		cjlistviewbtn001.setOnClickListener(cjlistv);
		cjlistviewbtn002.setOnClickListener(cjlistv2);
		beseadapterbtn001.setOnClickListener(baseadpter1);
		baseadapterbtn002.setOnClickListener(baseadpter2);
		baseadapterbtn005.setOnClickListener(baseadpter5);
		listviewadapterbtn001.setOnClickListener(listviewadapter);
		auotobtn001.setOnClickListener(auto);
		cjmxyy04.setOnClickListener(xyy);

	}

	OnClickListener list1 = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub

			Intent intent = new Intent(MainActivity03.this, ListView002.class);
			startActivity(intent);
		}
	};
	OnClickListener list2 = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity03.this,
					ListActivity001.class);
			startActivity(intent);
		}
	};
	OnClickListener cjlistv = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub

			Intent intent = new Intent(MainActivity03.this,
					SimpleAdapter001.class);
			startActivity(intent);
		}
	};
	OnClickListener cjlistv2 = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity03.this,
					SimpleAdapter002.class);
			startActivity(intent);
		}
	};
	OnClickListener baseadpter1 = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity03.this,
					BaseAdapter001.class);
			startActivity(intent);
		}
	};
	OnClickListener baseadpter2 = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity03.this,
					BaseAdapter002_1.class);
			startActivity(intent);
		}
	};
	OnClickListener baseadpter5 = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity03.this,
					BaseAdapter003.class);
			startActivity(intent);
		}
	};
	OnClickListener listviewadapter = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity03.this,
					ListViewAdapter001_01.class);
			startActivity(intent);
		}
	};
	OnClickListener auto = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity03.this,
					AutoCompleteTextView001.class);
			startActivity(intent);
		}
	};
	OnClickListener xyy = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity03.this,
					MainActivity04.class);
			startActivity(intent);
		}
	};

}
