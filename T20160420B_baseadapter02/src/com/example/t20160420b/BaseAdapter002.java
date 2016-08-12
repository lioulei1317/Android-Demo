package com.example.t20160420b;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ListView;

public class BaseAdapter002 extends Activity {
	BaseAdapter003 base;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base_adapter002);
		ListView lv=(ListView) findViewById(R.id.lv);
		ArrayList<String> listdata=new ArrayList<String>();
		for (int i = 0; i < 400; i++) {
			listdata.add("Item"+i);
		}
		lv.setAdapter(new BaseAdapter003(this,listdata));
	}



}
