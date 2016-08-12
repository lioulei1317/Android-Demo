package com.example.gongjibao10;

import com.example.gongjibao.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainAcivity10_01 extends Activity{
	Button Servicebtn001,BindServicebtn002;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_10_01);
		Servicebtn001=(Button) findViewById(R.id.servicebtn001);
		BindServicebtn002=(Button) findViewById(R.id.BindServicebtn002);
		
		Servicebtn001.setOnClickListener(Service1);
		BindServicebtn002.setOnClickListener(BindServicebtn2);
	}
	OnClickListener Service1=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent=new Intent(MainAcivity10_01.this,Service001.class);
			startActivity(intent);
		}
	};
	OnClickListener BindServicebtn2=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent=new Intent(MainAcivity10_01.this,BindService001.class);
			startActivity(intent);
		}
	};

}
