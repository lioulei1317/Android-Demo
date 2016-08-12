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
		// 获取当前界面数
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// 用于判断是否由对象生成界面
		return (arg0 == arg1);
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		// 从ViewGroup中移除当前View
		((ViewPager) container).removeView(list.get(position));
	}

	@Override
	public Object instantiateItem(View container, int position) {
		// return一个对象，这个对象表明了PagerAdapter适配器选择哪个对象放在当前的ViewPager中
		((ViewPager) container).addView(list.get(position));
		return list.get(position);
	}
}
