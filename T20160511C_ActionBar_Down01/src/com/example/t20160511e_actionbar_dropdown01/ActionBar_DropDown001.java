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
		// ����ActionBar�Ƿ���ʾ����
		actionBar.setDisplayShowTitleEnabled(true);
		// ���õ���ģʽ��ʹ��List����
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		// ΪactionBar��װArrayadapter
		actionBar
				.setListNavigationCallbacks(new ArrayAdapter<String>(
						ActionBar_DropDown001.this,
						android.R.layout.simple_list_item_1,
						android.R.id.text1,
						new String[] { "��һҳ", "�ڶ�ҳ", "����ҳ" }), this);
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

	// �������ѡ��ʱ�����÷���
	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// ����һ��Fragment����
		Fragment fragment = new ActionBar_DropDown002();
		// ����һ��Bundle����������Fragment�������
		Bundle bundle = new Bundle();
		bundle.putInt(ActionBar_DropDown002.CANGLIANG, itemPosition + 1);
		// ��fragment�������
		fragment.setArguments(bundle);
		// ��ȡFragmentTransaction����
		FragmentTransaction fragmenttransaction = getFragmentManager()
				.beginTransaction();
		// ʹ��fragment�����Activity��container���
		fragmenttransaction.replace(R.id.actionbardrop001, fragment);
		// �ύ����
		fragmenttransaction.commit();
		return true;

	}

}
