package com.example.nest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class fragment_3 extends Fragment{
	TextView tv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_layout, null);
		tv = (TextView) v.findViewById(R.id.tv);
		String ss = getArguments().getString("key");
		tv.setText(ss);
		return v;
	}


}
