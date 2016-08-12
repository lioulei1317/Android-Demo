package com.example.activity_life;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button bn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("MainActivity--------->onCreate");
		setContentView(R.layout.activity_main);
		bn = (Button) findViewById(R.id.bn);
		bn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						SecondActivity.class);
				startActivity(intent);
			}
		});
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		System.out.println("MainActivity--------->onStart");
		super.onStart();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		System.out.println("MainActivity--------->onResume");
		super.onResume();
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		System.out.println("MainActivity--------->onPause");
		super.onPause();
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		System.out.println("MainActivity--------->onStop");
		super.onStop();
	}
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		System.out.println("MainActivity--------->onRestart");
		super.onRestart();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		System.out.println("MainActivity--------->onDestroy");
		super.onDestroy();
	}

}
