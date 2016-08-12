package com.example.gongjibao;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListView002 extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview002);
		ListView list1 = (ListView) findViewById(R.id.list1);
		// 定义一个数组
		String[] arr1 = { "孙悟空", "猪八戒", "牛魔王" };
		// 将数组包装为ArrayAdapter
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
				R.layout.listview00200, arr1);
		//为ListView设置Adapter
		list1.setAdapter(adapter1);
		
		
		
		ListView list2 = (ListView) findViewById(R.id.list2);
		// 定义一个数组
		String[] arr2 = { "Java", "Android", "Sping" };
		// 将数组包装为ArrayAdapter
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
				R.layout.listview00201, arr2);
		//为ListView设置Adapter
		list2.setAdapter(adapter2);
	}

}
