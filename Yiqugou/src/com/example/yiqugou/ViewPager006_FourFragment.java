package com.example.yiqugou;
import com.jarvis.myyiqugou.user.User_life;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewPager006_FourFragment extends Fragment implements
		OnClickListener {
	Context context;
	private LinearLayout ll_user_life;
	private LinearLayout ll_user_members;
	private LinearLayout ll_user_store;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.viewpager003_wode_layout,
				container, false);
		initView(view);
		return view;
	}

	private void initView(View view) {
		((TextView) view.findViewById(R.id.tv_top_txtTitle)).setText("我的");
		((TextView) view.findViewById(R.id.tv_top_edit)).setText("");
		ll_user_life=(LinearLayout) view.findViewById(R.id.ll_user_life);
		ll_user_members=(LinearLayout) view.findViewById(R.id.ll_user_members);
		ll_user_store=(LinearLayout) view.findViewById(R.id.ll_user_store);
		ll_user_life.setOnClickListener(this);
		ll_user_members.setOnClickListener(this);
		ll_user_store.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.ll_user_life:
			// 进入刮刮乐界面
			Intent intent1 = new Intent(getActivity(), User_life.class);
			startActivity(intent1);
			System.out.println("你点击了生活圈");
			break;
		case R.id.ll_user_members:
			// 进入刮刮乐界面
			Intent intent2 = new Intent(getActivity(), User_life.class);
			startActivity(intent2);
			System.out.println("你点击了刮刮卡");
			break;
		case R.id.ll_user_store:
			// 进入刮刮乐界面
			Intent intent3 = new Intent(getActivity(), User_life.class);
			startActivity(intent3);
			System.out.println("你点击了网店");
			break;

		default:
			break;
		}

	}

}
