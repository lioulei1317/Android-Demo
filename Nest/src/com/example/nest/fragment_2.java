package com.example.nest;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class fragment_2 extends Fragment{
	Context context;
	ListView listview;
	String [] str = new String[]{"AAA","BBB","CCC"};
	FragmentManager fragmentmanager;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = getActivity();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.qiantao, null);
		listview = (ListView) v.findViewById(R.id.lv);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_activated_1,str);
		listview.setAdapter(adapter);
		
		fragmentmanager = getFragmentManager();
		listview.setOnItemClickListener(oicl);
		return v;
	}
	OnItemClickListener oicl = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			// TODO Auto-generated method stub
			fragment_3 frag = new fragment_3();
			fragmentmanager.beginTransaction().replace(R.id.ll,frag ).commit();
			
			Bundle data = new Bundle();
			data.putString("key", str[arg2]);
			frag.setArguments(data);
		}
	};


}
