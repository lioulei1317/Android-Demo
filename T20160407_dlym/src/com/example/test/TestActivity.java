package com.example.test;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class TestActivity extends Activity {
	TextView textv, textv1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test1);
		textv = (TextView) findViewById(R.id.yhm);
		textv1 = (TextView) findViewById(R.id.mima);
		listener_ l = new listener_();
		textv.setOnClickListener(l);
		textv1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(TestActivity.this, "今天不好", Toast.LENGTH_SHORT)
						.show();
			}
		});

	}

	class listener_ implements OnClickListener {
		public void onClick(View arg0) {
			textv.setText("很好啊");
			textv.setBackgroundColor(Color.BLUE);
		}
	}
}
