package com.example.t20160409;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Testv extends Activity {
	TextView textview;
	Button bnnn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test1);
		textview = (TextView) findViewById(R.id.testview);
		bnnn = (Button) findViewById(R.id.bnnn);
		listenener_ l = new listenener_();
		textview.setOnClickListener(l);
		bnnn.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(Testv.this, "早上好", Toast.LENGTH_SHORT).show();
				bnnn.setText("登录成功！");
			}

		});
	}

	class listenener_ implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			textview.setText("很多人文件");
			textview.setBackgroundColor(Color.BLUE);
		}

	}

}