package com.example.gongjibao;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.TextView;

public class Fragment004_Desc_Fragment extends Fragment {
	// 保存该Fragment显示的Book对象
	Fragment002_BookContent.Book book;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// 如果启动该Fragment时包含了ITEM_ID参数
		super.onCreate(savedInstanceState);
		if (getArguments().containsKey("ID")) {
			book = Fragment002_BookContent.ITEM_MAP.get(getArguments().getInt(
					"ID"));
		}
	}

	// 重写该方法，该方法返回的View将作为Fragment显示的组件
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 加载/res/layout/目录下的fragment_book_detail.xml布局文件
		View v = inflater.inflate(R.layout.fragment002_book, container, false);
		if (book != null) {
			// 让book_title文本框显示book对象的title属性
			TextView tv1 = (TextView) v.findViewById(R.id.fragtv_001);
			tv1.setText(book.title);
			// 让book_desc文本框显示book对象的desc属性
			TextView tv2 = (TextView) v.findViewById(R.id.fragtv_002);
			tv2.setText(book.desc);
		}

		return v;
	}

}
