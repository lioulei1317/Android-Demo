package com.example.gongjibao;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Intent001 extends Activity {
	// 定义一个Action常量
	public static final String MAIN_ACTION = "one";
	// 定义一个Category常量
	public static final String MAIN_CATEGORY = "two";
	Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intent001);
		btn = (Button) findViewById(R.id.intentbtn001);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				// 设置Action属性
				intent.setAction(Intent001.MAIN_ACTION);
				// 添加Category属性
				intent.addCategory(Intent001.MAIN_CATEGORY);
				startActivity(intent);
			}
		});

	}

}
