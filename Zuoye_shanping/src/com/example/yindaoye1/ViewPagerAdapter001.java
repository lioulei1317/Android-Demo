package com.example.yindaoye1;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class ViewPagerAdapter001 extends PagerAdapter {
	List<View> list;
	Context context;

	public ViewPagerAdapter001(List<View> list, Context context) {
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		// ��ȡ��ǰ������
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// �����ж��Ƿ��ɶ������ɽ���
		return (arg0 == arg1);
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		// ��ViewGroup���Ƴ���ǰView
		((ViewPager) container).removeView(list.get(position));
	}

	@Override
	public Object instantiateItem(View container, int position) {
		// returnһ������������������PagerAdapter������ѡ���ĸ�������ڵ�ǰ��ViewPager��
		((ViewPager) container).addView(list.get(position));
		return list.get(position);
	}
}
