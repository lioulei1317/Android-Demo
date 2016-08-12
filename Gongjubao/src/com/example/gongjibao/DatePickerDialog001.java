package com.example.gongjibao;

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
		// Ϊ"��������"��ť�󶨼�����
		dateBn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View source) {
				Calendar c = Calendar.getInstance();
				// ֱ�Ӵ���һ��DatePickerDialog�Ի���ʵ������������ʾ����
				new DatePickerDialog(DatePickerDialog001.this,
				// �󶨼�����
						new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker dp, int year,
									int month, int dayOfMonth) {
								EditText show = (EditText) findViewById(R.id.show);
								show.setText("��ѡ���ˣ�" + year + "��" + (month + 1)
										+ "��" + dayOfMonth + "��");
							}
						}
						// ���ó�ʼ����
						, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
								.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
		//Ϊ"����ʱ��"��ť�󶨼�����
		timeBn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar c=Calendar.getInstance();
				//����һ��TimePickerDialogʵ������������ʾ����
				new TimePickerDialog(DatePickerDialog001.this, 
						// �󶨼�����
						new TimePickerDialog.OnTimeSetListener() {
					
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						// TODO Auto-generated method stub
						EditText show=(EditText) findViewById(R.id.show);
						show.setText("��ѡ���ˣ�"+hourOfDay+"ʱ"+minute+"��");
					}
				}
				//���ó�ʼʱ��
				,c.get(Calendar.HOUR_OF_DAY)
				,c.get(Calendar.MINUTE)
				//true��ʾ����24Сʱ��
				, true).show();
			}
		});
	}



}
