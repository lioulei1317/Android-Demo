package com.example.gongjibao;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class Spinner003 extends Activity{
	Spinner provinceSpinner = null;
	Spinner citySpinner = null;
	Spinner countySpinner = null;
	Button spinnerbtn001;
	TextView spinnertv001;
	ArrayAdapter<String> provinceAdapter = null;
	ArrayAdapter<String> cityAdapter = null;
	ArrayAdapter<String> countyAdapter = null;
	static int provincePosition = 0;
	static int shiposition = 0;
	static int quposition = 0;
	// ʡ��ѡ��ֵ
	private String[] province = new String[] { "�Ĵ�ʡ", "����׳��������" };
	// �ؼ�ѡ��ֵ
	private String[][] city = new String[][] { { "������", "�ɶ���", "�ϳ���" },
			{ "������", "��ɫ��", "������" } };

	// �ؼ�ѡ��ֵ
	private String[][][] county = new String[][][] {
			{ { "����", "������" }, { "������", "��ţ��" }, { "Ӫɽ��", "���" } },
			{ { "������", "������" }, { "ƽ����", "�ﶫ��" }, { "������", "������" } } };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spinner003);
		setSpinner();
	}

	/*
	 * ����������
	 */
	private void setSpinner() {
		// TODO Auto-generated method stub
		provinceSpinner = (Spinner) findViewById(R.id.spin_province);
		citySpinner = (Spinner) findViewById(R.id.spin_city);
		countySpinner = (Spinner) findViewById(R.id.spin_county);
		spinnerbtn001 = (Button) findViewById(R.id.spinnerbtn001);
		spinnertv001 = (TextView) findViewById(R.id.spinnertv001);

		// ����������ֵ
		provinceAdapter = new ArrayAdapter<String>(Spinner003.this,
				android.R.layout.simple_spinner_item, province);
		provinceSpinner.setAdapter(provinceAdapter);
		provinceSpinner.setSelection(0, true); // ����Ĭ��ѡ����˴�ΪĬ��ѡ�е�4��ֵ

		cityAdapter = new ArrayAdapter<String>(Spinner003.this,
				android.R.layout.simple_spinner_item, city[0]);
		citySpinner.setAdapter(cityAdapter);
		citySpinner.setSelection(0, true); // Ĭ��ѡ�е�1��

		countyAdapter = new ArrayAdapter<String>(Spinner003.this,
				android.R.layout.simple_spinner_item, county[0][0]);
		countySpinner.setAdapter(countyAdapter);
		countySpinner.setSelection(0, true);

		// ʡ�����������
		provinceSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			// ��ʾѡ��ı��ʱ�򴥷��˷�������Ҫʵ�ְ취����̬�ı�ؼ��������İ�ֵ
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				// positionΪ��ǰʡ��ѡ�е�ֵ�����

				// ���ؼ���������ֵ�ı�Ϊcity[position]�е�ֵ
				cityAdapter = new ArrayAdapter<String>(Spinner003.this,
						android.R.layout.simple_spinner_item, city[position]);
				// ���ö��������б��ѡ������������
				citySpinner.setAdapter(cityAdapter);
				provincePosition = position; // ��¼��ǰʡ����ţ����������޸��ؼ�������ʱ��

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		// �ؼ���������
		citySpinner
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {

					}

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						countyAdapter = new ArrayAdapter<String>(
								Spinner003.this,
								android.R.layout.simple_spinner_item,
								county[provincePosition][position]);
						shiposition = position;
						countySpinner.setAdapter(countyAdapter);

					}
				});
		countySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

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
				spinnertv001.setText("��ѡ��ģ�" + province[provincePosition]
						+ "  " + city[provincePosition][shiposition] + "  "
						+ county[provincePosition][shiposition][quposition]);
			}  
		});

	}


}
