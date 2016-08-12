package com.example.t20160511e_actionbar_dropdown01;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.widget.ArrayAdapter;

public class ActionBar_DropDown001 extends Activity implements
		ActionBar.OnNavigationListener {
	private static final String SELECTED_ITEM = "selected_item";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actionbar_dropdown001);
		final ActionBar actionBar = getActionBar();
		// 设置ActionBar是否显示标题
		actionBar.setDisplayShowTitleEnabled(true);
		// 设置导航模式，使用List导航
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		// 为actionBar安装Arrayadapter
		actionBar
				.setListNavigationCallbacks(new ArrayAdapter<String>(
						ActionBar_DropDown001.this,
						android.R.layout.simple_list_item_1,
						android.R.id.text1,
						new String[] { "第一页", "第二页", "第三页" }), this);
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

	// 当导航项被选中时激发该方法
	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// 创建一个Fragment对象
		Fragment fragment = new ActionBar_DropDown002();
		// 创建一个Bundle对象，用于向Fragment传入参数
		Bundle bundle = new Bundle();
		bundle.putInt(ActionBar_DropDown002.CANGLIANG, itemPosition + 1);
		// 向fragment传入参数
		fragment.setArguments(bundle);
		// 获取FragmentTransaction对象
		FragmentTransaction fragmenttransaction = getFragmentManager()
				.beginTransaction();
		// 使用fragment代替该Activity中container组件
		fragmenttransaction.replace(R.id.actionbardrop001, fragment);
		// 提交事务
		fragmenttransaction.commit();
		return true;

	}

}
