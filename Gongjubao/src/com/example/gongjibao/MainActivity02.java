package com.example.gongjibao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity02 extends Activity{
	Button cjmxyy01,szbkysbun,tmdbtn,szbun,jdtbun,xjtbun,tskbun,pailiebtn,fgtbtn02;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main02);
			cjmxyy01=(Button) findViewById(R.id.cjmxyy01);
			szbkysbun=(Button) findViewById(R.id.szbkysbun);
			tmdbtn=(Button) findViewById(R.id.tmdbtn);
			szbun=(Button) findViewById(R.id.szbun);
			jdtbun=(Button) findViewById(R.id.jdtbun);
			xjtbun=(Button) findViewById(R.id.xjtbun);
			tskbun=(Button) findViewById(R.id.tskbun);
			pailiebtn=(Button) findViewById(R.id.pailiebtn);
			fgtbtn02=(Button) findViewById(R.id.fgtbtn02);
			szbkysbun.setOnClickListener(szbkys);
			cjmxyy01.setOnClickListener(xyy);
			tmdbtn.setOnClickListener(tmd);
			szbun.setOnClickListener(sz);
			jdtbun.setOnClickListener(jdt);
			xjtbun.setOnClickListener(xjt);
			tskbun.setOnClickListener(tsk);
			pailiebtn.setOnClickListener(pailie);
			fgtbtn02.setOnClickListener(fg);

		}
		OnClickListener xyy=new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity02.this,MainActivity03.class);
				startActivity(intent);
				
			}
		};
		OnClickListener szbkys=new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity02.this,Shezhibiankuangyanse001.class);
				startActivity(intent);
				
			}
		};
		OnClickListener tmd=new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity02.this,Toumingdu001.class);
				startActivity(intent);
				
			}
		};
		OnClickListener sz=new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity02.this,Shizhong001.class);
				startActivity(intent);
				
			}
		};
		OnClickListener jdt=new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity02.this,Jindutiao001.class);
				startActivity(intent);
				
			}
		};
		OnClickListener xjt=new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity02.this,Xingjitiao001.class);
				startActivity(intent);
				
			}
		};
		OnClickListener tsk=new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity02.this,Tishikuang001.class);
				startActivity(intent);
				
			}
		};
		OnClickListener pailie=new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity02.this,Pailie001.class);
				startActivity(intent);
				
			}
		};
		OnClickListener fg=new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity02.this,ListView001.class);
				startActivity(intent);
				
			}
		};
}
