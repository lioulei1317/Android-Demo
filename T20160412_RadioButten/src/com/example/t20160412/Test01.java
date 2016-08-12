package com.example.t20160412;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class Test01 extends Activity {
	RadioGroup rgp;
	RadioButton rb1, rb2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test2);
		rgp = (RadioGroup) findViewById(R.id.rgp);
		rb1 = (RadioButton) findViewById(R.id.rb1);
		rb2 = (RadioButton) findViewById(R.id.rb2);
		rgp.setOnCheckedChangeListener(occl);

	}

	OnCheckedChangeListener occl = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup aroup, int checkId) {
			// 第一个参数  是被选中的RadioButton所在的radiogroup的"id@+id/rgp"
			// 第二个参数  是被选中的RadioButton的id"@+id/rb1"或"@+id/rb2"
			if (rb1.getId() == checkId) {
				Toast.makeText(Test01.this, "男", Toast.LENGTH_SHORT).show();
			} else if (rb2.getId() == checkId) {
				Toast.makeText(Test01.this, "女", Toast.LENGTH_SHORT).show();
			}
		}
	};

}
