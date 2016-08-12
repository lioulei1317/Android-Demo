package com.example.yiqugou;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;

public class Yindaoye extends Activity {
	ViewPager viewpager;
	List<View> list;
	ViewPagerAdapter001 viewpageradapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main0);
		inviews();
	}
	private void inviews(){
		LayoutInflater inflater = LayoutInflater.from(this);
		list=new ArrayList<View>();
		list.add(inflater.inflate(R.layout.yindaoye001, null));
		list.add(inflater.inflate(R.layout.yindaoye002, null));
		list.add(inflater.inflate(R.layout.yindaoye003, null));
		viewpager=(ViewPager) findViewById(R.id.viewpager001);
		viewpageradapter=new ViewPagerAdapter001(list, this);
		viewpager.setAdapter(viewpageradapter);
	}
	public void start(View v){
		Intent intent=new Intent(Yindaoye.this,Home_Page.class);
		startActivity(intent);
	}
}
