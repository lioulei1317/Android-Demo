package com.example.t20160527a_sqldemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	MyDatabaseHelper dbHelper;
	Button insert;
	Button search;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// ����MyDatabaseHelper����ָ�����ݿ�汾Ϊ1���˴�ʹ�����·�����ɣ�
		// ���ݿ��ļ��Զ��ᱣ���ڳ���������ļ��е�databasesĿ¼�¡�
		dbHelper = new MyDatabaseHelper(this, "myDict.db3", 1);
		insert = (Button) findViewById(R.id.Btninsert);
		search = (Button) findViewById(R.id.Btnsearch);
		insert.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ��ȡ�û�����
				String word = ((EditText) findViewById(R.id.EdiTextword))
						.getText().toString();
				String detail = ((EditText) findViewById(R.id.EdiTextdetail))
						.getText().toString();
				// �������ʼ�¼
				insertData(dbHelper.getReadableDatabase(), word, detail);
				// ��ʾ��ʾ��Ϣ
				Toast.makeText(MainActivity.this, "������ʳɹ���", 8000).show();
			}
		});
		search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ��ȡ�û�����
				String key = ((EditText) findViewById(R.id.EdiTextkey))
						.getText().toString();
				// ִ�в�ѯ
				Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
						// ��ʼ��ѯ:"%"+x+"%"��ʾ���������ݵ����Ҷ�����ģ����ѯ,��ֻ���ұ���%��ʾֻģ����ѯ�ҷ�
						"select*from dict where word like ? or detail like ?",
						new String[] { "%" + key + "%", "%" + key + "%" });

				// ����һ��Bundle����
				Bundle data = new Bundle();
				data.putSerializable("data", converCursorToList(cursor));
				// ����һ��Intent
				Intent intent = new Intent(MainActivity.this,
						ResultActivity.class);
				intent.putExtras(data);
				// ����Activity
				startActivity(intent);

			}
		});

	}

	protected ArrayList<Map<String, String>> converCursorToList(Cursor cursor) {
		ArrayList<Map<String, String>> result = new ArrayList<Map<String, String>>();
		// ����Cursor�����
		while (cursor.moveToNext()) {
			// ��������е����ݴ���ArrayList��
			Map<String, String> map = new HashMap<String, String>();
			// ȡ����ѯ��¼�е�2�С���3�е�ֵ
			map.put("word", cursor.getString(1));
			map.put("detail", cursor.getString(2));
			result.add(map);

		}

		return result;

	}

	private void insertData(SQLiteDatabase db, String word, String detail) {
		// ִ�в������
		db.execSQL("insert into dict values(null,?,?)", new String[] { word,
				detail });
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// �˳�����ʱ�ر�MyDatabaseHelper���SQLiteDatabase
		if (dbHelper != null) {
			dbHelper.close();
		}
	}

}
