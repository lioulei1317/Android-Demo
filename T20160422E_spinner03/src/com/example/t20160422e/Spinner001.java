package com.example.t20160422e;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Spinner001 extends Activity {
	Spinner shengSpinner = null;
	Spinner shiSpinner = null;
	Spinner quSpinner = null;
	Button spinnerbtn001;
	TextView spinnertv001;
	ArrayAdapter<String> shengAdapter = null;
	ArrayAdapter<String> shiAdapter = null;
	ArrayAdapter<String> quAdapter = null;
	static int shengposition = 0;
	static int shiposition = 0;
	static int quposition = 0;
	// ʡ��ѡ��ֵ
	private String[] sheng = new String[] { "�Ĵ�ʡ", "����׳��������" };
	// �ؼ�ѡ��ֵ
	private String[][] shi = new String[][] { { "������", "�ɶ���", "�ϳ���" },
			{ "������", "��ɫ��", "������" } };

	// �ؼ�ѡ��ֵ
	private String[][][] qu = new String[][][] {
			{ { "����", "������" }, { "������", "��ţ��" }, { "Ӫɽ��", "���" } },
			{ { "������", "������" }, { "ƽ����", "�ﶫ��" }, { "������", "������" } } };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spinner001);
		setSpinner();
	}

	/*
	 * ����������
	 */
	private void setSpinner() {
		// TODO Auto-generated method stub
		shengSpinner = (Spinner) findViewById(R.id.spin_province);
		shiSpinner = (Spinner) findViewById(R.id.spin_city);
		quSpinner = (Spinner) findViewById(R.id.spin_county);
		spinnerbtn001 = (Button) findViewById(R.id.spinnerbtn001);
		spinnertv001 = (TextView) findViewById(R.id.spinnertv001);

		// ����������ֵ
		shengAdapter = new ArrayAdapter<String>(Spinner001.this,
				android.R.layout.simple_spinner_item, sheng);
		shengSpinner.setAdapter(shengAdapter);
		shengSpinner.setSelection(0, true); // ����Ĭ��ѡ����˴�ΪĬ��ѡ�е�1��ֵ

		shiAdapter = new ArrayAdapter<String>(Spinner001.this,
				android.R.layout.simple_spinner_item, shi[0]);
		shiSpinner.setAdapter(shiAdapter);
		shiSpinner.setSelection(0, true); // Ĭ��ѡ�е�1��

		quAdapter = new ArrayAdapter<String>(Spinner001.this,
				android.R.layout.simple_spinner_item, qu[0][0]);
		quSpinner.setAdapter(quAdapter);
		quSpinner.setSelection(0, true);

		// ʡ�����������
		shengSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			// ��ʾѡ��ı��ʱ�򴥷��˷�������Ҫʵ�ְ취����̬�ı�ؼ��������İ�ֵ
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				// ���ؼ���������ֵ�ı�Ϊcity[position]�е�ֵ
				shiAdapter = new ArrayAdapter<String>(Spinner001.this,
						android.R.layout.simple_spinner_item, shi[position]);
				// ���ö��������б��ѡ������������
				shiSpinner.setAdapter(shiAdapter);
				shengposition = position; // ��¼��ǰʡ����ţ����������޸��ؼ�������ʱ��

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		// �м���������
		shiSpinner
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {

					}

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						quAdapter = new ArrayAdapter<String>(
								Spinner001.this,
								android.R.layout.simple_spinner_item,
								qu[shengposition][position]);
						quSpinner.setAdapter(quAdapter);
						shiposition = position;

					}
				});
		quSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				quposition = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		spinnerbtn001.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				spinnertv001.setText("��ѡ��ģ�" + sheng[shengposition]
						+ "  " + shi[shengposition][shiposition] + "  "
						+ qu[shengposition][shiposition][quposition]);
			}  
		});

	}

}
