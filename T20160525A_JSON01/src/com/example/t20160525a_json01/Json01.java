package com.example.t20160525a_json01;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Json01 extends Activity {
	private String jsonData = "[{\"name\":\"sky\",\"age\":20},{\"name\":\"ssfd\",\"age\":25}]";
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.json01);
		button = (Button) findViewById(R.id.jsonbtn001);
		button.setOnClickListener(new ButtonListener());
	}

	private class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Json02_Utils jsonUtils = new Json02_Utils();
			jsonUtils.parseJson(jsonData);
		}

	}

}
