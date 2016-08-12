package com.example.t20160530a_notepad;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	public static final String CREATE_TB_NOTEINFO = DBConfig.CREATE_TABLE_HEAD
			+ DBConfig.TB_NOTEINFO + "(" + DBConfig.CREATE_ID
			+ DBConfig.C_TITLE + " text," + DBConfig.C_CONTENT + " text,"
			+ DBConfig.C_DATE + " text )";

	public DBHelper(Context context) {
		super(context, DBConfig.DB_NAME, null, DBConfig.DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TB_NOTEINFO);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
