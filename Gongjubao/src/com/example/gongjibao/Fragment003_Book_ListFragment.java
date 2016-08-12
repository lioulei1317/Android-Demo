package com.example.gongjibao;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Fragment003_Book_ListFragment extends ListFragment {
	Callbooks mCallbooks;

	// ����һ���ص��ӿڣ���Fragment����Activity��Ҫʵ�ָýӿ�
	// ��Fragment��ͨ���ýӿ��������ڵ�Activity����
	public interface Callbooks {
		public void onItemSelect(Integer id);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// Ϊ��ListFragment����Adapter
		setListAdapter(new ArrayAdapter<Fragment002_BookContent.Book>(
				getActivity(), android.R.layout.simple_list_item_activated_1,
				android.R.id.text1, Fragment002_BookContent.ITEMS));
	}

	// ����Fragment����ӡ���ʾ��Activityʱ���ص��÷���
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		// ���Activityû��ʵ��Callbacks�ӿڣ��׳��쳣
		if (!(activity instanceof Callbooks)) {
			throw new IllegalStateException("���Activityû��ʵ��Callbooks�ӿ�");
		}
		// �Ѹ�Activity����Callbacks����
		mCallbooks = (Callbooks) activity;
	}

	// ����Fragment����������Activity�б�ɾ��ʱ�ص��÷���
	@Override
	public void onDetach() {
		
		super.onDetach();
		// ��mCallbacks��Ϊnull��
		mCallbooks = null;
	}

	// ���û����ĳ�б���ʱ�����ûص�����
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		// ����mCallbacks��onItemSelected����
		mCallbooks.onItemSelect(Fragment002_BookContent.ITEMS.get(position).id);
		
	}
}
