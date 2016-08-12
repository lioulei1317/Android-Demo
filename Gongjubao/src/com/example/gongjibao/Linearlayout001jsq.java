package com.example.gongjibao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Linearlayout001jsq extends Activity{
	Button jsqfanhui;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.linearlayout001jsq);
			jsqfanhui=(Button) findViewById(R.id.jsqfanhui);
			jsqfanhui.setOnClickListener(jsqfh);
		}
		OnClickListener jsqfh=new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(Linearlayout001jsq.this,Linearlayout001.class);
				startActivity(intent);
				
			}
		};
}
