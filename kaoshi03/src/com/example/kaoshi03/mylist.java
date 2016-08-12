package com.example.kaoshi03;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class mylist extends ListView {
	// scollview与listview一起用时的焦点问题，要么只能滚动scollview,
	// listview只能显示一小部分，但是可以滚动
	// 解决方法：这里采用是自定义listview，继承listview,重写onMeasure
	// 方法，然后在布局里调用自定义的listview,调用自定义控件：com.example.ks503.mylist
	// 包名.类名，以什么开头就以什么结尾

	// 自定义listview需要重写的3个构造方法
	public mylist(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public mylist(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public mylist(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// 此方法是父控件控制子控件显示的宽高，在这里我们通过重写此方法，解决焦点冲突的问题
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,

		MeasureSpec.AT_MOST);
		// AT_MOST：子控件能完全显示自己占得最大的宽高。即scollview自己的滚动
		// 包含了listview完全显示完。
		super.onMeasure(widthMeasureSpec, expandSpec);

	}

}
