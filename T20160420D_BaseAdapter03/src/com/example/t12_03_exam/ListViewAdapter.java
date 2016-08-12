package com.example.t12_03_exam;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {
	Context context;
	List<Info> list;
	LayoutInflater inflater;// �Ѳ���ת����view����
//list��ʾҪ��ʾ�����ݣ�context����������View����ʱ��Ҫ�õ�
	public ListViewAdapter(Context context, List<Info> list) {
		this.context = context;
		this.list = list;
		// ���ز��ֹ�����
		inflater = LayoutInflater.from(context);
	}

	/**
	 * ��һ��list
	 * 
	 * @param _list
	 */
	public void addAllInfo(List<Info> _list) {
		if (list == null) {
			list = new ArrayList<Info>();
		}
		list.addAll(_list);
		/*
		 * ��ʱ��������Ҫ�޸��б�������ݣ���ʵʱˢ�£����ǳ��������ⲿ����adapter.notifyDataSetChanged()
		 * ������֪ͨactivityˢ�£�
		 * �����adapter�ڲ����漰���������ݣ�ɾ�������������ݣ��Ϳ���ֱ����adapter�ڲ�����notifyDataSetChanged
		 * {() ���������ǰ���Ǹ�listview�󶨵������иı䡣
		 */
		notifyDataSetChanged();// ˢ��
	}
	//getCount��Ҫ�����ж��ٸ�item��Ҳ����˵�����listview��չʾ��ô����
	@Override
	public int getCount() {
		if (list == null) {
			Log.i("result0", "----------------getCount()");
			return 0;

		}
		return list.size();
	}
	//getItem��Ҫ���ز���positionλ�õ�����
	@Override
	public Object getItem(int position) {
		Log.i("result", "----------------getItem()" + position);
		return list.get(position);
	}

	@Override
	public long getItemId(int positionId) {
		Log.i("result", "----------------getItemId()" + positionId);
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
	public View getView(int position, View contextView, ViewGroup arg2) {
		viewInfo vinfo;
		if (contextView == null || contextView.getTag() == null) {
			// ��xml����ת��Ϊview����
			// ����LayoutInflater�����inflate�������ɿؼ�����
			//inflate�����ĵ�һ������Ϊxml�ļ����ڶ�������һ��Ϊnull��
			//����ֵΪ��xml�ļ������ı�ǩ����
			contextView = inflater.inflate(R.layout.list_layout, null);
			vinfo = new viewInfo();
			// ����view�����ҵ������е����
			vinfo.image = (ImageView) contextView.findViewById(R.id.image);
			vinfo.list_txt = (TextView) contextView.findViewById(R.id.list_txt);
			vinfo.txt2 = (TextView) contextView.findViewById(R.id.txt2);
			vinfo.txt3 = (TextView) contextView.findViewById(R.id.txt3);
			vinfo.txt4 = (TextView) contextView.findViewById(R.id.txt4);
			//������ setTag()
			contextView.setTag(vinfo);
		}
		vinfo = (viewInfo) contextView.getTag();
		Info info = list.get(position);
		vinfo.image.setImageResource(info.getImg());
		vinfo.list_txt.setText(info.getTitle());
		vinfo.txt2.setText(info.getTime());
		vinfo.txt3.setText(info.getContext());
		vinfo.txt4.setText("����(" + info.getCount() + ")");
		Log.i("result", info.getTitle());
		return contextView;
	}

	// ����һ��viewInfo��
	class viewInfo {
		ImageView image = null;
		TextView list_txt = null;
		TextView txt2 = null;
		TextView txt3 = null;
		TextView txt4 = null;

	}
}