package com.example.gongjibao;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Fragmentlift002 extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(this);
		tv.setText("�Ի������Activity");
		setContentView(tv);
	}
}
