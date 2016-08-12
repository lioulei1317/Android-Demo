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
		// 创建或打开数据库（此处需要使用绝对路径）
		// db =
		// SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()
		// + "/my.db3", null);

		// 第一个参数————为数据库的名字，string类型。
		// 第二个参数————为常量，如下所示
		// 常量 含义
		// MODE_PRIVATE 默认模式，值为0，文件只可以被调用该方法的应用程序访问
		// MODE_WORLD_READABLE 所有的应用程序都具有对该文件读的权限。
		// MODE_WORLD_WRITEABLE 所有的应用程序都具有对该文件写的权限。
		// 第三个参数————当query方法被调用时，用来实例化cursor，通常为null
		// 自动在database文件夹下打开或创建相应的数据库
		db = Sql001.this.openOrCreateDatabase("my.db3", Context.MODE_PRIVATE,
				null);
		listView = (ListView) findViewById(R.id.sqlListView);
		bn = (Button) findViewById(R.id.sqlbtn);
		bn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 获取用户输入
				String title = ((EditText) findViewById(R.id.sqlEditText01))
						.getText().toString();
				String content = ((EditText) findViewById(R.id.sqlEditText02))
						.getText().toString();

				try {
					insertData(db, title, content);
					// Cursor cursor =
					// db.rawQuery("select * from personwhere name like ?and age=?",
					// new String[]{"%iteedu%", "4"});
					// rawQuery()执行带占位符的SQL查询
					Cursor cursor = db.rawQuery("select*from news_inf", null);
					inflateList(cursor);
				} catch (SQLiteException se) {
					// 执行DDL创建数据表
					db.execSQL("create table news_inf(_id varchar(32)"
							+ " primary key," + " news_title varchar(50),"
							+ " news_content varchar(255))");
					// 执行insert语句插入数据
					insertData(db, title, content);
					// 执行查询
					Cursor cursor = db.rawQuery("select * from news_inf", null);
					inflateList(cursor);
				}
			}
		});
	}

	private void insertData(SQLiteDatabase db, String title, String content) {
		// UUID uuid = UUID.randomUUID();
		// 随机生成32位进制的_id号
		String uuidStr = UUID.randomUUID().toString();
		System.out.println(uuidStr);
		// 去除_id号中间的短横线,只保留数字
		uuidStr = uuidStr.substring(0, 8) + uuidStr.substring(9, 13)
				+ uuidStr.substring(14, 18) + uuidStr.substring(19, 23)
				+ uuidStr.substring(24);
		System.out.println("==================" + uuidStr);
		// 执行插入语句(将用户输入的数据插入到表格"news_inf"中相应的位置)
		db.execSQL("insert into news_inf values(?,?,?)", new String[] {
				uuidStr, title, content });

	}

	private void inflateList(Cursor cursor) {
		// 填充SimpleCursorAdapter(上下文,列表项布局,查询结果,结果中需要显示的目录,将内容放到相应的组件上)
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(Sql001.this,
				R.layout.sql002, cursor, new String[] { "news_title",
						"news_content" }, new int[] { R.id.my_title,
						R.id.my_content },
				// 实时更新数据库数据
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		// 显示数据
		listView.setAdapter(adapter);

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// 退出程序时关闭SQLiteDatabase
		if (db != null && db.isOpen()) {
			db.close();
		}
	}

}
