package com.example.t20160412;

import android.app.Activity;
import android.os.Bundle;

import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class Test02 extends Activity {
	RadioGroup rgp,rgp2;
	RadioButton rb1, rb2, rb3, rb4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test4);
		rgp = (RadioGroup) findViewById(R.id.rgp1);
		rgp2 = (RadioGroup) findViewById(R.id.rgp2);
		rb1 = (RadioButton) findViewById(R.id.rb1);
		rb2 = (RadioButton) findViewById(R.id.rb2);
		rb3 = (RadioButton) findViewById(R.id.rb3);
		rb4 = (RadioButton) findViewById(R.id.rb4);
		rgp.setOnCheckedChangeListener(occl);
		rgp2.setOnCheckedChangeListener(occl);
		
	}

	OnCheckedChangeListener occl = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup arg0, int arg1) {
			// TODO Auto-generated method stub
			if (rb1.getId() == arg1) {
				rb3.setChecked(true);
				Toast.makeText(Test02.this, "男", Toast.LENGTH_SHORT).show();

			} else if (rb2.getId() == arg1) {
				rb4.setChecked(true);
				Toast.makeText(Test02.this, "女", Toast.LENGTH_SHORT).show();
			} else if (rb3.getId() == arg1) {
				rb1.setChecked(true);
				Toast.makeText(Test02.this, "男神", Toast.LENGTH_SHORT).show();
			} else if (rb4.getId() == arg1) {
				rb2.setChecked(true);
				Toast.makeText(Test02.this, "女神", Toast.LENGTH_SHORT).show();
			}
		}
	};
}
