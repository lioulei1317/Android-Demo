package com.yiqugou.myhome;

import java.util.ArrayList;

import com.example.adapter.Adapter_ListView_detail;
import com.example.myview.BabyPopWindow;
import com.example.myview.BabyPopWindow.OnItemClickListener;
import com.example.yiqugou.R;
import com.imooc.viewpager.HackyViewPager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

/**
 * 单个商品详情界面
 * */
public class BabyActivity extends FragmentActivity implements
		OnItemClickListener, OnClickListener {
	private HackyViewPager viewPager;
	private ArrayList<View> allListView;
	private int[] resId = { R.drawable.detail_show_1, R.drawable.detail_show_2,
			R.drawable.detail_show_3, R.drawable.detail_show_4,
			R.drawable.detail_show_5, R.drawable.detail_show_6 };
	private ListView listView;
	/** 收藏 */
	private ImageView iv_baby_collection;
	/** 弹出商品订单信息详情 */
	private BabyPopWindow popWindow;
	/** 用于设置背景暗淡 */
	private LinearLayout all_choice_layout = null;
	/** 判断是否点击的立即购买按钮 */
	boolean isClickBuy = false;
	/** 是否添加收藏 */
	private static boolean isCollection = false;
	/** ViewPager当前显示页的下标 */
	@SuppressWarnings("unused")
	private int position = 0;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.babyxq);
		// 得到保存的收藏信息
		getSaveCollection();
		initView();
		popWindow = new BabyPopWindow(this);
		popWindow.setOnItemClickListener(this);
	}

	private void initView() {
		// 返回
		((ImageView) findViewById(R.id.iv_back)).setOnClickListener(this);
		// 购物车
		((ImageView) findViewById(R.id.put_in)).setOnClickListener(this);
		// 立即购买
		((ImageView) findViewById(R.id.buy_now)).setOnClickListener(this);
		// 收藏
		iv_baby_collection = (ImageView) findViewById(R.id.iv_baby_collection);
		iv_baby_collection.setOnClickListener(this);
		all_choice_layout = (LinearLayout) findViewById(R.id.all_choice_layout);
		listView = (ListView) findViewById(R.id.listView_Detail);
		listView.setFocusable(false);
		listView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		listView.setAdapter(new Adapter_ListView_detail(this));
		listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// 进入恋上猫女衣坊的网店
				Uri uri = Uri.parse("http://yecaoly.taobao.com");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
		});
		initViewPager();
		if (isCollection) {
			// 如果已经收藏，则显示收藏后的效果
			iv_baby_collection.setImageResource(R.drawable.second_2_collection);
		}
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.iv_back:
			// 返回
			finish();
			break;
		case R.id.iv_baby_collection:
			// 收藏
			if (isCollection) {
				// 提示是否取消收藏
				cancelCollection();
			} else {
				isCollection = true;
				setSaveCollection();
				// 如果已经收藏，则显示收藏后的效果
				iv_baby_collection
						.setImageResource(R.drawable.second_2_collection);
				Toast.makeText(this, "亲，你已收藏成功，感谢你的关注！", Toast.LENGTH_SHORT)
						.show();
			}
			break;
		case R.id.put_in:
			// 添加购物车
			isClickBuy = false;
			setBackgroundBlack(all_choice_layout, 0);
			popWindow.showAsDropDown(view);
			break;
		case R.id.buy_now:
			// 立即购买
			isClickBuy = true;
			setBackgroundBlack(all_choice_layout, 0);
			popWindow.showAsDropDown(view);
			break;
		}
	}

	private void initViewPager() {
		if (allListView != null) {
			allListView.clear();
			allListView = null;
		}
		allListView = new ArrayList<View>();

		for (int i = 0; i < resId.length; i++) {
			View view = LayoutInflater.from(this).inflate(R.layout.pic_item,
					null);
			ImageView imageView = (ImageView) view.findViewById(R.id.pic_item);
			imageView.setImageResource(resId[i]);
			allListView.add(view);
		}
		viewPager = (HackyViewPager) findViewById(R.id.iv_baby);
		ViewPagerAdapter adapter = new ViewPagerAdapter();
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				position = arg0;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		viewPager.setAdapter(adapter);
	}

	private class ViewPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return allListView.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == (View) arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View view = allListView.get(position);
			container.addView(view);
			return view;
		}

	}

	// 点击弹窗的确认按钮的响应
	public void onClickOKPop() {
		setBackgroundBlack(all_choice_layout, 1);

		if (isClickBuy) {
			// 如果之前是点击的立即购买，那么就跳转到立即购物界面
			Intent intent = new Intent(BabyActivity.this, BuynowActivity.class);
			startActivity(intent);
		} else {
			Toast.makeText(this, "添加到购物车成功", Toast.LENGTH_SHORT).show();
		}
	}

	/** 控制背景变暗 0变暗 1变亮 */
	public void setBackgroundBlack(View view, int what) {
		switch (what) {
		case 0:
			view.setVisibility(View.VISIBLE);
			break;
		case 1:
			view.setVisibility(View.GONE);
			break;
		}
	}

	/** 保存是否添加收藏 */
	private void setSaveCollection() {
		SharedPreferences sp = getSharedPreferences("SAVECOLLECTION",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putBoolean("isCollection", isCollection);
		editor.commit();
	}

	/** 得到保存的是否添加收藏标记 */
	private void getSaveCollection() {
		SharedPreferences sp = getSharedPreferences("SAVECOLLECTION",
				Context.MODE_PRIVATE);
		isCollection = sp.getBoolean("isCollection", false);

	}

	/** 取消收藏 */
	private void cancelCollection() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("亲，是否取消收藏？");
		dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				isCollection = false;
				// 如果取消收藏，则显示取消收藏后的效果
				iv_baby_collection.setImageResource(R.drawable.second_2);
				setSaveCollection();
			}
		});
		dialog.setNegativeButton("取消", null);
		dialog.create().show();

	}

}
