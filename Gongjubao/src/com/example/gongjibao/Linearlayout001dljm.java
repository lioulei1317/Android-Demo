package com.example.gongjibao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Linearlayout001dljm extends Activity{
	Button fanhuiecjm0001;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout002dljm);
		fanhuiecjm0001=(Button) findViewById(R.id.fanhuiecjm0001);
		fanhuiecjm0001.setOnClickListener(fanhui);
	}
	OnClickListener fanhui=new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(Linearlayout001dljm.this,Linearlayout001.class);
			startActivity(intent);
			
		}
	};

}
