package com.example.gongjibao;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActionHome001 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Button butten=new Button(this);
		butten.setText("�����ڶ�������");
		setContentView(butten);
		butten.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					Intent intent=new Intent(ActionHome001.this,ActionHome002.class);
					startActivity(intent);
			}
		});
	}



}
