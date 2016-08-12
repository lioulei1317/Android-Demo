package com.example.t20160527a_sqldemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
	final String CREATE_TABLE_SQL = "create table dict(_id integer primary "
			+ "key autoincrement , word , detail)";

	public MyDatabaseHelper(Context context, String name, int version) {
		super(context, name, null, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// ��һ��ʹ�����ݿ�ʱ�Զ�����
		db.execSQL(CREATE_TABLE_SQL);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		System.out.println("--------------onUpdate  Called-------------"
				+ oldVersion + "----------->" + newVersion);
	}

}
