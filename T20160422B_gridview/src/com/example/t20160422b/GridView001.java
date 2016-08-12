package com.example.t20160422b;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class GridView001 extends Activity {
	GridView gridView;
	ImageView image;
	TextView gridviewtv001;
	int[] pictures = new int[] { R.drawable.ali1, R.drawable.ali10,
			R.drawable.ali11, R.drawable.ali12, R.drawable.ali13,
			R.drawable.ali14, R.drawable.ali15, R.drawable.ali16,
			R.drawable.ali17, R.drawable.ali18, R.drawable.ali2,
			R.drawable.ali20, R.drawable.ali21, R.drawable.ali3,
			R.drawable.ali4, R.drawable.ali5, R.drawable.ali6, R.drawable.ali7,
			R.drawable.ali8, R.drawable.ali9 };
	String[] wenzi = { "��ѽ��ѽŶ1", "��ѽ��ѽŶ2", "��ѽ��ѽŶ3", "��ѽ��ѽŶ4", "��ѽ��ѽŶ5",
			"��ѽ��ѽŶ6", "��ѽ��ѽŶ7", "��ѽ��ѽŶ8", "��ѽ��ѽŶ9", "��ѽ��ѽŶ10", "��ѽ��ѽŶ11",
			"��ѽ��ѽŶ12", "��ѽ��ѽŶ13", "��ѽ��ѽŶ14", "��ѽ��ѽŶ15", "��ѽ��ѽŶ16", "��ѽ��ѽŶ17",
			"��ѽ��ѽŶ18", "��ѽ��ѽŶ19", "��ѽ��ѽŶ20", };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid_view001);
		gridView = (GridView) findViewById(R.id.gridview);
		image = (ImageView) findViewById(R.id.image);
		gridviewtv001 = (TextView) findViewById(R.id.gridviewTv001);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < pictures.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pictures", pictures[i]);
			map.put("wenzi", wenzi[i]);
			list.add(map);
		}

		SimpleAdapter adapter = new SimpleAdapter(this, list,
				R.layout.grid_view002, new String[] { "pictures", "wenzi" },
				new int[] { R.id.good_image,R.id.gridviewTv002});
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				image.setImageResource(pictures[position]);
				gridviewtv001.setText(wenzi[position]);
			}
		});

	}
}
