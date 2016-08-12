package com.example.t20160530a_notepad;

public class DBConfig {
	public static final String DB_NAME ="note.db";
	public static final int DB_VERSION = 1;
	public static final String CREATE_TABLE_HEAD = "create table ";
	public static final String CREATE_ID =" _id integer primary key autoincrement,";
	//tb_noteinfo (_id,_title,_content,_date)
	public static final String C_ID ="_id";
	public static final String TB_NOTEINFO= "tb_noteInfo";
	public static final String C_TITLE = "_title";
	public static final String C_CONTENT = "_content";
	public static final String C_DATE = "_date";
}
