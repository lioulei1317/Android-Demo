package com.example.t20160428b_datepickerdialog;

import java.util.Calendar;
import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class DatePickerDialog001 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datepickerdialog001);
		Button dateBn = (Button) findViewById(R.id.dateBn);
		Button timeBn = (Button) findViewById(R.id.timeBn);
		// 为"设置日期"按钮绑定监听器
		dateBn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View source) {
				Calendar c = Calendar.getInstance();
				// 直接创建一个DatePickerDialog对话框实例，并将它显示出来
				new DatePickerDialog(DatePickerDialog001.this,
				// 绑定监听器
						new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker dp, int year,
									int month, int dayOfMonth) {
								EditText show = (EditText) findViewById(R.id.show);
								show.setText("您选择了：" + year + "年" + (month + 1)
										+ "月" + dayOfMonth + "日");
							}
						}
						// 设置初始日期
						, c.get(Calendar.YEAR)
						, c.get(Calendar.MONTH)
						, c.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
		//为"设置时间"按钮绑定监听器
		timeBn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar c=Calendar.getInstance();
				//创建一个TimePickerDialog实例，并把它显示出来
				new TimePickerDialog(DatePickerDialog001.this, 
						// 绑定监听器
						new TimePickerDialog.OnTimeSetListener() {
					
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						// TODO Auto-generated method stub
						EditText show=(EditText) findViewById(R.id.show);
						show.setText("您选择了："+hourOfDay+"时"+minute+"分");
					}
				}
				//设置初始时间
				,c.get(Calendar.HOUR_OF_DAY)
				,c.get(Calendar.MINUTE)
				//true表示采用24小时制
				, true).show();
			}
		});
	}


}
