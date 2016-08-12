package com.example.gongjibao;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity06 extends Activity{
	Button datePickerbtn001,ActionBarbtn002,ActionBarbtn003,ActionHomebtn004,ActionViewbtn005;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main06);
		datePickerbtn001=(Button) findViewById(R.id.datePickerbtn001);
		ActionBarbtn002=(Button) findViewById(R.id.ActionBarbtn002);
		ActionBarbtn003=(Button) findViewById(R.id.ActionBarbtn003);
		ActionHomebtn004=(Button) findViewById(R.id.ActionHomebtn004);
		ActionViewbtn005=(Button) findViewById(R.id.ActionViewbtn005);
		
		datePickerbtn001.setOnClickListener(datepicker1);
		ActionBarbtn002.setOnClickListener(ActionBar2);
		ActionBarbtn003.setOnClickListener(ActionBar3);
		ActionHomebtn004.setOnClickListener(ActionHome4);
		ActionViewbtn005.setOnClickListener(ActionView5);
	}
	OnClickListener datepicker1=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity06.this,
					DatePickerDialog001.class);
			startActivity(intent);
		}
	};
	OnClickListener ActionBar2=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity06.this,
					ActionBar001.class);
			startActivity(intent);
		}
	};
	OnClickListener ActionBar3=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity06.this,
					ActionBar002.class);
			startActivity(intent);
		}
	};
	OnClickListener ActionHome4=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity06.this,
					ActionHome001.class);
			startActivity(intent);
		}
	};
	OnClickListener ActionView5=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity06.this,
					ActionView001.class);
			startActivity(intent);
		}
	};

}
