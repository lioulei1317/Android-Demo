package com.example.gongjibao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity01 extends Activity {

	Button xxbjbun, xdbjbun, jdbjbun, bgbjbun, zbjbun, cjmsyy01, dxan, fxan,
			pmdbun, cjmxyy01;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main01);
		xxbjbun = (Button) findViewById(R.id.xxbjbun);
		xdbjbun = (Button) findViewById(R.id.xdbjbun);
		jdbjbun = (Button) findViewById(R.id.jdbjbun);
		bgbjbun = (Button) findViewById(R.id.bgbjbun);
		zbjbun = (Button) findViewById(R.id.zbjbun);
		dxan = (Button) findViewById(R.id.dxanbun);
		fxan = (Button) findViewById(R.id.fxanbun);
		pmdbun = (Button) findViewById(R.id.pmdbun);
		cjmxyy01 = (Button) findViewById(R.id.cjmxyy01);
		xxbjbun.setOnClickListener(xxbj);
		xdbjbun.setOnClickListener(xdbj);
		jdbjbun.setOnClickListener(jdbj);
		bgbjbun.setOnClickListener(bgbj);
		zbjbun.setOnClickListener(zbj);
		dxan.setOnClickListener(dx);
		fxan.setOnClickListener(fx);
		pmdbun.setOnClickListener(pmd);
		cjmxyy01.setOnClickListener(xyy);

	}

	OnClickListener xxbj = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity01.this,
					Linearlayout001.class);
			startActivity(intent);
		}
	};
	OnClickListener xdbj = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity01.this,
					Ralativelayout001.class);
			startActivity(intent);
		}
	};
	OnClickListener jdbj = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Toast.makeText(MainActivity01.this, "¾ø¶Ô²»ÓÃ£¡", Toast.LENGTH_SHORT)
					.show();
		}
	};
	OnClickListener bgbj = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity01.this,
					Tablelayout001.class);
			startActivity(intent);
		}
	};
	OnClickListener zbj = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity01.this,
					Framelayout001.class);
			startActivity(intent);
		}
	};

	OnClickListener dx = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity01.this,
					Danxuananniu001.class);
			startActivity(intent);
		}
	};
	OnClickListener fx = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity01.this,
					Fuxuananniu001.class);
			startActivity(intent);
		}
	};
	OnClickListener pmd = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity01.this, Paomadeng001.class);
			startActivity(intent);
		}
	};
	OnClickListener xyy = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity01.this,
					MainActivity02.class);
			startActivity(intent);
		}
	};

}
