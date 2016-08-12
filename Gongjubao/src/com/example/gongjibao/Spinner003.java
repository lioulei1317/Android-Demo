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
	// 省级选项值
	private String[] province = new String[] { "四川省", "广西壮族自治区" };
	// 地级选项值
	private String[][] city = new String[][] { { "达州市", "成都市", "南充市" },
			{ "南宁市", "百色市", "柳州市" } };

	// 县级选项值
	private String[][][] county = new String[][][] {
			{ { "渠县", "开江县" }, { "青羊区", "金牛区" }, { "营山县", "蓬安县" } },
			{ { "青秀区", "江南区" }, { "平果县", "田东县" }, { "柳城县", "柳江县" } } };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spinner003);
		setSpinner();
	}

	/*
	 * 设置下拉框
	 */
	private void setSpinner() {
		// TODO Auto-generated method stub
		provinceSpinner = (Spinner) findViewById(R.id.spin_province);
		citySpinner = (Spinner) findViewById(R.id.spin_city);
		countySpinner = (Spinner) findViewById(R.id.spin_county);
		spinnerbtn001 = (Button) findViewById(R.id.spinnerbtn001);
		spinnertv001 = (TextView) findViewById(R.id.spinnertv001);

		// 绑定适配器和值
		provinceAdapter = new ArrayAdapter<String>(Spinner003.this,
				android.R.layout.simple_spinner_item, province);
		provinceSpinner.setAdapter(provinceAdapter);
		provinceSpinner.setSelection(0, true); // 设置默认选中项，此处为默认选中第4个值

		cityAdapter = new ArrayAdapter<String>(Spinner003.this,
				android.R.layout.simple_spinner_item, city[0]);
		citySpinner.setAdapter(cityAdapter);
		citySpinner.setSelection(0, true); // 默认选中第1个

		countyAdapter = new ArrayAdapter<String>(Spinner003.this,
				android.R.layout.simple_spinner_item, county[0][0]);
		countySpinner.setAdapter(countyAdapter);
		countySpinner.setSelection(0, true);

		// 省级下拉框监听
		provinceSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			// 表示选项被改变的时候触发此方法，主要实现办法：动态改变地级适配器的绑定值
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				// position为当前省级选中的值的序号

				// 将地级适配器的值改变为city[position]中的值
				cityAdapter = new ArrayAdapter<String>(Spinner003.this,
						android.R.layout.simple_spinner_item, city[position]);
				// 设置二级下拉列表的选项内容适配器
				citySpinner.setAdapter(cityAdapter);
				provincePosition = position; // 记录当前省级序号，留给下面修改县级适配器时用

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		// 地级下拉监听
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
				spinnertv001.setText("你选择的：" + province[provincePosition]
						+ "  " + city[provincePosition][shiposition] + "  "
						+ county[provincePosition][shiposition][quposition]);
			}  
		});

	}


}
