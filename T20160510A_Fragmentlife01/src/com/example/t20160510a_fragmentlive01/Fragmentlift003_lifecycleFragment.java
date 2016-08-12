package com.example.t20160510a_fragmentlive01;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragmentlift003_lifecycleFragment extends Fragment {
	final String TAG = "输出日志";

	@Override
	public void onAttach(Activity activity) {
		// 当Fragment被添加到Activity时被回调，该方法只会被调用一次
		super.onAttach(activity);
		// 输出日志
		Log.d(TAG, "-------onAttach------");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// 创建Fragment时被回调，该方法只会被调用一次
		super.onCreate(savedInstanceState);
		// 输出日志
		Log.d(TAG, "-------onCreate------");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 每次创建、绘制该Fragment的View组件时回调该方法，Fragment将会显示该方法返回的View组件
		// 输出日志
		Log.d(TAG, "-------onCreateView------");
		TextView tv = new TextView(getActivity());
		tv.setGravity(Gravity.CENTER_HORIZONTAL);
		tv.setText("测试Fragment");
		tv.setTextSize(40);
		return tv;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// 当Fragment所在的Activity被启动完成后回调该方法
		super.onActivityCreated(savedInstanceState);
		// 输出日志
		Log.d(TAG, "-------onActivityCreated------");
	}

	@Override
	public void onStart() {
		// 启动Fragment时被回调
		super.onStart();
		// 输出日志
		Log.d(TAG, "-------onStart------");
	}

	@Override
	public void onResume() {
		// 恢复Fragment时被回调，调用onStart()方法后一定会回调onResume()方法
		super.onResume();
		// 输出日志
		Log.d(TAG, "-------onResume------");
	}

	@Override
	public void onPause() {
		// 暂停Fragment时被回调
		super.onPause();
		// 输出日志
		Log.d(TAG, "-------onPause------");
	}

	@Override
	public void onStop() {
		// 停止Fragment时被回调
		super.onStop();
		// 输出日志
		Log.d(TAG, "-------onStop------");
	}

	@Override
	public void onDestroyView() {
		// 销毁该Fragment所包含的View组件时调用
		super.onDestroyView();
		// 输出日志
		Log.d(TAG, "-------onDestroyView------");
	}

	@Override
	public void onDestroy() {
		// 销毁Fragment时被回调，该方法只会被调用一次
		super.onDestroy();
		// 输出日志
		Log.d(TAG, "-------onDestroy------");
	}

	@Override
	public void onDetach() {
		// 将该Fragment从Activity中被删除、被替换完成时回调该方法。
		// onDestroy()方法后一定会回调onDetach()方法，该方法只会被调用一次
		super.onDetach();
		// 输出日志
		Log.d(TAG, "-------onDetach------");
	}
}