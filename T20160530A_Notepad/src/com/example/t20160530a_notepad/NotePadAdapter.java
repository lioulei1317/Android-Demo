package com.example.t20160530a_notepad;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NotePadAdapter extends BaseAdapter {
	Context context;
	List<NotePadInfo> list;
	LayoutInflater inflater;

	public NotePadAdapter(Context context, List<NotePadInfo> list) {
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
	}
	public void deleteInfo(int position){
		list.remove(position);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (list != null) {
			return list.size();
		}
		return 0;
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
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder;
		if (view == null || view.getTag() == null) {
			view = inflater.inflate(R.layout.list_item, null);
			holder = new ViewHolder();
			holder.tv_title = (TextView) view.findViewById(R.id.tv_title);
			holder.tv_date = (TextView) view.findViewById(R.id.tv_date);
			view.setTag(holder);
		}
		holder = (ViewHolder) view.getTag();
		NotePadInfo info = list.get(position);
		holder.tv_title.setText(info.getTitle());
		holder.tv_date.setText(info.getDate());
		return view;
	}

	class ViewHolder {
		TextView tv_title;
		TextView tv_date;
	}

}
