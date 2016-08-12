package com.example.onkey_;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	EditText edt;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		edt = (EditText) findViewById(R.id.ed1);
		tv = (TextView) findViewById(R.id.tv);
		edt.setOnKeyListener(new onkey());
	}

	class onkey implements OnKeyListener {

		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			// TODO Auto-generated method stub
			switch (event.getAction()) {
			case KeyEvent.ACTION_UP://¼üÅÌËÉ¿ª
				String s = edt.getText().toString();
				tv.setText(s);
				break;

			case KeyEvent.ACTION_DOWN://¼üÅÌ°´ÏÂ
				break;
			}
			return false;
		}

	}

}
