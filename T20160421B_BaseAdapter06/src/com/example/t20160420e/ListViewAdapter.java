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
	LayoutInflater inflater;// �Ѳ���ת����view����

	// ���岼�ֵ�����
	final int VIEW_TYPE = 2;
	final int TYPE_1 = 0;
	final int TYPE_2 = 1;

	// list��ʾҪ��ʾ�����ݣ�context����������View����ʱ��Ҫ�õ�

	public ListViewAdapter(Context context, List<Info> list) {
		this.context = context;
		this.list = list;
		// ���ز��ֹ�����
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

	// һ���ж�����view��ʽ
	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return VIEW_TYPE;
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

	// getCount��Ҫ�����ж��ٸ�item��Ҳ����˵�����listview��չʾ��ô����
	@Override
	public int getCount() {
		if (list == null) {
			return 0;

		}
		return list.size();
	}

	// getItem��Ҫ���ز���positionλ�õ�����
	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int positionId) {
		return positionId;
	}

	/*
	 * getView�����Ƿ���λ��Ϊposition��View���� �ڶ�������convertView���ǵ���Դ��������,�����»����Ĺ����У�
	 * ��Ҫ��ʾĳ���ʱ��Ż����getView�������������ĳ����ز���ʾ�� �ͻ�Ѳ���ʾ��һ�е�View��ΪconvertView�������룬
	 * ���û��ĳ����أ�convertViewֵΪnull������ͨ����������е�
	 */
	@Override
	public View getView(int position, View contextView, ViewGroup arg2) {
		viewInfo vinfo=null;
		viewInfo2 vinfo2=null;
		int type = getItemViewType(position);
		if (contextView == null || contextView.getTag() == null) {
			switch (type) {
			case TYPE_1:
				// ��xml����ת��Ϊview����
				// ����LayoutInflater�����inflate�������ɿؼ�����
				// inflate�����ĵ�һ������Ϊxml�ļ����ڶ�������һ��Ϊnull��
				// ����ֵΪ��xml�ļ������ı�ǩ����
				contextView = inflater.inflate(R.layout.list_layout, null);
				vinfo = new viewInfo();
				// ����view�����ҵ������е����
				vinfo.image = (ImageView) contextView.findViewById(R.id.image);
				vinfo.list_txt = (TextView) contextView
						.findViewById(R.id.list_txt);
				vinfo.txt2 = (TextView) contextView.findViewById(R.id.txt2);
				vinfo.txt3 = (TextView) contextView.findViewById(R.id.txt3);
				vinfo.txt4 = (TextView) contextView.findViewById(R.id.txt4);
				// ������ setTag()
				contextView.setTag(vinfo);
				break;
			case TYPE_2:
				// ��xml����ת��Ϊview����
				// ����LayoutInflater�����inflate�������ɿؼ�����
				// inflate�����ĵ�һ������Ϊxml�ļ����ڶ�������һ��Ϊnull��
				// ����ֵΪ��xml�ļ������ı�ǩ����
				contextView = inflater.inflate(R.layout.list_layout2, null);
				vinfo2 = new viewInfo2();
				// ����view�����ҵ������е����
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
				// ������ setTag()
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
				vinfo.txt4.setText("����(" + info.getCount() + ")");
				Log.i("result", info.getTitle());
				break;
			case TYPE_2:
				vinfo2 =(viewInfo2) contextView.getTag();
				Info info2 = list.get(position);
				vinfo2.image2.setImageResource(info2.getImg());
				vinfo2.name.setText(info2.getTitle());
				vinfo2.pinglun.setText(info2.getTime());
				vinfo2.shuju.setText(info2.getContext());
				vinfo2.time.setText("����(" + info2.getCount() + ")");
				Log.i("result", info2.getTitle());
				break;
			}
			
		}
		return contextView;
	}

	// ����һ��viewInfo��
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
