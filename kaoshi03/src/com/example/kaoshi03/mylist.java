package com.example.kaoshi03;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class mylist extends ListView {
	// scollview��listviewһ����ʱ�Ľ������⣬Ҫôֻ�ܹ���scollview,
	// listviewֻ����ʾһС���֣����ǿ��Թ���
	// �������������������Զ���listview���̳�listview,��дonMeasure
	// ������Ȼ���ڲ���������Զ����listview,�����Զ���ؼ���com.example.ks503.mylist
	// ����.��������ʲô��ͷ����ʲô��β

	// �Զ���listview��Ҫ��д��3�����췽��
	public mylist(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public mylist(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public mylist(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// �˷����Ǹ��ؼ������ӿؼ���ʾ�Ŀ�ߣ�����������ͨ����д�˷�������������ͻ������
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,

		MeasureSpec.AT_MOST);
		// AT_MOST���ӿؼ�����ȫ��ʾ�Լ�ռ�����Ŀ�ߡ���scollview�Լ��Ĺ���
		// ������listview��ȫ��ʾ�ꡣ
		super.onMeasure(widthMeasureSpec, expandSpec);

	}

}
