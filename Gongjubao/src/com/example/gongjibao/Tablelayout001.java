package com.example.gongjibao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Tablelayout001 extends Activity{
	Button bgfhbun;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.tablelayout001);
			bgfhbun=(Button) findViewById(R.id.bgfhbun);
			bgfhbun.setOnClickListener(bgfanhui);
			
		}
		OnClickListener bgfanhui=new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(Tablelayout001.this,MainActivity01.class);
				startActivity(intent);
				
			}
		};
}
