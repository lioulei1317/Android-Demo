package com.example.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.yiqugou.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class Fragment_02 extends Fragment{
	Context context;
	GridView gridView;
	View view;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.second_fenlei, container, false);
		init();
		return view;
	}

	private void init() {
		gridView = (GridView) view.findViewById(R.id.second_fenlei_gridview);
		int[] pictures2 = { R.drawable.menu_1, R.drawable.menu_1_0,
				R.drawable.menu_1_1, R.drawable.menu_1_2, R.drawable.menu_1_3,
				R.drawable.menu_1, R.drawable.menu_2_1, R.drawable.menu_2_2,
				R.drawable.menu_2_3, R.drawable.menu_2_4, R.drawable.menu_2_5,
				R.drawable.menu_2, R.drawable.menu_3, R.drawable.menu_4,
				R.drawable.menu_5 };
		String[] wenzi2 = { "��ɹ��", "��Ь", "����Ů��", "���бر�", "cosmetics", "�����ײ�",
				"����", "ƻ���ײ�", "�������", "������ʳ", "�и���Ʒ", "��ʿ����", "Ůʿ��������", "����ȹ",
				"Ůʿ������" };
		List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < pictures2.length; i++) {
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("pictures2", pictures2[i]);
			map2.put("wenzi2", wenzi2[i]);
			list2.add(map2);
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(context, list2,
				R.layout.gallery_text2, new String[] { "pictures2", "wenzi2" },
				new int[] { R.id.gallery_text2_imageview001,
						R.id.gallery_text2_textview001 });
		gridView.setAdapter(simpleAdapter);
	}
}
