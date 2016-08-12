package com.example.gongjibao;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Spinner002 extends Activity{
	private Spinner provinceSpinner = null; // ʡ����ʡ��ֱϽ�У�
	private Spinner citySpinner = null; // �ؼ���
	private Spinner countySpinner = null; // �ؼ��������ء��ؼ��У�
	ArrayAdapter<String> provinceAdapter = null; // ʡ��������
	ArrayAdapter<String> cityAdapter = null; // �ؼ�������
	ArrayAdapter<String> countyAdapter = null; // �ؼ�������
	static int provincePosition = 3;

	// ʡ��ѡ��ֵ
	private String[] province = new String[] { "����", "�Ϻ�", "���", "�㶫" };// ,"����","������","����","ɽ��","�㽭","���","����"};
	// �ؼ�ѡ��ֵ
	private String[][] city = new String[][] {
			{ "������", "������", "������", "������", "������", "������", "��̨��", "ʯ��ɽ��", "��ͷ����",
					"��ɽ��", "ͨ����", "˳����", "������", "��ƽ��", "ƽ����", "������", "������",
					"������" },
			{ "������", "������", "������", "բ����", "�����" },
			{ "��ƽ��", "�Ӷ���", "������", "�Ͽ���", "�ӱ���", "������", "������", "������", "�����",
					"������" }, { "����", "����", "�ع�" // ,"�麣","��ͷ","��ɽ","տ��","����","����","ï��","����","÷��",
			// "��β","��Դ","����","��Զ","��ݸ","��ɽ","����","����","�Ƹ�"
			} };

	// �ؼ�ѡ��ֵ
	private String[][][] county = new String[][][] { { // ����
			{ "��" }, { "��" }, { "��" }, { "��" }, { "��" }, { "��" }, { "��" },
					{ "��" }, { "��" }, { "��" }, { "��" }, { "��" }, { "��" },
					{ "��" }, { "��" }, { "��" }, { "��" }, { "��" } }, { // �Ϻ�
			{ "��" }, { "��" }, { "��" }, { "��" }, { "��" } }, { // ���
			{ "��" }, { "��" }, { "��" }, { "��" }, { "��" }, { "��" }, { "��" },
					{ "��" }, { "��" }, { "��" } }, { // �㶫
					{ "������", "������", "Խ����", "������", "�ܸ���", "�����", "������", "������",
							"�ӻ���", "������", "��خ��", "��ɳ��" }, // ����
					{ "������", "������", "������", "�޺���", "��ɽ��", "������" }, // ����
					{ "�佭��", "䥽���", "������", "�ֲ���", "������", "ʼ����", "�ʻ���", "��Դ��",
							"�·���", "��Դ��" } // �ع�
			} };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spinner002);

		setSpinner();
	}

	/*
	 * ����������
	 */
	private void setSpinner() {
		provinceSpinner = (Spinner) findViewById(R.id.spin_province);
		citySpinner = (Spinner) findViewById(R.id.spin_city);
		countySpinner = (Spinner) findViewById(R.id.spin_county);

		// ����������ֵ
		provinceAdapter = new ArrayAdapter<String>(Spinner002.this,
				android.R.layout.simple_spinner_item, province);
		provinceSpinner.setAdapter(provinceAdapter);
		provinceSpinner.setSelection(3, true); // ����Ĭ��ѡ����˴�ΪĬ��ѡ�е�4��ֵ

		cityAdapter = new ArrayAdapter<String>(Spinner002.this,
				android.R.layout.simple_spinner_item, city[3]);
		citySpinner.setAdapter(cityAdapter);
		citySpinner.setSelection(0, true); // Ĭ��ѡ�е�1��

		countyAdapter = new ArrayAdapter<String>(Spinner002.this,
				android.R.layout.simple_spinner_item, county[3][0]);
		countySpinner.setAdapter(countyAdapter);
		countySpinner.setSelection(0, true);

		// ʡ�����������
		provinceSpinner
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					// ��ʾѡ��ı��ʱ�򴥷��˷�������Ҫʵ�ְ취����̬�ı�ؼ��������İ�ֵ
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						// positionΪ��ǰʡ��ѡ�е�ֵ�����

						// ���ؼ���������ֵ�ı�Ϊcity[position]�е�ֵ
						cityAdapter = new ArrayAdapter<String>(Spinner002.this,
								android.R.layout.simple_spinner_item,
								city[position]);
						// ���ö��������б��ѡ������������
						citySpinner.setAdapter(cityAdapter);
						provincePosition = position; // ��¼��ǰʡ����ţ����������޸��ؼ�������ʱ��
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {

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
								Spinner002.this,
								android.R.layout.simple_spinner_item,
								county[provincePosition][position]);
						countySpinner.setAdapter(countyAdapter);

					}
				});
	}

}
