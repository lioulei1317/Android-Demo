package com.example.t20160429b_actionhome01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActionHome002 extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Button butten=new Button(this);
		butten.setText("��������������");
		setContentView(butten);
		butten.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ActionHome002.this,ActionHome003.class);
				startActivity(intent);
			}
		});
	}

}
