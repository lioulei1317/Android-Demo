package com.example.t20160506a_activityforresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ActivityForResult002 extends Activity{
	EditText editText;
	Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activityforresult002);
		editText=(EditText) findViewById(R.id.activityforett004);
		button=(Button) findViewById(R.id.activityforbtn003);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=getIntent();
				intent.putExtra("city", editText.getText().toString());
				//设置该SelectActivity的结果码
				//并设置结束之后退回的Activity
				ActivityForResult002.this.setResult(55, intent);
				ActivityForResult002.this.finish();
			}
		});
	}
}
