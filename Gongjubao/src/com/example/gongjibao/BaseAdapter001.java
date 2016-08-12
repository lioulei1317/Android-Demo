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
		
			//而getview返回了每个item项所显示的view
			//重写该方法，该方法返回的view将作为列表框
			@Override
			public View getView(int position, View arg1, ViewGroup arg2) {
			//创建一个LinearLayout，并向其中添加2个组件
				LinearLayout line=new LinearLayout(BaseAdapter001.this);
				line.setOrientation(0);
				ImageView image=new ImageView(BaseAdapter001.this);
				image.setImageResource(R.drawable.ic_launcher);
				TextView text=new TextView(BaseAdapter001.this);
				text.setText("第"+(position+1)+"个列表项");
				text.setTextSize(20);
				text.setTextColor(Color.RED);
				line.addView(image);
				line.addView(text);
				//返回LinearLayout
				return line;
			}
			
			//重写该方法，该方法的返回值将作为列表项的ID
			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}
			
			//用来在我们设置setOnItemclickListener
			//setOnItemLongClickListener
			//setOnItemSelectedListener
			//点击选择吃力事件中方便地调用来获取当前行数据的
			
			@Override
			public Object getItem(int arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			// getCount决定了listview一共有多少个item
			@Override
			public int getCount() {
				//指定一共包含40
				return 40;
			}
		};
		myList.setAdapter(adapter);
	}

	

}
