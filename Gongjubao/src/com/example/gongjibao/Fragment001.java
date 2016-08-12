package com.example.gongjibao;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Fragment001 extends Activity implements
		Fragment003_Book_ListFragment.Callbooks {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ����/res/layoutĿ¼�µ�activity_book_twopane.xml�����ļ�
		setContentView(R.layout.fragment001);
	}

	// ʵ��Callbacks�ӿڱ���ʵ�ֵķ���
	@Override
	public void onItemSelect(Integer id) {
		// ����Bundle��׼����Fragment�������
		Bundle bundle = new Bundle();
		bundle.putInt("ID", id);
		// ����Fragment004_Desc_Fragment����
		Fragment004_Desc_Fragment fragment = new Fragment004_Desc_Fragment();
		// ��Fragment�������
		fragment.setArguments(bundle);
		// ʹ��fragment�滻book_detail_container������ǰ��ʾ��Fragment
		getFragmentManager().beginTransaction()
				.replace(R.id.book_detail, fragment).commit();
	}

}
