package com.example.gongjibao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Shezhibiankuangyanse001 extends Activity{
	Button szbkfhbtn;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.shezhibiankuangyanse001);
			szbkfhbtn=(Button) findViewById(R.id.szbkfhbtn);
			szbkfhbtn.setOnClickListener(fh);
		}
		OnClickListener fh=new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(Shezhibiankuangyanse001.this,MainActivity02.class);
				startActivity(intent);
				
			}
		};
}
