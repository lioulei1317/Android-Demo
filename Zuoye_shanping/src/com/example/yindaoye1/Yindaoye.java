package com.example.yindaoye1;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;

public class Yindaoye extends Activity {
	ViewPager viewpager;
	List<View> list;
	ViewPagerAdapter001 viewpageradapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		inviews();
	}
	private void inviews(){
		LayoutInflater inflater = LayoutInflater.from(this);
		list=new ArrayList<View>();
		list.add(inflater.inflate(R.layout.yindaoye001, null));
		list.add(inflater.inflate(R.layout.yindaoye002, null));
		list.add(inflater.inflate(R.layout.yindaoye003, null));
		list.add(inflater.inflate(R.layout.yindaoye004, null));
		viewpager=(ViewPager) findViewById(R.id.viewpager001);
		viewpageradapter=new ViewPagerAdapter001(list, this);
		viewpager.setAdapter(viewpageradapter);
	}
	public void start(View v){
		Intent intent=new Intent(Yindaoye.this,Denglujiemian001.class);
		startActivity(intent);
	}
}
