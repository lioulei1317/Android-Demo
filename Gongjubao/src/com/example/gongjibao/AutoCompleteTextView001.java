package com.example.gongjibao;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class AutoCompleteTextView001 extends Activity{
	AutoCompleteTextView auto;
	MultiAutoCompleteTextView mauto;
	String[] phone = { "С��", "С��", "С��", "С��", "СС", "���" ,"aaaa","xxx","ooo"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.autocompletetextview001);
		auto = (AutoCompleteTextView) findViewById(R.id.auto);
		mauto = (MultiAutoCompleteTextView) findViewById(R.id.mauto);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, phone);
		auto.setAdapter(adapter);
		mauto.setAdapter(adapter);
		//ΪMultiAutoCompleteTextView��ӷָ���
		mauto.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
	}
}
