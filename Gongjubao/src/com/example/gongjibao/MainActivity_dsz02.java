package com.example.gongjibao;

import com.example.gongjibao.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity_dsz02 extends Activity {
	Button Fragmentlifebtn01, ViewPagerbtn02, ActionBar_Tabbtn003,
			ActionBar_DropDown004, Intentbtn005, Resourcesbtn006, Stylebtn007,
			Framebtn008,yyya01;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_dsz02);
		Fragmentlifebtn01 = (Button) findViewById(R.id.Fragmentlifebtn01);
		ViewPagerbtn02 = (Button) findViewById(R.id.ViewPagerbtn02);
		ActionBar_Tabbtn003 = (Button) findViewById(R.id.ActionBar_Tabbtn003);
		ActionBar_DropDown004 = (Button) findViewById(R.id.ActionBar_DropDown004);
		Intentbtn005 = (Button) findViewById(R.id.Intentbtn005);
		Resourcesbtn006 = (Button) findViewById(R.id.Resourcesbtn006);
		Stylebtn007 = (Button) findViewById(R.id.Stylebtn007);
		Framebtn008 = (Button) findViewById(R.id.Framebtn008);
		yyya01 = (Button) findViewById(R.id.yyya01);

		Fragmentlifebtn01.setOnClickListener(Fragmentlife1);
		ViewPagerbtn02.setOnClickListener(ViewPager2);
		ActionBar_Tabbtn003.setOnClickListener(ActionBar_Tab3);
		ActionBar_DropDown004.setOnClickListener(ActionBar_DropDown4);
		Intentbtn005.setOnClickListener(Intent5);
		Resourcesbtn006.setOnClickListener(Resources6);
		Stylebtn007.setOnClickListener(Style7);
		Framebtn008.setOnClickListener(Frame8);
		yyya01.setOnClickListener(yyy);
	}

	OnClickListener Fragmentlife1 = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity_dsz02.this,
					Fragmentlift001.class);
			startActivity(intent);
		}
	};
	OnClickListener ViewPager2 = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity_dsz02.this,
					ViewPager001.class);
			startActivity(intent);
		}
	};
	OnClickListener ActionBar_Tab3 = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity_dsz02.this,
					ActionBar_Tab001.class);
			startActivity(intent);
		}
	};
	OnClickListener ActionBar_DropDown4 = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity_dsz02.this,
					ActionBar_DropDown001.class);
			startActivity(intent);
		}
	};
	OnClickListener Intent5 = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity_dsz02.this, Intent001.class);
			startActivity(intent);
		}
	};
	OnClickListener Resources6 = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity_dsz02.this,
					Resources001.class);
			startActivity(intent);
		}
	};
	OnClickListener Style7 = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity_dsz02.this, Style001.class);
			startActivity(intent);
		}
	};
	OnClickListener Frame8 = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity_dsz02.this, Frame001.class);
			startActivity(intent);
		}
	};
	OnClickListener yyy = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MainActivity_dsz02.this, MainActivity_dsz03.class);
			startActivity(intent);
		}
	};

}
