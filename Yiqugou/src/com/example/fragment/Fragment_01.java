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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Fragment_01 extends Fragment {
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
		int[] pictures2 = { R.drawable.wuping1, R.drawable.wuping2,
				R.drawable.wuping3, R.drawable.wuping4, R.drawable.wuping5,
				R.drawable.wuping6, R.drawable.wuping7, R.drawable.wuping8,
				R.drawable.wuping9, R.drawable.wuping10, R.drawable.wuping11,
				R.drawable.wuping12, R.drawable.wuping13, R.drawable.wuping14,
				R.drawable.wuping15 };
		String[] wenzi2 = { "防晒衣", "凉鞋", "潮流女包", "旅行必备", "cosmetics", "护肤套餐",
				"美白", "苹果套餐", "益智玩具", "休闲零食", "孕妇用品", "男士寸衫", "女士潮流寸衫", "连衣裙",
				"女士背带裤" };
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
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "你点击了第" + (position + 1),
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}
