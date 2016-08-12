package com.example.gongjibao;

import java.util.ArrayList;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BaseAdapter002_2 extends BaseAdapter{
	private ArrayList<String> listdata;
	private Context context;
	public BaseAdapter002_2(Context context,ArrayList<String>listdata){
		this.context=context;
		this.listdata=listdata;
	}

	public int getCount() {
		return listdata.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listdata.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Log.i("position",position+"");
		TextView tmView;
		if(convertView==null){
			TextView tm=new TextView(context);
			tmView=tm;
			tmView.setTag("old"+position);
			tmView.setText(listdata.get(position));
		}else{
			tmView=(TextView) convertView;
			tmView.setText(listdata.get(position)+"\t"+convertView.getTag());
		}
		return tmView;
	}

}
