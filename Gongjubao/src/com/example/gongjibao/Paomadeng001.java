package com.example.gongjibao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Paomadeng001 extends Activity{
	Button pmdfhbtn;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.paomadeng001);
			pmdfhbtn=(Button) findViewById(R.id.pmdfhbtn);
			pmdfhbtn.setOnClickListener(fh);
		}
		OnClickListener fh=new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(Paomadeng001.this,MainActivity01.class);
				startActivity(intent);
				
			}
		};
}
