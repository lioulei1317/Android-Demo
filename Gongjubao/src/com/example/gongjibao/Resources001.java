package com.example.gongjibao;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class Resources001 extends Activity {
	// ��ȡϵͳ�����������Դ
	String[] texts;
	// ʹ���ַ�����Դ
	int[] textIds = new int[] { R.string.c1, R.string.c2, R.string.c3,
			R.string.c4, R.string.c5, R.string.c6, R.string.c7, R.string.c8,
			R.string.c9 };
	// ʹ����ɫ��Դ
	int[] colorIds = new int[] { R.color.c1, R.color.c2, R.color.c3,
			R.color.c4, R.color.c5, R.color.c6, R.color.c7, R.color.c8,
			R.color.c9 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resource001);
		texts = getResources().getStringArray(R.array.string_arr);
		// ����һ��BaseAdapter����
		BaseAdapter ba = new BaseAdapter() {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				TextView text = new TextView(Resources001.this);
				Resources res = Resources001.this.getResources();
				// ʹ�ó߶���Դ�������ı���ĸ߶ȡ����
				text.setWidth((int) res.getDimension(R.dimen.cell_width));
				text.setHeight((int) res.getDimension(R.dimen.cell_height));
				// ʹ���ַ�����Դ�����ı��������
//				text.setText(textIds[position]);
				text.setText(texts[position]);
				// ʹ����ɫ��Դ�������ı���ı���ɫ
//				text.setBackgroundResource(colorIds[position]);
//				// text.setTextSize(20);
//				text.setTextSize(getResources().getInteger(R.integer.font_size));
				TypedArray icons=res.obtainTypedArray(R.array.plain_arr);
				// ʹ����ɫ��Դ�������ı���ı���ɫ
				text.setBackgroundDrawable(icons.getDrawable(position));
				text.setTextSize(20);
				return text;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}

			@Override
			public Object getItem(int position) {
				// ����ָ��λ�õ��ı�
//				return getResources().getText(textIds[position]);
				return texts[position];
			}

			@Override
			public int getCount() {
				// ָ��һ������9��ѡ��
//				return textIds.length;
				return texts.length;
			}
		};
		GridView grid = (GridView) findViewById(R.id.grid01);
		// ΪGridView����Adapter
		grid.setAdapter(ba);
	}

}
