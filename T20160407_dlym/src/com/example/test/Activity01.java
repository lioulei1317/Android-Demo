package com.example.test;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity01 extends Activity {
	Button denglu;
	EditText yhm_sr, mima_sr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		denglu = (Button) findViewById(R.id.denglu);
		yhm_sr = (EditText) findViewById(R.id.yhm_sr);
		mima_sr = (EditText) findViewById(R.id.mima_sr);
		denglu.setOnClickListener(oc1);// setOnClickListener��button�󶨼������ķ���
	}

	OnClickListener oc1 = new OnClickListener() {// ����һ��������

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if ("admin".equals(yhm_sr.getText().toString())
					&& "admin".equals(mima_sr.getText().toString())) {// �ж��û����������Ƿ�������ȷ
				Toast.makeText(Activity01.this, "��¼�ɹ�", Toast.LENGTH_SHORT)
						.show();// ��λ�â���ʾ���֢���ʾʱ��
			} else if ("".equals(yhm_sr.getText().toString())
					&& "admin".equals(mima_sr.getText().toString())) {
				Toast.makeText(Activity01.this, "�û�������Ϊ��", Toast.LENGTH_SHORT)
						.show();
			} else if ("admin".equals(yhm_sr.getText().toString())
					&& "".equals(mima_sr.getText().toString())) {
				Toast.makeText(Activity01.this, "���벻��Ϊ��", Toast.LENGTH_SHORT)
						.show();
			} else if (yhm_sr.getText().toString().equals("")
					&& mima_sr.getText().toString().equals("")) {
				Toast.makeText(Activity01.this, "�û��������벻��Ϊ��",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(Activity01.this, "�û����������������",
						Toast.LENGTH_SHORT).show();
			}
		}
	};
}
