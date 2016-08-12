package com.example.gongjibao;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Fragment001 extends Activity implements
		Fragment003_Book_ListFragment.Callbooks {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 加载/res/layout目录下的activity_book_twopane.xml布局文件
		setContentView(R.layout.fragment001);
	}

	// 实现Callbacks接口必须实现的方法
	@Override
	public void onItemSelect(Integer id) {
		// 创建Bundle，准备向Fragment传入参数
		Bundle bundle = new Bundle();
		bundle.putInt("ID", id);
		// 创建Fragment004_Desc_Fragment对象
		Fragment004_Desc_Fragment fragment = new Fragment004_Desc_Fragment();
		// 向Fragment传入参数
		fragment.setArguments(bundle);
		// 使用fragment替换book_detail_container容器当前显示的Fragment
		getFragmentManager().beginTransaction()
				.replace(R.id.book_detail, fragment).commit();
	}

}
