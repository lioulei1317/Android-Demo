package com.sky.t08_03_listviewtwo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView list1 = (ListView) findViewById(R.id.list1);
		// ����һ������
		String[] arr1 = { "�ܲ�", "��Ȩ", "����" };
		// �������װΪArrayAdapter
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
				R.layout.array_item, arr1);
		list1.setAdapter(adapter1);

		ListView list2 = (ListView) findViewById(R.id.list2);
		String[] arr2 = { "Teemo", "Ashe", "VARUS" };
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
				R.layout.checked_item, arr2);
		list2.setAdapter(adapter2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
