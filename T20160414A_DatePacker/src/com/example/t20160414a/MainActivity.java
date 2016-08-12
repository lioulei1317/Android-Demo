package com.example.t20160414a;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class MainActivity extends Activity {
	DatePicker date;
	TimePicker time;
	EditText edt;
	int year, month, day, hour, minute;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		date = (DatePicker) findViewById(R.id.datepicker);
		time = (TimePicker) findViewById(R.id.timepicker);
		edt = (EditText) findViewById(R.id.edt);

		// 获取当前年月日，小时，分钟
		Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		hour = c.get(Calendar.HOUR);
		minute = c.get(Calendar.MINUTE);

		// 初始化DatePicker组件，初始化时指定其监听器
		date.init(year, month, day, new OnDateChangedListener() {

			@Override
			public void onDateChanged(DatePicker arg0, int year, int month,
					int day) {
				// TODO Auto-generated method stub
				MainActivity.this.year = year;
				MainActivity.this.month = month;
				MainActivity.this.day = day;
				show(year, month, day, hour, minute);
			}
		});
		time.setOnTimeChangedListener(new OnTimeChangedListener() {

			@Override
			public void onTimeChanged(TimePicker arg0, int hour, int mintue) {
				// TODO Auto-generated method stub
				MainActivity.this.hour = hour;
				MainActivity.this.minute = mintue;
				show(year, month, day, hour, mintue);
			}
		});
	}

	// 定义一个编辑框中显示所选的日期时间的方法
	private void show(int year, int mouth, int day, int hour, int mintue) {
		// TODO Auto-generated method stub
		edt.setText("您当前所选择的时间为" + year + "年" + (mouth+1) + "月" + day + "日" + hour
				+ "小时" + mintue + "分钟");

	}

}
