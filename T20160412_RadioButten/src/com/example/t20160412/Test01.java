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
			// ��һ������  �Ǳ�ѡ�е�RadioButton���ڵ�radiogroup��"id@+id/rgp"
			// �ڶ�������  �Ǳ�ѡ�е�RadioButton��id"@+id/rb1"��"@+id/rb2"
			if (rb1.getId() == checkId) {
				Toast.makeText(Test01.this, "��", Toast.LENGTH_SHORT).show();
			} else if (rb2.getId() == checkId) {
				Toast.makeText(Test01.this, "Ů", Toast.LENGTH_SHORT).show();
			}
		}
	};

}
