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
	// �÷�������ֵ���Ǹ�Fragment��ʾ��View���
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		TextView textview = new TextView(getActivity());
		textview.setGravity(Gravity.CENTER_HORIZONTAL);
		//��ȡ������Fragmentʱ����Ĳ���Bundle
		Bundle args=getArguments();
		//����Textview��ʾ���ı�
		textview.setText(args.getInt(CANGLIANG)+"");
		textview.setTextSize(30);
		//���ظ�Textview
		return textview;
	}

}
