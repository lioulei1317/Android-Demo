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
		//��ȡ��Activity��Ӧ��Intent��Action����
		String action=getIntent().getAction();
		//��ʾAction����
		show.setText("ActionΪ��"+action);
		//��ȡ��Activity��Ӧ��Intent��Category����
		Set<String> cates=getIntent().getCategories();
		//��ʾAction����
		cate.setText("Category����Ϊ��"+cates);
	}

}
