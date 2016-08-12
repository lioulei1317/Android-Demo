package com.sky.t09_03_simpleadaptertest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends Activity {
	private String[] names = new String[] { "阿狸", "路飞", "鸣人", "白兔" };
	private String[] descs = new String[] { "我是可爱的阿狸", "成为海贼王的男人", "成为火影的人",
			"成为兔子精" };
	private int[] imageIds = new int[] { R.drawable.ali, R.drawable.haizei,
			R.drawable.mingren, R.drawable.mengchong1 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 创建一个List集合，List集合的元素是Map
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < names.length; i++) {
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("header", imageIds[i]);
			listItem.put("personName", names[i]);
			listItem.put("desc", descs[i]);
			listItems.add(listItem);
		}
		// 创建一个SimpleAdapterk
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
				R.layout.simple_item, new String[] { "personName", "header",
						"desc" },
				new int[] { R.id.name, R.id.header, R.id.desc });
		ListView list = (ListView) findViewById(R.id.mylist);
		// 为ListView设置Adapter
		list.setAdapter(simpleAdapter);
		// 为ListView的列表项单击事件绑定事件监听器
		list.setOnItemClickListener(new OnItemClickListener() {
			// 第position项被点击时激发该方法。
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				System.out.println(names[position] + "被点击了");
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
