package com.example.gongjibao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class HoverDialog001 extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hoverdialog001);
		Intent intent=new Intent();
		intent.setClass(HoverDialog001.this, HoverDialog002.class);
		startActivity(intent);
		
	}
}
