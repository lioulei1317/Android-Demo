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
	LayoutInflater inflater;// �Ѳ���ת����view����
	//list��ʾҪ��ʾ�����ݣ�context����������View����ʱ��Ҫ�õ�
	public Myadapter(Context context, List<Info> list) {
		// TODO Auto-generated method stub
		this.context = context;
		this.list = list;
		// ���ز��ֹ�����
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
		 * ��ʱ��������Ҫ�޸��б�������ݣ���ʵʱˢ�£����ǳ��������ⲿ����adapter.notifyDataSetChanged()
		 * ������֪ͨactivityˢ�£�
		 * �����adapter�ڲ����漰���������ݣ�ɾ�������������ݣ��Ϳ���ֱ����adapter�ڲ�����notifyDataSetChanged
		 * {() ���������ǰ���Ǹ�listview�󶨵������иı䡣
		 */
		notifyDataSetChanged();//ˢ��
		
		System.out.println("=======++++++++++++++++++++++++============");
	}
	//getCount��Ҫ�����ж��ٸ�item��Ҳ����˵�����listview��չʾ��ô����
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (list == null) {
			return 0;
		}
		return list.size();
	}
	//getItem��Ҫ���ز���positionλ�õ�����
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
	 * getView�����Ƿ���λ��Ϊposition��View����
	 * �ڶ�������convertView���ǵ���Դ��������,�����»����Ĺ����У�
	 * ��Ҫ��ʾĳ���ʱ��Ż����getView�������������ĳ����ز���ʾ��
	 * �ͻ�Ѳ���ʾ��һ�е�View��ΪconvertView�������룬
	 * ���û��ĳ����أ�convertViewֵΪnull������ͨ����������е�
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
		vh.tv4.setText("����(" + info.getCount() + ")��");

		return convertView;
	}
	// ����һ��ViewHolder��
	class ViewHolder {
		ImageView image;
		TextView tv1, tv2, tv3, tv4;
	}

}
