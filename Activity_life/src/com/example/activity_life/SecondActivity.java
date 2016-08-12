package com.example.activity_life;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondActivity extends Activity {
	Button bn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("SecondActivity--------->onCreate");
		setContentView(R.layout.activity_second);
		bn = (Button) findViewById(R.id.bn1);
		bn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(SecondActivity.this, MainActivity.class);
				startActivity(i);
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		System.out.println("SecondActivity--------->onStart");
		super.onStart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		System.out.println("SecondActivity--------->onResume");
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		System.out.println("SecondActivity--------->onPause");
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		System.out.println("SecondActivity--------->onStop");
		super.onStop();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		System.out.println("SecondActivity--------->onRestart");
		super.onRestart();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		System.out.println("SecondActivity--------->onDestroy");
		super.onDestroy();
	}

}
