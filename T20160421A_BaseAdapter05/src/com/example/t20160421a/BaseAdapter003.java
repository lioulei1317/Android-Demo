package com.example.t20160421a;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class BaseAdapter003 extends Activity {
	ListView listview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.baseadapter003);
		listview=(ListView) findViewById(R.id.lv);
		List<String> list=new ArrayList<String>();
		for (int i = 0; i <100; i++) {
			list.add("item"+i);
		}
		listview.setAdapter(new BaseAdapter004(this,list));
	}


}
