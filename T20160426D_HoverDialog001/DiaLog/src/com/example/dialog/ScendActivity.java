package com.example.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ScendActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scend);
		Intent intent = new Intent(ScendActivity.this, MainActivity.class);
		startActivity(intent);
	}
}
