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
		// ����ActionBar�ĵ�����ʽ��Tab����
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// �������3��Tabҳ����Ϊ3��Tab��ǩ����¼�������
		actionBar
				.addTab(actionBar.newTab().setText("��һҳ").setTabListener(this));
		actionBar
				.addTab(actionBar.newTab().setText("�ڶ�ҳ").setTabListener(this));
		actionBar
				.addTab(actionBar.newTab().setText("����ҳ").setTabListener(this));
	}

	// ��
	// ����activity��״̬
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// ����ǰѡ�е�Fragmentҳ���������浽Bundle��
		outState.putInt(SELECTED_ITEM, getActionBar()
				.getSelectedNavigationIndex());
	}

	// ��
	// �ٺ͢���Ϊ�˼�������
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		if (savedInstanceState.containsKey(SELECTED_ITEM)) {
			// ѡ��ǰ�汣���������Ӧ��Fragmentҳ
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(SELECTED_ITEM));
		}
	}

	// ��ָ��Tab��ѡ��ʱ�����÷���
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// ����һ��Fragment����
		Fragment fragment = new ActionBar_Tab002();
		// ����һ��Bundle����������Fragment�������
		Bundle bundle = new Bundle();
		bundle.putInt(ActionBar_Tab002.CANGLIANG, tab.getPosition() + 1);
		// ��fragment�������
		fragment.setArguments(bundle);
		// ��ȡFragmentTransaction����
		FragmentTransaction fragmenttransaction = getFragmentManager()
				.beginTransaction();
		// ʹ��fragment�����Activity��container���
		fragmenttransaction.replace(R.id.actionbarlinear001, fragment);
		// �ύ����
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
