package com.example.gongjibao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Linearlayout001 extends Activity {
	Button jisuanq001, denglujiemian002, fanhui003, jianpan002;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout001);
		jisuanq001 = (Button) findViewById(R.id.jisuanq001);
		denglujiemian002 = (Button) findViewById(R.id.denglujiemian002);
		fanhui003 = (Button) findViewById(R.id.fanhui003);
		jianpan002 = (Button) findViewById(R.id.jianpan002);
		jisuanq001.setOnClickListener(jisuanqi);
		denglujiemian002.setOnClickListener(dljm);
		fanhui003.setOnClickListener(fanhui);
		jianpan002.setOnClickListener(jianpan);
	}

	OnClickListener jisuanqi = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(Linearlayout001.this,
					Linearlayout001jsq.class);
			startActivity(intent);
		}
	};
	OnClickListener dljm = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(Linearlayout001.this,
					Linearlayout001dljm.class);
			startActivity(intent);
		}
	};
	OnClickListener fanhui = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(Linearlayout001.this,
					MainActivity01.class);
			startActivity(intent);
		}
	};
	OnClickListener jianpan = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(Linearlayout001.this,
					Linearlayout001jianpan.class);
			startActivity(intent);
		}
	};
}
