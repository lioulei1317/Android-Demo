package com.example.gongjibao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ListViewAdapter001_01 extends Activity {
	Button submit;
	ListView list;
	List<ListViewAdapter001_02> list1;
	ListViewAdapter001_02 info;
	ListViewAdapter001_03 adapter;
	int number=0;
	int[] tupian = { R.drawable.qq, R.drawable.tuian1, R.drawable.tupain7,
			R.drawable.tupian10, R.drawable.tupian2, R.drawable.tupian3,
			R.drawable.tupian4, R.drawable.tupian5, R.drawable.tupian6,
			R.drawable.tupian8, R.drawable.tupian9 };

	// ListViewAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listviewadapter001);
		// list = (ListView) findViewById(R.id.listViewId);
		list = (ListView) findViewById(R.id.listViewId);
		submit = (Button) findViewById(R.id.buttonId);
		submit.setOnClickListener(click);
		adapter = new ListViewAdapter001_03(this, list1);
		// ��listview����û�����ݵ�ʱ����ʾview
		list.setEmptyView(findViewById(R.id.tv_emt));
		list.setAdapter(adapter);
		// ��listView�󶨵���¼�
		list.setOnItemClickListener(onItemClick);
	}

	OnClickListener click = new OnClickListener() {

		@Override
		public void onClick(View v) {
			data();
			adapter.addAllInfo(list1);
			number++;
		
		}
	};

	OnItemClickListener onItemClick = new OnItemClickListener() {
		/*
		 * AdapterView ��ǰ��AdapterView�������������ListView View
		 * AdapterView���󶨵�Adapter��getView�������ص�View int ��ǰ���������Ŀ�������� long
		 * AdapterView���󶨵�Adapter��getItemId���ص�ֵ
		 */
		@Override
		public void onItemClick(AdapterView<?> parent, View arg1, int position,
				long itemID) {
			info = (ListViewAdapter001_02) parent.getItemAtPosition(position);
			Intent intent = new Intent();
			intent.setClass(ListViewAdapter001_01.this, ListViewAdapter001_04.class);
			intent.putExtra("info", info);
			startActivity(intent);

		}
	};

	private void data() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(new Date(System.currentTimeMillis()));
		list1 = new ArrayList<ListViewAdapter001_02>();
		for (int i = 0; i < 1; i++) {
			info = new ListViewAdapter001_02();
			info.setImg(tupian[(int) (Math.random()* 11)]);
			info.setTime(time);
			info.setTitle("��" + (number + 1) + "λŮ�����ұ��!");
			info.setContext("�Ҿܾ��˵�" + (number + 1) + "λŮ��!");
			info.setCount((int) (Math.random() * 10000) + 1);
			list1.add(info);
		}
	}
}
