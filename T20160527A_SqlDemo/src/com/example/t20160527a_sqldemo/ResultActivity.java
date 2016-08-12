package com.example.t20160527a_sqldemo;

import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ResultActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup);
		ListView listview = (ListView) findViewById(R.id.listview021);
		Intent intent = getIntent();
		// ��ȡ��intent��Я��������
		Bundle data = intent.getExtras();
		// ��Bundle���ݰ���ȡ������
		List<Map<String, String>> list = (List<Map<String, String>>) data
				.getSerializable("data");
		// ��List��װ��SimpleAdapter
		SimpleAdapter adapter = new SimpleAdapter(ResultActivity.this, list,
				R.layout.line, new String[] { "word", "detail" }, new int[] {
						R.id.word, R.id.detail });
		listview.setAdapter(adapter);
	}
}
