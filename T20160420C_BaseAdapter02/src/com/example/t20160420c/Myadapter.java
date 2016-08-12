package com.example.t20160420c;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Myadapter extends BaseAdapter {
	Context context;
	List<Info> list;
	LayoutInflater inflater;// 把布局转换成view对象
	//list表示要显示的数据，context变量在生成View对象时需要用到
	public Myadapter(Context context, List<Info> list) {
		// TODO Auto-generated method stub
		this.context = context;
		this.list = list;
		// 加载布局管理器
		inflater = LayoutInflater.from(context);

	}
	public void addInfoList(List<Info> list_) {
		// TODO Auto-generated method stub
		if(list==null){
			list=new ArrayList<Info>();
		}
		list.addAll(list_);
		System.out.println(list);
		/*
		 * 有时候我们需要修改列表里的数据，并实时刷新，我们除了在在外部调用adapter.notifyDataSetChanged()
		 * 方法来通知activity刷新，
		 * 如果在adapter内部有涉及到更新数据，删除或者增加数据，就可以直接在adapter内部调用notifyDataSetChanged
		 * {() 这个方法，前提是该listview绑定的数据有改变。
		 */
		notifyDataSetChanged();//刷新
		
		System.out.println("=======++++++++++++++++++++++++============");
	}
	//getCount需要返回有多少个item，也就是说最会在listview中展示这么多行
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (list == null) {
			return 0;
		}
		return list.size();
	}
	//getItem需要返回参数position位置的数据
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	/*
	 * getView方法是返回位置为position的View对象，
	 * 第二个参数convertView考虑到资源重用问题,在上下滑动的过程中，
	 * 需要显示某项的时候才会调用getView方法，而如果有某项被隐藏不显示，
	 * 就会把不显示那一行的View作为convertView参数传入，
	 * 如果没有某项被隐藏，convertView值为null。可以通过下面代码中的
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder vh;
		if (convertView == null || convertView.getTag() == null) {
			convertView = inflater.inflate(R.layout.list_layout, null);
			vh = new ViewHolder();
			vh.image = (ImageView) convertView.findViewById(R.id.list_image);
			vh.tv1 = (TextView) convertView.findViewById(R.id.list_tv1);
			vh.tv2 = (TextView) convertView.findViewById(R.id.list_tv2);
			vh.tv3 = (TextView) convertView.findViewById(R.id.list_tv3);
			vh.tv4 = (TextView) convertView.findViewById(R.id.list_tv4);
			convertView.setTag(vh);
		}
		vh = (ViewHolder) convertView.getTag();
		Info info = list.get(position);
		vh.image.setImageResource(info.getImage());
		vh.tv1.setText(info.getName());
		vh.tv2.setText(info.getContext());
		vh.tv3.setText(info.getTime());
		vh.tv4.setText("评论(" + info.getCount() + ")条");

		return convertView;
	}
	// 定义一个ViewHolder类
	class ViewHolder {
		ImageView image;
		TextView tv1, tv2, tv3, tv4;
	}

}
