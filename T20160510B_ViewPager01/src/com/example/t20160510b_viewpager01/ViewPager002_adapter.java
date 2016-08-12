package com.example.t20160510b_viewpager01;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPager002_adapter extends FragmentPagerAdapter {
	FragmentManager manager;
	List<Fragment> list;

	public ViewPager002_adapter(FragmentManager fm, List<Fragment> list) {
		super(fm);
		this.list = list;
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public int getCount() {
		if(list==null){
			return 0;
		}
		return list.size();
	}

}
