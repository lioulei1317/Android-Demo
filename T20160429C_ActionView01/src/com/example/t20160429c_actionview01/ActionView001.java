package com.example.t20160429c_actionview01;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ActionView001 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actionview001);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.action_view001, menu);
		return true;
	}

}
