package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Test01 extends Activity {
	Button queren01, fanhui1;
	EditText et1, et2, et3, et4;
	TextView mimayanse, xianshimm;
	int a = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test1);
		queren01 = (Button) findViewById(R.id.queren01);
		fanhui1 = (Button) findViewById(R.id.fanhui1);
		et1 = (EditText) findViewById(R.id.et1);
		et2 = (EditText) findViewById(R.id.et2);
		et3 = (EditText) findViewById(R.id.et3);
		mimayanse = (TextView) findViewById(R.id.mimayanse);
		xianshimm = (TextView) findViewById(R.id.xianshimm);
		xianshimm.setOnClickListener(ocl2);
		fanhui1.setOnClickListener(ocl3);
		queren01.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Test01.this,
						Test01.class);
				intent.putExtra("�û���", et1.getText().toString());
				intent.putExtra("����", et2.getText().toString());
				startActivity(intent);
			}
		});
		et3.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				String s1 = et2.getText().toString();
				String s2 = et3.getText().toString();
				if (!s2.equals("")) {
					if (s2.equals(s1)) {
						xianshimm.setTextColor(Color.GREEN);
						xianshimm.setText("������ȷ");
					} else {
						xianshimm.setTextColor(Color.RED);
						xianshimm.setText("�������벻һ��");
					}

				}
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	OnClickListener ocl = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if (et2.getText().toString().equals(et3.getText().toString())) {
				mimayanse.setText("����һ��");
				mimayanse.setBackgroundColor(Color.GREEN);

			} else {
				mimayanse.setBackgroundColor(Color.RED);
				mimayanse.setText("���벻һ��");
			}

		}
	};
	OnClickListener ocl2 = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			a++;
			if (a % 2 == 0) {
				et2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);// ��ʾ�����ı�
				et3.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
				xianshimm.setText("��������");
			} else {
				et2.setInputType(InputType.TYPE_CLASS_TEXT
						| InputType.TYPE_TEXT_VARIATION_PASSWORD);
				et3.setInputType(InputType.TYPE_CLASS_TEXT
						| InputType.TYPE_TEXT_VARIATION_PASSWORD);
				xianshimm.setText("��ʾ����");
			}

		}
	};
	OnClickListener ocl3 = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(Test01.this, In.class);
			startActivity(intent);
		}
	};

}
