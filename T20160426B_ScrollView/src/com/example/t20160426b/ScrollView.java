package com.example.t20160426b;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ScrollView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scrollview);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.scroll_view, menu);
		return true;
	}

}
