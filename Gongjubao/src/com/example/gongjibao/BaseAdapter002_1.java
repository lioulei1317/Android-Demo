package com.example.gongjibao;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class BaseAdapter002_1 extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.baseadapter002);
		ListView lv=(ListView) findViewById(R.id.baselv001);
		ArrayList<String> listdata=new ArrayList<String>();
		for (int i = 0; i < 400; i++) {
			listdata.add("Item"+i);
		}
		lv.setAdapter(new BaseAdapter002_2(this,listdata));
	}

}
