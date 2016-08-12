package com.example.t20160422a;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class Autocomplete001 extends Activity {
	AutoCompleteTextView auto;
	MultiAutoCompleteTextView mauto;
	String[] phone = { "小何", "小黑", "小白", "小崔", "小小", "大大" ,"aaaa","xxx","ooo"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.auotocomplete001);
		auto = (AutoCompleteTextView) findViewById(R.id.auto);
		mauto = (MultiAutoCompleteTextView) findViewById(R.id.mauto);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, phone);
		auto.setAdapter(adapter);
		mauto.setAdapter(adapter);
		//为MultiAutoCompleteTextView添加分隔符
		mauto.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
	}

}
