package com.example.gongjibao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Framelayout001 extends Activity{
	Button zbjfhbun;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.framelayout001);
		zbjfhbun=(Button) findViewById(R.id.zbjfhbun);
		zbjfhbun.setOnClickListener(syy);
	}
	OnClickListener syy=new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(Framelayout001.this,MainActivity01.class);
			startActivity(intent);
			
		}
	};
}
