package com.example.yiqugou;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.fragment.Fragment_01;
import com.example.fragment.Fragment_02;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ViewPager004_SecondFragment extends Fragment {
	Context context;
	ListView listview;
	View view;
	int[] pictures = { R.drawable.tuijian, R.drawable.nvren01,
			R.drawable.meizhuang3, R.drawable.shuma4, R.drawable.jujia5,
			R.drawable.lingshi6, R.drawable.nanren7, R.drawable.chuangyi8 };
	String[] wenzi = { "推荐", "女人", "美妆", "数码", "居家", "零食", "男人", "创意" };

	FragmentManager fragmentmanager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.viewpager002_fenlei_layout, container,
				false);
		listview = (ListView) view.findViewById(R.id.second_listview);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < pictures.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pictures", pictures[i]);
			map.put("wenzi", wenzi[i]);
			list.add(map);
		}
		SimpleAdapter simpleadapter = new SimpleAdapter(context, list,
				R.layout.second, new String[] { "pictures", "wenzi" },
				new int[] { R.id.second_imageview, R.id.second_textview });
		listview.setAdapter(simpleadapter);
		fragmentmanager = getFragmentManager();
		listview.setOnItemClickListener(oicl);
		// 默认界面为Fragment_01
		Fragment_01 fragment_01 = new Fragment_01();
		fragmentmanager.beginTransaction()
				.replace(R.id.second_linearlayout, fragment_01).commit();
		return view;
	}

	OnItemClickListener oicl = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			if (position == 0) {
				Fragment_01 fragment_01 = new Fragment_01();
				fragmentmanager.beginTransaction()
						.replace(R.id.second_linearlayout, fragment_01)
						.commit();
			} else if (position == 1) {
				Fragment_02 fragment_02 = new Fragment_02();
				fragmentmanager.beginTransaction()
						.replace(R.id.second_linearlayout, fragment_02)
						.commit();
			} else {
				Fragment_01 fragment_01 = new Fragment_01();
				fragmentmanager.beginTransaction()
						.replace(R.id.second_linearlayout, fragment_01)
						.commit();
			}

		}
	};

}
