package com.example.test;

import android.os.Bundle;
import android.app.Activity;
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
		bn = (Button) findViewById(R.id.bn);
		et1 = (EditText) findViewById(R.id.et1);
		et2 = (EditText) findViewById(R.id.et2);
		bn.setOnClickListener(ocl);
	}

	OnClickListener ocl = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if ("abc".equals(et1.getText().toString())
					&& "123".equals(et2.getText().toString())) {
				// ��ȡ�ı�������ݣ���ת��ΪtoString����
				Toast.makeText(MainActivity.this, "��¼�ɹ�", Toast.LENGTH_SHORT)
						.show();//�������
			} else if (et1.getText().toString().equals("")) {
				Toast.makeText(MainActivity.this, "�������û���", Toast.LENGTH_SHORT)
						.show();
			} else if ("abc".equals(et1.getText().toString())
					&& et2.getText().toString().equals("")) {
				Toast.makeText(MainActivity.this, "����������", Toast.LENGTH_SHORT)
						.show();
			} else {
				Toast.makeText(MainActivity.this, "�û������������",
						Toast.LENGTH_SHORT).show();
			}
		}
	};
}
