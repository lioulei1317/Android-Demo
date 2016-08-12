package com.example.t20160420c;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {
	ListView listview;
	Button bn1;
	Info info;
	List<Info> list;
	Myadapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listview = (ListView) findViewById(R.id.lv);
		bn1 = (Button) findViewById(R.id.bn1);
		bn1.setOnClickListener(ocl);
		// 当listview里面没有数据的时候显示view
		listview.setEmptyView(findViewById(R.id.tv));
		adapter = new Myadapter(this, list);
		listview.setAdapter(adapter);
		
		// 对listView绑定点击事件
		listview.setOnItemClickListener(onItemClick);
	
		
		
	}

	OnClickListener ocl=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			System.out.println("-------------------------------------------");
			data();
			adapter.addInfoList(list);
		}
	};

	OnItemClickListener onItemClick = new OnItemClickListener() {
		/*
		 * AdapterView 当前的AdapterView对象，你这里就是ListView View
		 * AdapterView所绑定的Adapter的getView方法返回的View int 当前被点击的条目的索引号 long
		 * AdapterView所绑定的Adapter的getItemId返回的值
		 */
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
//			info = (Info) parent.getItemAtPosition(position);
//			Intent intent = new Intent(MainActivity.this, Myadapter.class);
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, Listlayout.class);
			intent.putExtra("info", info);
			startActivity(intent);

		}
	};

	private void data() {
		// TODO Auto-generated method stub
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String time = sdf.format(new Date(System.currentTimeMillis()));
		list = new ArrayList<Info>();
		for (int i = 0; i < 10; i++) {
			info = new Info();
			info.setImage(R.drawable.ic_launcher);
			info.setName("name" + i);
			info.setContext("第" + (i + 1) + "条数据");
			info.setTime("2016-4-20");
			info.setCount(10);
			list.add(info);
		}
	}

}
