package com.best.heart;

import com.example.nature.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.star);
		new Thread(new Runnable() {
			@Override 
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Intent intent = new Intent(MainActivity.this,IndexActivity.class);
				startActivity(intent);
				finish();
			}
		}).start();
	}
}
