package com.example.gongjibao;

import com.example.gongjibao10.MainAcivity10_01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity2 extends Activity {
	Button djiuzbun, dshizbun, dshiyizbun, dshierzbun, dshisanzbun, dshisizbun,
			dshiwuzbun,dshiliuzbun,dshiqizbun,zjmsyy1;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		djiuzbun=(Button) findViewById(R.id.djiuzbun);
		dshizbun=(Button) findViewById(R.id.dshizbun);
		dshiyizbun=(Button) findViewById(R.id.dshiyizbun);
		dshierzbun=(Button) findViewById(R.id.dshierzbun);
		dshisanzbun=(Button) findViewById(R.id.dshisanzbun);
		dshisizbun=(Button) findViewById(R.id.dshisizbun);
		dshiwuzbun=(Button) findViewById(R.id.dshiwuzbun);
		dshiliuzbun=(Button) findViewById(R.id.dshiliuzbun);
		dshiqizbun=(Button) findViewById(R.id.dshiqizbun);
		zjmsyy1=(Button) findViewById(R.id.zjmsyy1);
		
		
		dshizbun.setOnClickListener(dshiz);
		zjmsyy1.setOnClickListener(zjmsyy);
		
	}
	OnClickListener dshiz=new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(MainActivity2.this,MainAcivity10_01.class);
			startActivity(intent);
		}
	};
	OnClickListener zjmsyy=new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(MainActivity2.this,MainActivity.class);
			startActivity(intent);
		}
	};

}
