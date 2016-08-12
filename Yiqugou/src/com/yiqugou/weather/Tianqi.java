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
	String hqsheng = "四川";
	String hqshi;
	HttpResponse httpresponse;
	HttpEntity httpentity;

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			Bundle data = msg.getData();
			String val = data.getString("val");
			Log.i("请求结果--->", val);
			JsonUtil json = new JsonUtil();
			try {
				map = json.jiexi(val);

				WeatherInfo weatherInfo = (WeatherInfo) map.get("weatherInfo");
				setdesc(weatherInfo);
				List<Info> list_info = (List<Info>) map.get("list_info");
				grid_fu(list_info);
			} catch (Exception e) {
				// TODO: handle exception
				Toast.makeText(Tianqi.this, "查询不到该城市的天气！", Toast.LENGTH_SHORT)
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
		if (weather_.equals("晴") || weather_.equals("晴转多云")
				|| weather_.equals("晴转雨") || weather_.equals("晴天")) {
			imageView_tianqi.setImageResource(R.drawable.qing);
		} else if (weather_.equals("阴") || weather_.equals("多云")
				|| weather_.equals("少云") || weather_.equals("局部多云")) {
			imageView_tianqi.setImageResource(R.drawable.yin);
			System.out.println("今天天气" + weather_ + ";;;;;;;;;;;;;;;;;;;;");
		} else if (weather_.equals("小雨") || weather_.equals("中雨")
				|| weather_.equals("大雨") || weather_.equals("雷阵雨")
				|| weather_.equals("阵雨")) {
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
			if (dayTime.equals("晴") || dayTime.equals("晴转多云")
					|| dayTime.equals("晴转雨") || dayTime.equals("晴天")) {
				map.put("dayTime", R.drawable.qing);
			} else if (dayTime.equals("阴") || dayTime.equals("多云")
					|| dayTime.equals("少云") || dayTime.equals("局部多云")) {
				map.put("dayTime", R.drawable.yin);
			} else if (dayTime.equals("小雨") || dayTime.equals("中雨")
					|| dayTime.equals("大雨") || dayTime.equals("雷阵雨")
					|| dayTime.equals("阵雨")) {
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
					Toast.makeText(Tianqi.this, "您输入的城市不能为空！",
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
			// 1、创建httpget的对象
			HttpGet httpget = new HttpGet(
			// 请求码
					"http://apicloud.mob.com/v1/weather/query?key=13292cda522db&city="
							+ hqshi + "&province=" + hqsheng);
			// 2、创建httpclient的对象
			HttpClient httpclient = new DefaultHttpClient();
			InputStream input = null;

			try {
				// 3、发送GET请求，获取对应的响应
				httpresponse = httpclient.execute(httpget);
				// 4、得到里面的内容
				httpentity = httpresponse.getEntity();
				// 读取服务器响应
				input = httpentity.getContent();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						input));
				String line = "";
				String result = "";
				while ((line = br.readLine()) != null) {
					result = result + line;
				}
				Log.d("请求结果", result);

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
