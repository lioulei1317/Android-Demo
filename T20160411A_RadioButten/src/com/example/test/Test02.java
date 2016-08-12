package com.example.test;
import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class Test02 extends Activity {

	CheckBox cb1, cb2, cb3, cb4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test3);
		cb1 = (CheckBox) findViewById(R.id.cb1);
		cb2 = (CheckBox) findViewById(R.id.cb2);
		cb3 = (CheckBox) findViewById(R.id.cb3);
		cb4 = (CheckBox) findViewById(R.id.cb4);
		cb1.setOnCheckedChangeListener(ocl);
		cb2.setOnCheckedChangeListener(ocl);
		cb3.setOnCheckedChangeListener(ocl);
		cb4.setOnCheckedChangeListener(ocl);
	}

	OnCheckedChangeListener ocl = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
			// TODO Auto-generated method stub
			switch (arg0.getId()) {
			case R.id.cb1:
				if (arg1 == true) {
					Toast.makeText(Test02.this, "你选择了大德大威天龙宝宝",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(Test02.this, "你取消了了大德大威天龙宝宝",
							Toast.LENGTH_SHORT).show();
				}

				break;
			case R.id.cb2:
				if (arg1 == true) {
					Toast.makeText(Test02.this, "你选择了哈哈",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(Test02.this, "你取消了了哈哈",
							Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.cb3:
				if (arg1 == true) {
					Toast.makeText(Test02.this, "你选择了宝宝",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(Test02.this, "你取消了了宝宝",
							Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.cb4:
				if (arg1 == true) {
					cb1.setChecked(true);
					cb2.setChecked(true);
					cb3.setChecked(true);
					Toast.makeText(Test02.this, "全选", Toast.LENGTH_SHORT)
							.show();
				} else {
					cb1.setChecked(false);
					cb2.setChecked(false);
					cb3.setChecked(false);
					Toast.makeText(Test02.this, "全部取消",
							Toast.LENGTH_SHORT).show();
				}

			default:
				break;
			}
		}
	};

}
