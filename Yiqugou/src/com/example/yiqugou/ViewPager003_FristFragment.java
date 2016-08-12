package com.example.yiqugou;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.example.adapter.Adapter_GridView_hot;
import com.example.adapter.GalleryAdapter;
import com.imooc.listviewfrashdemo1.MainActivity2;
import com.two.ui.MainActivity;
import com.yiqugou.myhome.BabyActivity;
import com.yiqugou.webview.MyWebView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SimpleAdapter;

public class ViewPager003_FristFragment extends Fragment {
	private Gallery gallery;
	private RadioGroup radioGroup;
	private RadioButton radioButton01;
	private RadioButton radioButton02;
	private RadioButton radioButton03;
	private RadioButton radioButton04;
	private RadioButton radioButton05;
	private GridView gridView;
	private Handler mhHandler;
	private ImageView imageView1;
	private ImageView imageView2;
	private ImageView imageView3;
	private ImageView saoyisao;
	private EditText editText;
	// 热门市场的九宫格
	private GridView my_gridView_hot;
	private Adapter_GridView_hot adapter_GridView_hot;
	int cut = 0;
	Context context;
	int[] image = new int[] { R.drawable.haibao1, R.drawable.haibao2,
			R.drawable.haibao3, R.drawable.haibao4, R.drawable.haibao };
	int[] pictures = new int[] { R.drawable.ali1, R.drawable.ali2,
			R.drawable.ali3, R.drawable.ali4, R.drawable.ali5, R.drawable.ali6,
			R.drawable.ali7, R.drawable.ali8 };
	// 热门市场的资源文件
	private int[] pic_path_hot = { R.drawable.menu_1, R.drawable.menu_2,
			R.drawable.menu_3, R.drawable.menu_4, R.drawable.menu_5,
			R.drawable.menu_6 };
	String[] wenzi = { "新品上架", "畅销热卖", "抢购", "晒单", "全场正品", "闪电发货", "全场包邮",
			"货到付款" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = getActivity();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.viewpager001_home_layout2,
				container, false);
		gallery = (Gallery) view.findViewById(R.id.home_gallery001);
		radioGroup = (RadioGroup) view.findViewById(R.id.home_radiogroup);
		radioButton01 = (RadioButton) view
				.findViewById(R.id.home_radiobutton001);
		radioButton02 = (RadioButton) view
				.findViewById(R.id.home_radiobutton002);
		radioButton03 = (RadioButton) view
				.findViewById(R.id.home_radiobutton003);
		radioButton04 = (RadioButton) view
				.findViewById(R.id.home_radiobutton004);
		radioButton05 = (RadioButton) view
				.findViewById(R.id.home_radiobutton005);
		gridView = (GridView) view.findViewById(R.id.shouyeGridView_001);
		imageView1 = (ImageView) view
				.findViewById(R.id.home_imageview_miaosha1);
		imageView2 = (ImageView) view
				.findViewById(R.id.home_imageview_miaosha2);
		imageView3 = (ImageView) view
				.findViewById(R.id.home_imageview_miaosha3);
		saoyisao = (ImageView) view
				.findViewById(R.id.shouye_imageview002_shaoyishao);
		editText = (EditText) view.findViewById(R.id.shouye_Editext);
		my_gridView_hot = (GridView) view.findViewById(R.id.my_gridview_hot);
		my_gridView_hot.setSelector(new ColorDrawable(Color.TRANSPARENT));
		adapter_GridView_hot = new Adapter_GridView_hot(getActivity(), pic_path_hot);
		my_gridView_hot.setAdapter(adapter_GridView_hot);

		GalleryAdapter adapter = new GalleryAdapter(context, image);
		gallery.setAdapter(adapter);
		gallery.setOnItemSelectedListener(listener);
		radioGroup.setOnCheckedChangeListener(change);
		gridView.setOnItemClickListener(gridviewlistener);
		imageView1.setOnClickListener(imagev1);
		imageView2.setOnClickListener(imagev2);
		imageView3.setOnClickListener(imagev3);
		saoyisao.setOnClickListener(saoyisaolistener);
		editText.setOnFocusChangeListener(editextlistener);
		SetSimpleadapter();
		my_gridView_hot.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				//跳转到宝贝详情界面
				Intent intent = new Intent(getActivity(), BabyActivity.class);
				startActivity(intent);
			}
		});

		mhHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0x123) {
					if (++cut < image.length) {
						gallery.setSelection(cut);
					} else {
						cut = 0;
						gallery.setSelection(cut);
					}
				}
			}
		};
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mhHandler.sendEmptyMessage(0x123);
			}
		}, 0, 3000);
		return view;
	}

	OnItemSelectedListener listener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long arg) {
			// TODO Auto-generated method stub
			int id = R.id.home_radiobutton001;
			switch (position) {
			case 0:
				id = R.id.home_radiobutton001;
				break;
			case 1:
				id = R.id.home_radiobutton002;
				break;
			case 2:
				id = R.id.home_radiobutton003;
				break;
			case 3:
				id = R.id.home_radiobutton004;
				break;
			case 4:
				id = R.id.home_radiobutton005;
				break;

			}
			radioGroup.check(id);

		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub

		}
	};
	OnCheckedChangeListener change = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			int position = 0;
			if (radioButton01.isChecked()) {
				position = 0;
				gallery.setSelection(position);
			} else if (radioButton02.isChecked()) {
				position = 1;
				gallery.setSelection(position);
			} else if (radioButton03.isChecked()) {
				position = 2;
				gallery.setSelection(position);
			} else if (radioButton04.isChecked()) {
				position = 3;
				gallery.setSelection(position);
			} else if (radioButton05.isChecked()) {
				position = 4;
				gallery.setSelection(position);
			}

		}
	};
	OnItemClickListener gridviewlistener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(context, BabyActivity.class);
			startActivity(intent);
		}
	};
	OnClickListener imagev1 = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(context, MyWebView.class);
			startActivity(intent);

		}
	};
	OnClickListener imagev2 = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(context, MyWebView.class);
			startActivity(intent);
		}
	};
	OnClickListener imagev3 = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(context, MyWebView.class);
			startActivity(intent);
		}
	};
	OnClickListener saoyisaolistener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(context, MainActivity.class);
			startActivity(intent);
		}
	};
	OnFocusChangeListener editextlistener = new OnFocusChangeListener() {

		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			// TODO Auto-generated method stub
			if (hasFocus) {
				// Editext获取焦点的响应
				 Intent intent=new Intent(context,MainActivity2.class);
				 startActivity(intent);
				 System.out.println("Editext获取焦点的响应"+"5655555555555555555555555");
			} else {
				// Editext失去焦点的响应
//				 Intent intent=new Intent(context,MainActivity2.class);
//				 startActivity(intent);
				 System.out.println(" Editext失去焦点的响应"+"5655555555555555555555555");
			}
		}
	};

	private void SetSimpleadapter() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < pictures.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("picture", pictures[i]);
			map.put("wenzi", wenzi[i]);
			list.add(map);
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(context, list,
				R.layout.gallery_text, new String[] { "picture", "wenzi" },
				new int[] { R.id.gallery_text_imageview001,
						R.id.gallery_text_textview001 });
		gridView.setAdapter(simpleAdapter);
	}

}
