package com.example.t20160526a_sql;

import java.util.UUID;
import java.util.zip.Inflater;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v4.widget.CursorAdapter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Sql001 extends Activity {
	SQLiteDatabase db;
	Button bn;
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sql001);
		// ����������ݿ⣨�˴���Ҫʹ�þ���·����
		// db =
		// SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()
		// + "/my.db3", null);

		// ��һ��������������Ϊ���ݿ�����֣�string���͡�
		// �ڶ���������������Ϊ������������ʾ
		// ���� ����
		// MODE_PRIVATE Ĭ��ģʽ��ֵΪ0���ļ�ֻ���Ա����ø÷�����Ӧ�ó������
		// MODE_WORLD_READABLE ���е�Ӧ�ó��򶼾��жԸ��ļ�����Ȩ�ޡ�
		// MODE_WORLD_WRITEABLE ���е�Ӧ�ó��򶼾��жԸ��ļ�д��Ȩ�ޡ�
		// ��������������������query����������ʱ������ʵ����cursor��ͨ��Ϊnull
		// �Զ���database�ļ����´򿪻򴴽���Ӧ�����ݿ�
		db = Sql001.this.openOrCreateDatabase("my.db3", Context.MODE_PRIVATE,
				null);
		listView = (ListView) findViewById(R.id.sqlListView);
		bn = (Button) findViewById(R.id.sqlbtn);
		bn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ��ȡ�û�����
				String title = ((EditText) findViewById(R.id.sqlEditText01))
						.getText().toString();
				String content = ((EditText) findViewById(R.id.sqlEditText02))
						.getText().toString();

				try {
					insertData(db, title, content);
					// Cursor cursor =
					// db.rawQuery("select * from personwhere name like ?and age=?",
					// new String[]{"%iteedu%", "4"});
					// rawQuery()ִ�д�ռλ����SQL��ѯ
					Cursor cursor = db.rawQuery("select*from news_inf", null);
					inflateList(cursor);
				} catch (SQLiteException se) {
					// ִ��DDL�������ݱ�
					db.execSQL("create table news_inf(_id varchar(32)"
							+ " primary key," + " news_title varchar(50),"
							+ " news_content varchar(255))");
					// ִ��insert����������
					insertData(db, title, content);
					// ִ�в�ѯ
					Cursor cursor = db.rawQuery("select * from news_inf", null);
					inflateList(cursor);
				}
			}
		});
	}

	private void insertData(SQLiteDatabase db, String title, String content) {
		// UUID uuid = UUID.randomUUID();
		// �������32λ���Ƶ�_id��
		String uuidStr = UUID.randomUUID().toString();
		System.out.println(uuidStr);
		// ȥ��_id���м�Ķ̺���,ֻ��������
		uuidStr = uuidStr.substring(0, 8) + uuidStr.substring(9, 13)
				+ uuidStr.substring(14, 18) + uuidStr.substring(19, 23)
				+ uuidStr.substring(24);
		System.out.println("==================" + uuidStr);
		// ִ�в������(���û���������ݲ��뵽���"news_inf"����Ӧ��λ��)
		db.execSQL("insert into news_inf values(?,?,?)", new String[] {
				uuidStr, title, content });

	}

	private void inflateList(Cursor cursor) {
		// ���SimpleCursorAdapter(������,�б����,��ѯ���,�������Ҫ��ʾ��Ŀ¼,�����ݷŵ���Ӧ�������)
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(Sql001.this,
				R.layout.sql002, cursor, new String[] { "news_title",
						"news_content" }, new int[] { R.id.my_title,
						R.id.my_content },
				// ʵʱ�������ݿ�����
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		// ��ʾ����
		listView.setAdapter(adapter);

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// �˳�����ʱ�ر�SQLiteDatabase
		if (db != null && db.isOpen()) {
			db.close();
		}
	}

}
