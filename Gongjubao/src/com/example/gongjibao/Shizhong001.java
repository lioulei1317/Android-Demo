package com.example.gongjibao;

import java.util.Calendar;
import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker.OnTimeChangedListener;

public class Shizhong001 extends Activity{
	DatePicker date;
	TimePicker time;
	EditText edt;
	int year, month, day, hour, minute;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shizhong001);
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
				Shizhong001.this.year = year;
				Shizhong001.this.month = month;
				Shizhong001.this.day = day;
				show(year, month, day, hour, minute);
			}
		});
		time.setOnTimeChangedListener(new OnTimeChangedListener() {

			@Override
			public void onTimeChanged(TimePicker arg0, int hour, int mintue) {
				// TODO Auto-generated method stub
				Shizhong001.this.hour = hour;
				Shizhong001.this.minute = mintue;
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
