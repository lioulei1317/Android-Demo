package com.example.gongjibao;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Fragment003_Book_ListFragment extends ListFragment {
	Callbooks mCallbooks;

	// 定义一个回调接口，该Fragment所在Activity需要实现该接口
	// 该Fragment将通过该接口与它所在的Activity交互
	public interface Callbooks {
		public void onItemSelect(Integer id);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 为该ListFragment设置Adapter
		setListAdapter(new ArrayAdapter<Fragment002_BookContent.Book>(
				getActivity(), android.R.layout.simple_list_item_activated_1,
				android.R.id.text1, Fragment002_BookContent.ITEMS));
	}

	// 当该Fragment被添加、显示到Activity时，回调该方法
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		// 如果Activity没有实现Callbacks接口，抛出异常
		if (!(activity instanceof Callbooks)) {
			throw new IllegalStateException("你的Activity没有实现Callbooks接口");
		}
		// 把该Activity当成Callbacks对象
		mCallbooks = (Callbooks) activity;
	}

	// 当该Fragment从它所属的Activity中被删除时回调该方法
	@Override
	public void onDetach() {
		
		super.onDetach();
		// 将mCallbacks赋为null。
		mCallbooks = null;
	}

	// 当用户点击某列表项时激发该回调方法
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		// 激发mCallbacks的onItemSelected方法
		mCallbooks.onItemSelect(Fragment002_BookContent.ITEMS.get(position).id);
		
	}
}
