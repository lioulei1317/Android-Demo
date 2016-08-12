package com.yiqugou.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import com.example.yiqugou.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Tianqi extends Activity {
	Map<String, Object> map;
	TextView riqi, xingqi, wendu, tianqi, kongqizl, shidu,riluo;
	Button btn;
	EditText chengshi;
	ImageView imageView_tianqi;
	GridView gridView;
	String hqsheng = "�Ĵ�";
	String hqshi;
	HttpResponse httpresponse;
	HttpEntity httpentity;

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			Bundle data = msg.getData();
			String val = data.getString("val");
			Log.i("������--->", val);
			JsonUtil json = new JsonUtil();
			try {
				map = json.jiexi(val);

				WeatherInfo weatherInfo = (WeatherInfo) map.get("weatherInfo");
				setdesc(weatherInfo);
				List<Info> list_info = (List<Info>) map.get("list_info");
				grid_fu(list_info);
			} catch (Exception e) {
				// TODO: handle exception
				Toast.makeText(Tianqi.this, "��ѯ�����ó��е�������", Toast.LENGTH_SHORT)
						.show();
			}

		};
	};

	private void setdesc(WeatherInfo weatherInfo) {
		// TODO Auto-generated method stub

		riqi.setText(weatherInfo.getDate());
		xingqi.setText(weatherInfo.getWeek());
		wendu.setText(weatherInfo.getTemperature());
		kongqizl.setText(weatherInfo.getAirCondition());
		shidu.setText(weatherInfo.getHumidity());
		riluo.setText(weatherInfo.getSunset());
		String weather_ = weatherInfo.getWeather();
		tianqi.setText(weather_);
		if (weather_.equals("��") || weather_.equals("��ת����")
				|| weather_.equals("��ת��") || weather_.equals("����")) {
			imageView_tianqi.setImageResource(R.drawable.qing);
		} else if (weather_.equals("��") || weather_.equals("����")
				|| weather_.equals("����") || weather_.equals("�ֲ�����")) {
			imageView_tianqi.setImageResource(R.drawable.yin);
			System.out.println("��������" + weather_ + ";;;;;;;;;;;;;;;;;;;;");
		} else if (weather_.equals("С��") || weather_.equals("����")
				|| weather_.equals("����") || weather_.equals("������")
				|| weather_.equals("����")) {
			imageView_tianqi.setImageResource(R.drawable.xiaoyu1);
		} else {
			imageView_tianqi.setImageResource(R.drawable.qingzhuanduoyun);
		}

	}

	private void grid_fu(List<Info> list_info) {
		// TODO Auto-generated method stub
		System.out.println(list_info);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 1; i < list_info.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("riqi", list_info.get(i).getDate());
			map.put("week", list_info.get(i).getWeek());
			map.put("temperature", list_info.get(i).getTemperature());
			map.put("tianqi", list_info.get(i).getDayTime());
			String dayTime = list_info.get(i).getNight();
			System.out.println(dayTime);
			if (dayTime.equals("��") || dayTime.equals("��ת����")
					|| dayTime.equals("��ת��") || dayTime.equals("����")) {
				map.put("dayTime", R.drawable.qing);
			} else if (dayTime.equals("��") || dayTime.equals("����")
					|| dayTime.equals("����") || dayTime.equals("�ֲ�����")) {
				map.put("dayTime", R.drawable.yin);
			} else if (dayTime.equals("С��") || dayTime.equals("����")
					|| dayTime.equals("����") || dayTime.equals("������")
					|| dayTime.equals("����")) {
				map.put("dayTime", R.drawable.xiaoyu1);
			} else {
				map.put("dayTime", R.drawable.qingzhuanduoyun);
			}
			list.add(map);
		}

		SimpleAdapter simple = new SimpleAdapter(Tianqi.this, list,
				R.layout.grid_list, new String[] { "riqi", "week",
						"temperature", "dayTime","tianqi" },
				new int[] { R.id.grid_riqi, R.id.grid_week,
						R.id.grid_temperature, R.id.grid_image ,R.id.grid_tianqi2});

		gridView.setAdapter(simple);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weather);
		riqi = (TextView) findViewById(R.id.weather_riqi);
		xingqi = (TextView) findViewById(R.id.weather_xingqi);
		chengshi = (EditText) findViewById(R.id.weather_diqu);
		btn = (Button) findViewById(R.id.weather_btn);
		wendu = (TextView) findViewById(R.id.weather_wendu);
		imageView_tianqi = (ImageView) findViewById(R.id.weather_tianqitubiao);
		tianqi = (TextView) findViewById(R.id.weather_tianqi);
		kongqizl = (TextView) findViewById(R.id.weather_kongqizhiliang);
		shidu = (TextView) findViewById(R.id.weather_shidu);
		riluo = (TextView) findViewById(R.id.weather_riluo);
		gridView = (GridView) findViewById(R.id.http_gridview01);
		gridView.getBackground().setAlpha(50);

		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				hqshi = chengshi.getText().toString();
				if (hqshi.equals("")) {
					Toast.makeText(Tianqi.this, "������ĳ��в���Ϊ�գ�",
							Toast.LENGTH_SHORT).show();
				} else {
					new Thread(network).start();
				}
			}
		});

	}

	Runnable network = new Runnable() {

		@Override
		public void run() {
			// 1������httpget�Ķ���
			HttpGet httpget = new HttpGet(
			// ������
					"http://apicloud.mob.com/v1/weather/query?key=13292cda522db&city="
							+ hqshi + "&province=" + hqsheng);
			// 2������httpclient�Ķ���
			HttpClient httpclient = new DefaultHttpClient();
			InputStream input = null;

			try {
				// 3������GET���󣬻�ȡ��Ӧ����Ӧ
				httpresponse = httpclient.execute(httpget);
				// 4���õ����������
				httpentity = httpresponse.getEntity();
				// ��ȡ��������Ӧ
				input = httpentity.getContent();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						input));
				String line = "";
				String result = "";
				while ((line = br.readLine()) != null) {
					result = result + line;
				}
				Log.d("������", result);

				Message message = new Message();
				Bundle bundle = new Bundle();
				bundle.putString("val", result);
				message.setData(bundle);
				handler.sendMessage(message);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	};
}
