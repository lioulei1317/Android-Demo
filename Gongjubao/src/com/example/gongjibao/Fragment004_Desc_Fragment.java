package com.example.gongjibao;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.TextView;

public class Fragment004_Desc_Fragment extends Fragment {
	// �����Fragment��ʾ��Book����
	Fragment002_BookContent.Book book;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// ���������Fragmentʱ������ITEM_ID����
		super.onCreate(savedInstanceState);
		if (getArguments().containsKey("ID")) {
			book = Fragment002_BookContent.ITEM_MAP.get(getArguments().getInt(
					"ID"));
		}
	}

	// ��д�÷������÷������ص�View����ΪFragment��ʾ�����
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// ����/res/layout/Ŀ¼�µ�fragment_book_detail.xml�����ļ�
		View v = inflater.inflate(R.layout.fragment002_book, container, false);
		if (book != null) {
			// ��book_title�ı�����ʾbook�����title����
			TextView tv1 = (TextView) v.findViewById(R.id.fragtv_001);
			tv1.setText(book.title);
			// ��book_desc�ı�����ʾbook�����desc����
			TextView tv2 = (TextView) v.findViewById(R.id.fragtv_002);
			tv2.setText(book.desc);
		}

		return v;
	}

}
