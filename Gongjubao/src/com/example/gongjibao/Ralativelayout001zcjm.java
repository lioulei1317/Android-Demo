package com.example.gongjibao;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Ralativelayout001zcjm extends Activity{
	Button queren01, fanhui01;
	EditText et1, et2, et3;
	TextView  xianshimm;
	int a = 1;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.ralativelayout001zcjm);
    	queren01 = (Button) findViewById(R.id.queren01);
		fanhui01 = (Button) findViewById(R.id.fanhui01);
		et1 = (EditText) findViewById(R.id.et1);
		et2 = (EditText) findViewById(R.id.et2);
		et3 = (EditText) findViewById(R.id.et3);
		xianshimm = (TextView) findViewById(R.id.xianshimm);
		xianshimm.setOnClickListener(ocl2);
		fanhui01.setOnClickListener(ocl3);
		queren01.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Ralativelayout001zcjm.this, Ralativelayout001dljm.class);
				intent.putExtra("用户名", et1.getText().toString());
				intent.putExtra("密码", et2.getText().toString());
				startActivity(intent);
			}
		});

		et3.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				String s1 = et2.getText().toString();
				String s2 = et3.getText().toString();
				if (!s2.equals("")) {
					if (s2.equals(s1)) {
						xianshimm.setTextColor(Color.GREEN);
						xianshimm.setText("密码正确");
					} else {
						xianshimm.setTextColor(Color.RED);
						xianshimm.setText("两次密码不一致");
					}

				}
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}
		});
    	
    }
     OnClickListener ocl2 = new OnClickListener() {
 		@Override
 		public void onClick(View arg0) {
 			// TODO Auto-generated method stub
 			a++;
 			if (a % 2 == 0) {
 				et2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);// 显示密码文本
 				et3.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
 				xianshimm.setText("隐藏密码");
 			} else {
 				et2.setInputType(InputType.TYPE_CLASS_TEXT
 						| InputType.TYPE_TEXT_VARIATION_PASSWORD);
 				et3.setInputType(InputType.TYPE_CLASS_TEXT
 						| InputType.TYPE_TEXT_VARIATION_PASSWORD);
 				xianshimm.setText("显示密码");
 			}

 		}
 	};
 	OnClickListener ocl3 = new OnClickListener() {

 		@Override
 		public void onClick(View arg0) {
 			// TODO Auto-generated method stub
 			Intent intent = new Intent(Ralativelayout001zcjm.this, Ralativelayout001dljm.class);
 			startActivity(intent);
 		}
 	};
}
