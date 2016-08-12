package com.example.gongjibao;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class BaseAdapter001 extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.baseadapter001);
		ListView myList=(ListView) findViewById(R.id.mylist);
		BaseAdapter adapter=new BaseAdapter() {
		
			//��getview������ÿ��item������ʾ��view
			//��д�÷������÷������ص�view����Ϊ�б��
			@Override
			public View getView(int position, View arg1, ViewGroup arg2) {
			//����һ��LinearLayout�������������2�����
				LinearLayout line=new LinearLayout(BaseAdapter001.this);
				line.setOrientation(0);
				ImageView image=new ImageView(BaseAdapter001.this);
				image.setImageResource(R.drawable.ic_launcher);
				TextView text=new TextView(BaseAdapter001.this);
				text.setText("��"+(position+1)+"���б���");
				text.setTextSize(20);
				text.setTextColor(Color.RED);
				line.addView(image);
				line.addView(text);
				//����LinearLayout
				return line;
			}
			
			//��д�÷������÷����ķ���ֵ����Ϊ�б����ID
			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}
			
			//��������������setOnItemclickListener
			//setOnItemLongClickListener
			//setOnItemSelectedListener
			//���ѡ������¼��з���ص�������ȡ��ǰ�����ݵ�
			
			@Override
			public Object getItem(int arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			// getCount������listviewһ���ж��ٸ�item
			@Override
			public int getCount() {
				//ָ��һ������40
				return 40;
			}
		};
		myList.setAdapter(adapter);
	}

	

}
