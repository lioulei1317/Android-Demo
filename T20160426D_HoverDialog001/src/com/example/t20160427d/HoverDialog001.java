package com.example.t20160427d;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class HoverDialog001 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hoverdialog001);
		Intent intent=new Intent();
		intent.setClass(HoverDialog001.this, HoverDialog002.class);
		startActivity(intent);
		
	}
	
	


}
