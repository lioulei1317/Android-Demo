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
		// ����һ������
		String[] arr1 = { "�����", "��˽�", "ţħ��" };
		// �������װΪArrayAdapter
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
				R.layout.listview00200, arr1);
		//ΪListView����Adapter
		list1.setAdapter(adapter1);
		
		
		
		ListView list2 = (ListView) findViewById(R.id.list2);
		// ����һ������
		String[] arr2 = { "Java", "Android", "Sping" };
		// �������װΪArrayAdapter
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
				R.layout.listview00201, arr2);
		//ΪListView����Adapter
		list2.setAdapter(adapter2);
	}

}
