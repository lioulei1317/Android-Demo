package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class In extends Activity {
	Button zc;
	EditText et1, et2, ety, etm;
	TextView xs1;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test2);
		et1 = (EditText) findViewById(R.id.et1);
		et2 = (EditText) findViewById(R.id.et2);
		ety = (EditText) findViewById(R.id.ety);
		etm = (EditText) findViewById(R.id.etm);
		xs1 = (TextView) findViewById(R.id.xs1);
		zc = (Button) findViewById(R.id.zc);
		zc.setOnClickListener(ocl);
		Intent i = getIntent();
		String s = i.getStringExtra("�û���");
		et1.setText(s);
		String k = i.getStringExtra("����");
		et2.setText(k);

	}

	OnClickListener ocl = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(In.this, Test01.class);
			startActivity(intent);
		}
	};
	OnClickListener ocl2 = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if (et1.getText().toString().equals(ety.getText().toString())
					&& et2.getText().toString()
							.equals(etm.getText().toString())) {
				xs1.setText("��¼�ɹ�");
				Intent intent = new Intent(In.this, Test02.class);

			} else if (ety.getText().toString().equals("")) {
				xs1.setText("�û�������Ϊ�գ�");
			} else if (etm.getText().toString().equals("")) {
				xs1.setText("���벻��Ϊ�գ�");
			}
		}
	};
}
