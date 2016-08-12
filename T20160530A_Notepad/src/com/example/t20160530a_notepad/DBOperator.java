package com.example.t20160530a_notepad;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBOperator {
	/**
	 * 查询 所有的 日记
	 */
	public static List<NotePadInfo> queryALLNoteInfo(SQLiteDatabase db) {
		List<NotePadInfo> list = new ArrayList<NotePadInfo>();
		Cursor cursor = db.rawQuery("select * from " + DBConfig.TB_NOTEINFO,
				null);
		while (cursor.moveToNext()) {
			NotePadInfo info = new NotePadInfo();
			int index = 0;
			info.setId(cursor.getInt(index++));
			info.setTitle(cursor.getString(index++));
			info.setContent(cursor.getString(index++));
			info.setDate(cursor.getString(index));
			list.add(info);
			info = null;
		}
		cursor.close();
		return list;

	}

	public static void addNotepadInfo(SQLiteDatabase db, NotePadInfo info) {
		// 在数据库中插入数据的时候，首先应该有一个ContentValues的对象
		ContentValues values = new ContentValues();
		values.put(DBConfig.C_TITLE, info.getTitle());
		values.put(DBConfig.C_CONTENT, info.getContent());
		values.put(DBConfig.C_DATE, info.getDate());
		db.insert(DBConfig.TB_NOTEINFO, null, values);
	}

	public static void updateNotepadInfo(SQLiteDatabase db, NotePadInfo info) {
		ContentValues values = new ContentValues();
		values.put(DBConfig.C_TITLE, info.getTitle());
		values.put(DBConfig.C_CONTENT, info.getContent());
		values.put(DBConfig.C_DATE, info.getDate());
		db.update(DBConfig.TB_NOTEINFO, values, DBConfig.C_ID + "=?",
				new String[] { "" + info.getId() });
	}

	public static void deleteNotepadInfo(SQLiteDatabase db, NotePadInfo info) {
		db.delete(DBConfig.TB_NOTEINFO, DBConfig.C_ID + "=?", new String[] { ""
				+ info.getId() });
	}
}
