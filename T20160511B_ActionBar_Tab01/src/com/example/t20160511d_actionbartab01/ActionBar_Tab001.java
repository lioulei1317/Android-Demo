package com.example.t20160511d_actionbartab01;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.Menu;

public class ActionBar_Tab001 extends Activity implements ActionBar.TabListener {
	private static final String SELECTED_ITEM = "selected_item";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actionbar_tab001);
		final ActionBar actionBar = getActionBar();
		// 设置ActionBar的导航方式：Tab导航
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// 依次添加3个Tab页，并为3个Tab标签添加事件监听器
		actionBar
				.addTab(actionBar.newTab().setText("第一页").setTabListener(this));
		actionBar
				.addTab(actionBar.newTab().setText("第二页").setTabListener(this));
		actionBar
				.addTab(actionBar.newTab().setText("第三页").setTabListener(this));
	}

	// ①
	// 保存activity的状态
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// 将当前选中的Fragment页的索引保存到Bundle中
		outState.putInt(SELECTED_ITEM, getActionBar()
				.getSelectedNavigationIndex());
	}

	// ②
	// ①和②是为了减少运算
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		if (savedInstanceState.containsKey(SELECTED_ITEM)) {
			// 选中前面保存的索引对应的Fragment页
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(SELECTED_ITEM));
		}
	}

	// 当指定Tab被选中时激发该方法
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// 创建一个Fragment对象
		Fragment fragment = new ActionBar_Tab002();
		// 创建一个Bundle对象，用于向Fragment传入参数
		Bundle bundle = new Bundle();
		bundle.putInt(ActionBar_Tab002.CANGLIANG, tab.getPosition() + 1);
		// 向fragment传入参数
		fragment.setArguments(bundle);
		// 获取FragmentTransaction对象
		FragmentTransaction fragmenttransaction = getFragmentManager()
				.beginTransaction();
		// 使用fragment代替该Activity中container组件
		fragmenttransaction.replace(R.id.actionbarlinear001, fragment);
		// 提交事务
		fragmenttransaction.commit();

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

}
