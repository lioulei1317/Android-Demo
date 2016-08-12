package com.example.t20160409;

import android.os.Bundle;
import android.app.Activity;
import android.app.SearchManager.OnCancelListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button bn;
	EditText et1, et2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d("date", "onCreate execute");
		bn = (Button) findViewById(R.id.bn);
		et1 = (EditText) findViewById(R.id.et1);
		et2 = (EditText) findViewById(R.id.et2);
		bn.setOnClickListener(ocl);

	}

	OnClickListener ocl = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if ("123".equals(et1.getText().toString())
					|| "123".equals(et2.getText().toString())) {
				Toast.makeText(MainActivity.this, "µÇÂ½³É¹¦£¡", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(MainActivity.this, "´íÎó£¡", Toast.LENGTH_SHORT).show();
			}
		}
	};

}
