package com.example.nest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends FragmentActivity {
	RadioGroup group;
	RadioButton rb1, rb2;
	ViewPager pager;

	List<Fragment> list;
	
	FragmentManager fragmentmanager;
	Myadapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		data();
		adapter = new Myadapter(fragmentmanager, list);
		pager.setAdapter(adapter);
		
	}

	private void init() {
		// TODO Auto-generated method stub
		group = (RadioGroup) findViewById(R.id.group);
		pager = (ViewPager) findViewById(R.id.pager);
		rb1 = (RadioButton) findViewById(R.id.first);
		rb2 = (RadioButton) findViewById(R.id.second);
		fragmentmanager = getSupportFragmentManager();
		group.setOnCheckedChangeListener(occl);
		pager.setOnPageChangeListener(opc);

	}
	private void data() {
		// TODO Auto-generated method stub
		list = new ArrayList<Fragment>();
		list.add(new fragment_1());
		list.add(new fragment_2());

	}
	OnPageChangeListener opc = new OnPageChangeListener() {
		
		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			RadioButton rb = (RadioButton) group.getChildAt(arg0);
			rb.setChecked(true);
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	OnCheckedChangeListener occl = new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			switch (checkedId) {
			case R.id.first:
				pager.setCurrentItem(0);
				break;

			case R.id.second:
				pager.setCurrentItem(1);
				break;
			}
			
		}
	};

}
