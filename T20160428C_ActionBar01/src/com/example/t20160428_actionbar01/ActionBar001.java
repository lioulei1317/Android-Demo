package com.example.t20160428_actionbar01;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class ActionBar001 extends Activity {
	ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actionbar001);
		// ��ȡactivity��actionbar
		actionBar = getActionBar();
	}

	public void show(View v) {
		// ��ʾactionBar
		actionBar.show();
	}

	public void hide(View v) {
		// ����actionBar
		actionBar.hide();
	}

}
