package com.example.gongjibao;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ViewPager001 extends FragmentActivity {
	ViewPager viewpager;
	List<Fragment> list;
	RadioGroup rgp;
	RadioButton rb1, rb2, rb3, rb4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager001);
		viewpager = (ViewPager) findViewById(R.id.pager);
		rgp = (RadioGroup) findViewById(R.id.radiogroup_);
		viewpager.setOnPageChangeListener(listener);
		rgp.setOnCheckedChangeListener(click);
		data();
		FragmentManager manager=getSupportFragmentManager();
		viewpager.setAdapter(new ViewPager002_adapter(manager,list));
	}
	OnPageChangeListener listener=new OnPageChangeListener() {
		 //此方法是页面跳转完后得到调用，
		//arg0是你当前选中的页面的Position（位置编号）。
		@Override
		public void onPageSelected(int position) {
			RadioButton rb = (RadioButton) rgp.getChildAt(position);
			rb.setChecked(true);
			
		}
		
		
		//当页面在滑动的时候会调用此方法，在滑动被停止之前，
		//此方法回一直得到调用。其中三个参数的含义分别为：
		// arg0 :当前页面，及你点击滑动的页面
		//arg1:当前页面偏移的百分比
		//arg2:当前页面偏移的像素位置  
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}
		
		// 此方法是在状态改变的时候调用，其中arg0这个参数有三种状态（0，1，2）。arg0
		// ==1的正在滑动，arg0==2的滑动完毕了，arg0==0的什么都没做。
		//当页面开始滑动的时候，三种状态的变化顺序为（1，2，0），演示如下：
		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	};
	OnCheckedChangeListener click = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkId) {
			switch (checkId) {
			case R.id.radbt001:
				viewpager.setCurrentItem(0);
				break;
			case R.id.radbt002:
				viewpager.setCurrentItem(1);
				break;
			case R.id.radbt003:
				viewpager.setCurrentItem(2);
				break;
			case R.id.radbt004:
				viewpager.setCurrentItem(3);
				break;
			}

		}
	};

	private void data() {
		// TODO Auto-generated method stub
		list=new ArrayList<Fragment>();
		list.add(new ViewPager003_FristFragment());
		list.add(new ViewPager004_SecondFragment());
		list.add(new ViewPager005_ThridFragment());
		list.add(new ViewPager006_FourFragment());

	}

}
