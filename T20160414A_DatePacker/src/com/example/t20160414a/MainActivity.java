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

		// ��ȡ��ǰ�����գ�Сʱ������
		Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		hour = c.get(Calendar.HOUR);
		minute = c.get(Calendar.MINUTE);

		// ��ʼ��DatePicker�������ʼ��ʱָ���������
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

	// ����һ���༭������ʾ��ѡ������ʱ��ķ���
	private void show(int year, int mouth, int day, int hour, int mintue) {
		// TODO Auto-generated method stub
		edt.setText("����ǰ��ѡ���ʱ��Ϊ" + year + "��" + (mouth+1) + "��" + day + "��" + hour
				+ "Сʱ" + mintue + "����");

	}

}
