package com.example.gongjibao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity_dsz03 extends Activity {
	Button Frame_Blastbtn001,Tweenbtn002;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_dsz03);
		Frame_Blastbtn001=(Button) findViewById(R.id.Frame_Blastbtn001);
		Tweenbtn002=(Button) findViewById(R.id.Tweenbtn002);
		
		Frame_Blastbtn001.setOnClickListener(Frame_Blast1);
		Tweenbtn002.setOnClickListener(Tween2);
	}
	OnClickListener Frame_Blast1=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(MainActivity_dsz03.this,Frame_Blast001.class);
			startActivity(intent);
		}
	};
	OnClickListener Tween2=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(MainActivity_dsz03.this,Tween001.class);
			startActivity(intent);
		}
	};
}
