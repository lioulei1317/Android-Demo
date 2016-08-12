package com.example.liulei0903;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity2 extends Activity {
	Spinner shengSpinner = null;
	Spinner shiSpinner = null;
	GridView qugv;
	ArrayAdapter<String> shengAdapter = null;
	ArrayAdapter<String> shiAdapter = null;
	ArrayAdapter<String> quAdapter = null;
	static int shengPosition = 0;
	static int shiposition = 0;
	String sheng1 = "", shi1 = "", xian1 = "";
	// ʡ��ѡ��ֵ
	private String[] sheng = new String[] { "�Ĵ�ʡ", "����׳��������" };
	// �ؼ�ѡ��ֵ
	private String[][] shi = new String[][] { { "������", "�ɶ���" },
			{ "������", "��ɫ��" } };

	// �ؼ�ѡ��ֵ
	private String[][][] qu1 = new String[][][] {
			{ { "����", "������", "ͨ����", "�ﴨ��" }, { "������", "��ţ��", "������", "�����" } },
			{ { "������", "������", "������", "������" }, { "ƽ����", "�ﶫ��", "�ﶫ��", "��ҵ��" } } };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spinner2);
		setSpinner();

	}

	private void setSpinner() {
		// TODO Auto-generated method stub
		shengSpinner = (Spinner) findViewById(R.id.spin_province);
		shiSpinner = (Spinner) findViewById(R.id.spin_city);
		qugv = (GridView) findViewById(R.id.gridview001);

		// ����������ֵ
		shengAdapter = new ArrayAdapter<String>(MainActivity2.this,
				android.R.layout.simple_spinner_item, sheng);
		shengSpinner.setAdapter(shengAdapter);

		// ʡ�����������
		shengSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			// ��ʾѡ��ı��ʱ�򴥷��˷�������Ҫʵ�ְ취����̬�ı�ؼ��������İ�ֵ
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// ���ؼ���������ֵ�ı�Ϊcity[position]�е�ֵ
				shiAdapter = new ArrayAdapter<String>(MainActivity2.this,
						android.R.layout.simple_spinner_item, shi[position]);
				// ���ö��������б��ѡ������������
				shiSpinner.setAdapter(shiAdapter);
				sheng1 = shengSpinner.getSelectedItem().toString();
				shengPosition = position; // ��¼��ǰʡ����ţ����������޸��ؼ�������ʱ��

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
								MainActivity2.this,
								android.R.layout.simple_spinner_item,
								qu1[shengPosition][position]);

						qugv.setAdapter(quAdapter);
						shi1 = shiSpinner.getSelectedItem().toString();
						shiposition = position;

					}
				});
		qugv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				xian1 = qu1[shengPosition][shiposition][position];
				Intent intent = getIntent();
				intent.putExtra("city", sheng1 + shi1 + xian1);
				MainActivity2.this.setResult(55, intent);
				MainActivity2.this.finish();
			}
		});

	}
}
