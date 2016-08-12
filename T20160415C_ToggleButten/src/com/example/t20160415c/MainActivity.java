package com.example.t20160415c;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	ToggleButton tlgg;
	Switch swiotch_;
	LinearLayout ll;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tlgg=(ToggleButton) findViewById(R.id.togglebn);
		swiotch_=(Switch) findViewById(R.id.swith);
		ll=(LinearLayout) findViewById(R.id.ll);
		tlgg.setOnCheckedChangeListener(occl);
		swiotch_.setOnCheckedChangeListener(occl);
	}
	OnCheckedChangeListener occl=new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			if(isChecked){
				//设置lineatlayout垂直排列
				ll.setOrientation(1);
			}else{
				//设置lineaterlayout水平排列
				ll.setOrientation(0);
			}
		}
	};


}
