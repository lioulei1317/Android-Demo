package com.example.t20160525b_gson01;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class Gson01 extends Activity {
	private String jsonData = "{\"name\":\"sky\",\"age\":20}";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gson01);
	}

	public void start(View v) {
		Gson03_03 json = new Gson03_03();
		json.jiexi(jsonData);
	}
}
