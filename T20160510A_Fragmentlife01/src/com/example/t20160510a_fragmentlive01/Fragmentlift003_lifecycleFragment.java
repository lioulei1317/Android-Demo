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
	final String TAG = "�����־";

	@Override
	public void onAttach(Activity activity) {
		// ��Fragment����ӵ�Activityʱ���ص����÷���ֻ�ᱻ����һ��
		super.onAttach(activity);
		// �����־
		Log.d(TAG, "-------onAttach------");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// ����Fragmentʱ���ص����÷���ֻ�ᱻ����һ��
		super.onCreate(savedInstanceState);
		// �����־
		Log.d(TAG, "-------onCreate------");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// ÿ�δ��������Ƹ�Fragment��View���ʱ�ص��÷�����Fragment������ʾ�÷������ص�View���
		// �����־
		Log.d(TAG, "-------onCreateView------");
		TextView tv = new TextView(getActivity());
		tv.setGravity(Gravity.CENTER_HORIZONTAL);
		tv.setText("����Fragment");
		tv.setTextSize(40);
		return tv;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// ��Fragment���ڵ�Activity��������ɺ�ص��÷���
		super.onActivityCreated(savedInstanceState);
		// �����־
		Log.d(TAG, "-------onActivityCreated------");
	}

	@Override
	public void onStart() {
		// ����Fragmentʱ���ص�
		super.onStart();
		// �����־
		Log.d(TAG, "-------onStart------");
	}

	@Override
	public void onResume() {
		// �ָ�Fragmentʱ���ص�������onStart()������һ����ص�onResume()����
		super.onResume();
		// �����־
		Log.d(TAG, "-------onResume------");
	}

	@Override
	public void onPause() {
		// ��ͣFragmentʱ���ص�
		super.onPause();
		// �����־
		Log.d(TAG, "-------onPause------");
	}

	@Override
	public void onStop() {
		// ֹͣFragmentʱ���ص�
		super.onStop();
		// �����־
		Log.d(TAG, "-------onStop------");
	}

	@Override
	public void onDestroyView() {
		// ���ٸ�Fragment��������View���ʱ����
		super.onDestroyView();
		// �����־
		Log.d(TAG, "-------onDestroyView------");
	}

	@Override
	public void onDestroy() {
		// ����Fragmentʱ���ص����÷���ֻ�ᱻ����һ��
		super.onDestroy();
		// �����־
		Log.d(TAG, "-------onDestroy------");
	}

	@Override
	public void onDetach() {
		// ����Fragment��Activity�б�ɾ�������滻���ʱ�ص��÷�����
		// onDestroy()������һ����ص�onDetach()�������÷���ֻ�ᱻ����һ��
		super.onDetach();
		// �����־
		Log.d(TAG, "-------onDetach------");
	}
}