package com.example.gongjibao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Ralativelayout001 extends Activity{
	Button xdbjdlbun0001,xdbjsyybun0001,xdbjjpbtn0001;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ralativelayout001);
		xdbjdlbun0001=(Button) findViewById(R.id.xdbjdlbun0001);
		xdbjsyybun0001=(Button) findViewById(R.id.xdbjsyybun0001);
		xdbjjpbtn0001=(Button) findViewById(R.id.xdbjjpbtn0001);
		xdbjdlbun0001.setOnClickListener(denglu);
		xdbjsyybun0001.setOnClickListener(syy);
		xdbjjpbtn0001.setOnClickListener(jianpan);
		
	}
	OnClickListener denglu=new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(Ralativelayout001.this,Ralativelayout001dljm.class);
			startActivity(intent);
			
		}
	};
	OnClickListener syy=new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(Ralativelayout001.this,MainActivity01.class);
			startActivity(intent);
		}
	};
	OnClickListener jianpan=new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(Ralativelayout001.this,Ralativelayout001jianpan.class);
			startActivity(intent);
		}
	};
}
