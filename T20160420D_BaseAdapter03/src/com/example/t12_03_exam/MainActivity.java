package com.example.t12_03_exam;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	Button submit;
	ListView list;
	List<Info> list1;
	protected Info info;
	public ListViewAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		list = (ListView) findViewById(R.id.listViewId);
		submit = (Button) findViewById(R.id.buttonId);
		submit.setOnClickListener(click);
		adapter = new ListViewAdapter(this, list1);
		// 当listview里面没有数据的时候显示view
		list.setEmptyView(findViewById(R.id.tv_emt));
		list.setAdapter(adapter);
		// 对listView绑定点击事件
		list.setOnItemClickListener(onItemClick);
	}

	OnClickListener click = new OnClickListener() {

		@Override
		public void onClick(View v) {
			data();
			adapter.addAllInfo(list1);
		}
	};
	OnItemClickListener onItemClick = new OnItemClickListener() {
/*
 * AdapterView 当前的AdapterView对象，你这里就是ListView
 * View AdapterView所绑定的Adapter的getView方法返回的View
 * int  当前被点击的条目的索引号
 * long AdapterView所绑定的Adapter的getItemId返回的值
 */
		@Override
		public void onItemClick(AdapterView<?> parent, View arg1, int position,
				long itemID) {
			info = (Info) parent.getItemAtPosition(position);
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, Listlayout_Activity.class);
			intent.putExtra("info", info);
			startActivity(intent);

		}
	};

	private void data() {
		list1 = new ArrayList<Info>();
		Info info;
		for (int i = 0; i < 12; i++) {
			info = new Info();
			info.setImg(R.drawable.h_2);
			info.setTime("2013_4_5");
			info.setTitle("测试" + i);
			info.setContext("第" + (i + 1) + "条数据");
			info.setCount(10);
			list1.add(info);
		}
	}
}
