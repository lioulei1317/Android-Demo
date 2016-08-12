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
	// 省级选项值
	private String[] sheng = new String[] { "四川省", "广西壮族自治区" };
	// 地级选项值
	private String[][] shi = new String[][] { { "达州市", "成都市", "南充市" },
			{ "南宁市", "百色市", "柳州市" } };

	// 县级选项值
	private String[][][] qu = new String[][][] {
			{ { "渠县", "开江县" }, { "青羊区", "金牛区" }, { "营山县", "蓬安县" } },
			{ { "青秀区", "江南区" }, { "平果县", "田东县" }, { "柳城县", "柳江县" } } };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spinner001);
		setSpinner();
	}

	/*
	 * 设置下拉框
	 */
	private void setSpinner() {
		// TODO Auto-generated method stub
		shengSpinner = (Spinner) findViewById(R.id.spin_province);
		shiSpinner = (Spinner) findViewById(R.id.spin_city);
		quSpinner = (Spinner) findViewById(R.id.spin_county);
		spinnerbtn001 = (Button) findViewById(R.id.spinnerbtn001);
		spinnertv001 = (TextView) findViewById(R.id.spinnertv001);

		// 绑定适配器和值
		shengAdapter = new ArrayAdapter<String>(Spinner001.this,
				android.R.layout.simple_spinner_item, sheng);
		shengSpinner.setAdapter(shengAdapter);
		shengSpinner.setSelection(0, true); // 设置默认选中项，此处为默认选中第1个值

		shiAdapter = new ArrayAdapter<String>(Spinner001.this,
				android.R.layout.simple_spinner_item, shi[0]);
		shiSpinner.setAdapter(shiAdapter);
		shiSpinner.setSelection(0, true); // 默认选中第1个

		quAdapter = new ArrayAdapter<String>(Spinner001.this,
				android.R.layout.simple_spinner_item, qu[0][0]);
		quSpinner.setAdapter(quAdapter);
		quSpinner.setSelection(0, true);

		// 省级下拉框监听
		shengSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			// 表示选项被改变的时候触发此方法，主要实现办法：动态改变地级适配器的绑定值
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				// 将地级适配器的值改变为city[position]中的值
				shiAdapter = new ArrayAdapter<String>(Spinner001.this,
						android.R.layout.simple_spinner_item, shi[position]);
				// 设置二级下拉列表的选项内容适配器
				shiSpinner.setAdapter(shiAdapter);
				shengposition = position; // 记录当前省级序号，留给下面修改县级适配器时用

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		// 市级下拉监听
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
				spinnertv001.setText("你选择的：" + sheng[shengposition]
						+ "  " + shi[shengposition][shiposition] + "  "
						+ qu[shengposition][shiposition][quposition]);
			}  
		});

	}

}
