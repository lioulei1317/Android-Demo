package com.example.gongjibao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SimpleAdapter002 extends Activity{
	private String[] names = { "���", "����", "�ź�", "�ƻ�", "���ι�", "����", "�ӹ���",
			"ͯ�˱�", "������", "���ĳ�" };
	private String[] qianming = { "����˭��", "�Һ�����", "�Ҳ�֪����", "���ڸ��", "ʲôʲôѽ",
			"������", "�ǺǺ�", "������Ⱥ��", "��ô��������", "ɵ��" };
	private int[] vips = { R.drawable.vip, R.drawable.vip, R.drawable.vip,
			R.drawable.vip, R.drawable.vip, R.drawable.vip, R.drawable.vip,
			R.drawable.vip, R.drawable.vip, R.drawable.vip };
	private int[] touxiang = { R.drawable.tuian1, R.drawable.tupian2,
			R.drawable.tupian3, R.drawable.tupian4, R.drawable.tupian5,
			R.drawable.tupian6, R.drawable.tupain7, R.drawable.tupian8,
			R.drawable.tupian9, R.drawable.tupian10 };
	private int[] zhuangtai = { R.drawable.diannao, R.drawable.shouji,
			R.drawable.shouji4g, R.drawable.shouji, R.drawable.diannao,
			R.drawable.diannao, R.drawable.wifi, R.drawable.diannao,
			R.drawable.diannao, R.drawable.wifi };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simpleadapter001);
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < names.length; i++) {
			Map<String, Object> listmp = new HashMap<String, Object>();
			listmp.put("tx", touxiang[i]);
			listmp.put("mz", names[i]);
			listmp.put("vp", vips[i]);
			listmp.put("qm", qianming[i]);
			listmp.put("zt", zhuangtai[i]);
			lists.add(listmp);
		}
		// ����һ��SimpleAdapterk
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, lists,
				R.layout.simpleadapter003, new String[] { "tx", "mz", "vp","qm",
						"zt" }, new int[] { R.id.imv1, R.id.name1, R.id.imv2,
						R.id.name2, R.id.imv3});
		ListView list=(ListView) findViewById(R.id.mylist);
		// ΪListView����Adapter
		list.setAdapter(simpleAdapter);
		

	}

}
