package com.example.gongjibao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class SimpleAdapter001 extends Activity {
	private String[] names = new String[] { "����", "·��", "����", "����" };
	private String[] descs = new String[] { "���ǿɰ��İ���", "��Ϊ������������", "��Ϊ��Ӱ����",
			"��Ϊ���Ӿ�" };
	private int[] imageIds = new int[] { R.drawable.ali, R.drawable.haizei,
			R.drawable.mingren, R.drawable.mengchong1 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simpleadapter001);
		// ����һ��List���ϣ�List���ϵ�Ԫ����Map
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < names.length; i++) {
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("header", imageIds[i]);
			listItem.put("personName", names[i]);
			listItem.put("desc", descs[i]);
			listItems.add(listItem);
		}
		// ����һ��SimpleAdapterk
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
				R.layout.simpleadapter002, new String[] { "personName",
						"header", "desc" }, new int[] { R.id.name, R.id.header,
						R.id.desc });
		ListView list = (ListView) findViewById(R.id.mylist);
		// ΪListView����Adapter
		list.setAdapter(simpleAdapter);
		// ΪListView���б�����¼����¼�������
		list.setOnItemClickListener(new OnItemClickListener() {
			// ��position����ʱ�����÷�����
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				System.out.println(names[position] + "�������");
			}
		});
	}
}