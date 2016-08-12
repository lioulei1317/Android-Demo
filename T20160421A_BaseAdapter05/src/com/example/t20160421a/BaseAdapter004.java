package com.example.t20160421a;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class BaseAdapter004 extends BaseAdapter {
	Context context;
	List<String> list;
	LayoutInflater inflater;

	// 定义布局的种类
	final int VIEW_TYPE = 3;
	final int TYPE_1 = 0;
	final int TYPE_2 = 1;
	final int TYPE_3 = 2;

	public BaseAdapter004(Context context, List<String> list) {
		this.context = context;
		this.list = list;
		//加载布局
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		int p=position%6;
		if(p==0){
			return TYPE_1;
		}else if(p==1){
			return TYPE_2;
		}else if(p==2){
			return TYPE_3;
		}else{
			return TYPE_1;
		}
		
	}
	//一共有多少种view样式
	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return VIEW_TYPE;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		viewholder_1 vh1=null;
		viewholder_2 vh2=null;
		viewholder_3 vh3=null;
		int type=getItemViewType(position);
		if(convertView==null){
			//按照当前所需样式  确定布局
			switch (type) {
			case TYPE_1:
				convertView=inflater.inflate(R.layout.baseadapter_003, null);
				vh1=new viewholder_1();
				vh1.cb=(CheckBox) convertView.findViewById(R.id.chb1);
				vh1.tv=(TextView) convertView.findViewById(R.id.tvw1);
				convertView.setTag(vh1);
				break;
			case TYPE_2:
				convertView=inflater.inflate(R.layout.baseadapter_004, null);
				vh2=new viewholder_2();
				vh2.tv=(TextView) convertView.findViewById(R.id.tvw2);
				convertView.setTag(vh2);
				break;
			case TYPE_3:
				convertView=inflater.inflate(R.layout.baseadapter_005, null);
				vh3=new viewholder_3();
				vh3.image=(ImageView) convertView.findViewById(R.id.image);
				vh3.tv=(TextView) convertView.findViewById(R.id.tvw3);
				convertView.setTag(vh3);
				break;
			}
		}else{
			switch (type) {
			case TYPE_1:
				vh1=(viewholder_1) convertView.getTag();
				break;
			case TYPE_2:
				vh2=(viewholder_2) convertView.getTag();
				break;
			case TYPE_3:
				vh3=(viewholder_3) convertView.getTag();
				break;

			}
			switch (type) {
			case TYPE_1:
				vh1.tv.setText("这是第一个样式");
				vh1.cb.setChecked(true);
				break;
			case TYPE_2:
				vh2.tv.setText("这是第二个样式");
				break;
			case TYPE_3:
				vh3.image.setImageResource(R.drawable.ic_launcher);
				vh3.tv.setText("这是第三个样式");
				break;

			}
			
		}
		
		return convertView;
	}
	class viewholder_1{
		CheckBox cb;
		TextView tv;
	}
	class viewholder_2{
		TextView tv;
	}
	class viewholder_3{
		ImageView image;
		TextView tv;
	}
}
