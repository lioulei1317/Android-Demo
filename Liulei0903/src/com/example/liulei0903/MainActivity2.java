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
	// 省级选项值
	private String[] sheng = new String[] { "四川省", "广西壮族自治区" };
	// 地级选项值
	private String[][] shi = new String[][] { { "达州市", "成都市" },
			{ "南宁市", "百色市" } };

	// 县级选项值
	private String[][][] qu1 = new String[][][] {
			{ { "渠县", "开江县", "通川区", "达川区" }, { "青羊区", "金牛区", "锦江区", "武侯区" } },
			{ { "兴宁区", "江南区", "青秀区", "良庆区" }, { "平果县", "田东县", "田东县", "乐业县" } } };

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

		// 绑定适配器和值
		shengAdapter = new ArrayAdapter<String>(MainActivity2.this,
				android.R.layout.simple_spinner_item, sheng);
		shengSpinner.setAdapter(shengAdapter);

		// 省级下拉框监听
		shengSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			// 表示选项被改变的时候触发此方法，主要实现办法：动态改变地级适配器的绑定值
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// 将地级适配器的值改变为city[position]中的值
				shiAdapter = new ArrayAdapter<String>(MainActivity2.this,
						android.R.layout.simple_spinner_item, shi[position]);
				// 设置二级下拉列表的选项内容适配器
				shiSpinner.setAdapter(shiAdapter);
				sheng1 = shengSpinner.getSelectedItem().toString();
				shengPosition = position; // 记录当前省级序号，留给下面修改县级适配器时用

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
