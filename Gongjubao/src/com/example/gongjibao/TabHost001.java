package com.example.gongjibao;
import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabHost001 extends TabActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabhost001);
		// 获取该Activity里面的TabHost组件
		TabHost tabHost = getTabHost();
		// 创建第一个Tab页
		TabSpec tab1 = tabHost.newTabSpec("tab1").setIndicator("已接电话")// 设置标题
				.setContent(R.id.tab01);// 设置内容
		// 添加第一个标签页
		tabHost.addTab(tab1);
		TabSpec tab2 = tabHost
				.newTabSpec("tab2")
				// 在标签标题上放置图标
				.setIndicator("呼出电话",//只有"呼出电话"为""才能显示图片
						getResources().getDrawable(R.drawable.ic_launcher))
				.setContent(R.id.tab02);
		
		// 添加第二个标签页
		tabHost.addTab(tab2);
		TabSpec tab3 = tabHost.newTabSpec("tab3")
		// 在标签标题上放置图标
				.setIndicator("未接电话").setContent(R.id.tab03);
		
		// 添加第三个标签页
		tabHost.addTab(tab3);

	}
}
