package com.example.arrayadapter1;

import android.R;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���貼���ļ�
		String[] arr = { "�����", "��˽�", "��ɮ" };
		// ����ArrayAdapter����
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_multiple_choice, arr);
		// ���øô�����ʾ�б�
		setListAdapter(adapter);
	}

}
