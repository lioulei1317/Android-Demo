package com.example.gongjibao;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ActionBar_DropDown002 extends Fragment{
	public static final String CANGLIANG = "section_number";
	// 该方法返回值就是该Fragment显示的View组件
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		TextView textview = new TextView(getActivity());
		textview.setGravity(Gravity.CENTER_HORIZONTAL);
		//获取创建该Fragment时传入的参数Bundle
		Bundle args=getArguments();
		//设置Textview显示的文本
		textview.setText(args.getInt(CANGLIANG)+"");
		textview.setTextSize(30);
		//返回该Textview
		return textview;
	}

}
