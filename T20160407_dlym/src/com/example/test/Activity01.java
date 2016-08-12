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
		denglu.setOnClickListener(oc1);// setOnClickListener给button绑定监听器的方法
	}

	OnClickListener oc1 = new OnClickListener() {// 创建一个监听器

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if ("admin".equals(yhm_sr.getText().toString())
					&& "admin".equals(mima_sr.getText().toString())) {// 判断用户名和密码是否输入正确
				Toast.makeText(Activity01.this, "登录成功", Toast.LENGTH_SHORT)
						.show();// ①位置②显示文字③显示时长
			} else if ("".equals(yhm_sr.getText().toString())
					&& "admin".equals(mima_sr.getText().toString())) {
				Toast.makeText(Activity01.this, "用户名不能为空", Toast.LENGTH_SHORT)
						.show();
			} else if ("admin".equals(yhm_sr.getText().toString())
					&& "".equals(mima_sr.getText().toString())) {
				Toast.makeText(Activity01.this, "密码不能为空", Toast.LENGTH_SHORT)
						.show();
			} else if (yhm_sr.getText().toString().equals("")
					&& mima_sr.getText().toString().equals("")) {
				Toast.makeText(Activity01.this, "用户名和密码不能为空",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(Activity01.this, "用户名或密码输入错误",
						Toast.LENGTH_SHORT).show();
			}
		}
	};
}
