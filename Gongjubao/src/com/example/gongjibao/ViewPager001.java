package com.example.gongjibao;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ViewPager001 extends FragmentActivity {
	ViewPager viewpager;
	List<Fragment> list;
	RadioGroup rgp;
	RadioButton rb1, rb2, rb3, rb4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager001);
		viewpager = (ViewPager) findViewById(R.id.pager);
		rgp = (RadioGroup) findViewById(R.id.radiogroup_);
		viewpager.setOnPageChangeListener(listener);
		rgp.setOnCheckedChangeListener(click);
		data();
		FragmentManager manager=getSupportFragmentManager();
		viewpager.setAdapter(new ViewPager002_adapter(manager,list));
	}
	OnPageChangeListener listener=new OnPageChangeListener() {
		 //�˷�����ҳ����ת���õ����ã�
		//arg0���㵱ǰѡ�е�ҳ���Position��λ�ñ�ţ���
		@Override
		public void onPageSelected(int position) {
			RadioButton rb = (RadioButton) rgp.getChildAt(position);
			rb.setChecked(true);
			
		}
		
		
		//��ҳ���ڻ�����ʱ�����ô˷������ڻ�����ֹ֮ͣǰ��
		//�˷�����һֱ�õ����á��������������ĺ���ֱ�Ϊ��
		// arg0 :��ǰҳ�棬������������ҳ��
		//arg1:��ǰҳ��ƫ�Ƶİٷֱ�
		//arg2:��ǰҳ��ƫ�Ƶ�����λ��  
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}
		
		// �˷�������״̬�ı��ʱ����ã�����arg0�������������״̬��0��1��2����arg0
		// ==1�����ڻ�����arg0==2�Ļ�������ˣ�arg0==0��ʲô��û����
		//��ҳ�濪ʼ������ʱ������״̬�ı仯˳��Ϊ��1��2��0������ʾ���£�
		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	};
	OnCheckedChangeListener click = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkId) {
			switch (checkId) {
			case R.id.radbt001:
				viewpager.setCurrentItem(0);
				break;
			case R.id.radbt002:
				viewpager.setCurrentItem(1);
				break;
			case R.id.radbt003:
				viewpager.setCurrentItem(2);
				break;
			case R.id.radbt004:
				viewpager.setCurrentItem(3);
				break;
			}

		}
	};

	private void data() {
		// TODO Auto-generated method stub
		list=new ArrayList<Fragment>();
		list.add(new ViewPager003_FristFragment());
		list.add(new ViewPager004_SecondFragment());
		list.add(new ViewPager005_ThridFragment());
		list.add(new ViewPager006_FourFragment());

	}

}
