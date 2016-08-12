package com.example.gongjibao;

import java.util.Set;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class Intent002 extends Activity{
	EditText show,cate;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intent002);
		show=(EditText) findViewById(R.id.intentet001);
		cate=(EditText) findViewById(R.id.intentet002);
		//获取该Activity对应的Intent的Action属性
		String action=getIntent().getAction();
		//显示Action属性
		show.setText("Action为："+action);
		//获取该Activity对应的Intent的Category属性
		Set<String> cates=getIntent().getCategories();
		//显示Action属性
		cate.setText("Category属性为："+cates);
	}

}
