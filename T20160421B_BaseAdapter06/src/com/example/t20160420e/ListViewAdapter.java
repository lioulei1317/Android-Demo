package com.example.t20160420e;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {
	Context context;
	List<Info> list;
	LayoutInflater inflater;// 把布局转换成view对象

	// 定义布局的种类
	final int VIEW_TYPE = 2;
	final int TYPE_1 = 0;
	final int TYPE_2 = 1;

	// list表示要显示的数据，context变量在生成View对象时需要用到

	public ListViewAdapter(Context context, List<Info> list) {
		this.context = context;
		this.list = list;
		// 加载布局管理器
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		int p = position % 2;
		if (p == 0) {
			return TYPE_1;
		} else if (p == 1) {
			return TYPE_2;
		}
		return TYPE_1;

	}

	// 一共有多少种view样式
	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return VIEW_TYPE;
	}

	/**
	 * 传一个list
	 * 
	 * @param _list
	 */
	public void addAllInfo(List<Info> _list) {
		if (list == null) {
			list = new ArrayList<Info>();
		}
		list.addAll(_list);
		/*
		 * 有时候我们需要修改列表里的数据，并实时刷新，我们除了在在外部调用adapter.notifyDataSetChanged()
		 * 方法来通知activity刷新，
		 * 如果在adapter内部有涉及到更新数据，删除或者增加数据，就可以直接在adapter内部调用notifyDataSetChanged
		 * {() 这个方法，前提是该listview绑定的数据有改变。
		 */
		notifyDataSetChanged();// 刷新
	}

	// getCount需要返回有多少个item，也就是说最会在listview中展示这么多行
	@Override
	public int getCount() {
		if (list == null) {
			return 0;

		}
		return list.size();
	}

	// getItem需要返回参数position位置的数据
	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int positionId) {
		return positionId;
	}

	/*
	 * getView方法是返回位置为position的View对象， 第二个参数convertView考虑到资源重用问题,在上下滑动的过程中，
	 * 需要显示某项的时候才会调用getView方法，而如果有某项被隐藏不显示， 就会把不显示那一行的View作为convertView参数传入，
	 * 如果没有某项被隐藏，convertView值为null。可以通过下面代码中的
	 */
	@Override
	public View getView(int position, View contextView, ViewGroup arg2) {
		viewInfo vinfo=null;
		viewInfo2 vinfo2=null;
		int type = getItemViewType(position);
		if (contextView == null || contextView.getTag() == null) {
			switch (type) {
			case TYPE_1:
				// 将xml布局转换为view对象
				// 调用LayoutInflater对象的inflate方法生成控件对象，
				// inflate方法的第一个参数为xml文件，第二个参数一般为null。
				// 返回值为该xml文件最外层的标签对象。
				contextView = inflater.inflate(R.layout.list_layout, null);
				vinfo = new viewInfo();
				// 利用view对象，找到布局中的组件
				vinfo.image = (ImageView) contextView.findViewById(R.id.image);
				vinfo.list_txt = (TextView) contextView
						.findViewById(R.id.list_txt);
				vinfo.txt2 = (TextView) contextView.findViewById(R.id.txt2);
				vinfo.txt3 = (TextView) contextView.findViewById(R.id.txt3);
				vinfo.txt4 = (TextView) contextView.findViewById(R.id.txt4);
				// 绑定数据 setTag()
				contextView.setTag(vinfo);
				break;
			case TYPE_2:
				// 将xml布局转换为view对象
				// 调用LayoutInflater对象的inflate方法生成控件对象，
				// inflate方法的第一个参数为xml文件，第二个参数一般为null。
				// 返回值为该xml文件最外层的标签对象。
				contextView = inflater.inflate(R.layout.list_layout2, null);
				vinfo2 = new viewInfo2();
				// 利用view对象，找到布局中的组件
				vinfo2.image2 = (ImageView) contextView
						.findViewById(R.id.list2_image2);
				vinfo2.name = (TextView) contextView
						.findViewById(R.id.list2_name);
				vinfo2.shuju = (TextView) contextView
						.findViewById(R.id.list2_shuju);
				vinfo2.time = (TextView) contextView
						.findViewById(R.id.list2_time);
				vinfo2.pinglun = (TextView) contextView
						.findViewById(R.id.list2_pinglun);
				// 绑定数据 setTag()
				contextView.setTag(vinfo2);
				break;
			}

		} else {
			switch (type) {
			case TYPE_1:
				vinfo=(viewInfo) contextView.getTag();
				break;
			case TYPE_2:
				vinfo2=(viewInfo2) contextView.getTag();
				break;

			default:
				break;
			}
			switch (type) {
			case TYPE_1:
				vinfo = (viewInfo) contextView.getTag();
				Info info = list.get(position);
				vinfo.image.setImageResource(info.getImg());
				vinfo.list_txt.setText(info.getTitle());
				vinfo.txt2.setText(info.getTime());
				vinfo.txt3.setText(info.getContext());
				vinfo.txt4.setText("评论(" + info.getCount() + ")");
				Log.i("result", info.getTitle());
				break;
			case TYPE_2:
				vinfo2 =(viewInfo2) contextView.getTag();
				Info info2 = list.get(position);
				vinfo2.image2.setImageResource(info2.getImg());
				vinfo2.name.setText(info2.getTitle());
				vinfo2.pinglun.setText(info2.getTime());
				vinfo2.shuju.setText(info2.getContext());
				vinfo2.time.setText("评论(" + info2.getCount() + ")");
				Log.i("result", info2.getTitle());
				break;
			}
			
		}
		return contextView;
	}

	// 定义一个viewInfo类
	class viewInfo {
		public Object image2;
		ImageView image = null;
		TextView list_txt = null;
		TextView txt2 = null;
		TextView txt3 = null;
		TextView txt4 = null;

	}

	class viewInfo2 {
		ImageView image2 = null;
		TextView name = null;
		TextView shuju = null;
		TextView time = null;
		TextView pinglun = null;

	}

}
