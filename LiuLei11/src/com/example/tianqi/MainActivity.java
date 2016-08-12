package com.example.tianqi;

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

import com.example.liulei11.R;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends Activity {
	Spinner shengSpinner = null;
	Spinner shiSpinner = null;
	Button chakan;
	ImageView imageview;
	GridView gridView;
	ArrayAdapter<String> shengAdapter = null;
	ArrayAdapter<String> shiAdapter = null;
	static int shengPosition = 0;
	static int shiposition = 0;
	public String hqsheng;
	public String hqshi;
	Map<String, Object> map;
	// 省级选项值
	private String[] sheng = new String[] { "四川", "广西" };
	// 地级选项值
	private String[][] shi = new String[][] { { "达州", "成都" }, { "南宁", "百色" } };

	Button bn;
	HttpResponse httpresponse;
	HttpEntity httpentity;
	static TextView tianqiqk, riqi, wendu, shidu, fengxiang, weilai;

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			Bundle data = msg.getData();
			String val = data.getString("val");
			Log.i("请求结果--->", val);
			JsonUtil json = new JsonUtil();
			map = json.jiexi(val);
			WeatherInfo weatherInfo = (WeatherInfo) map.get("weatherInfo");
			setdesc(weatherInfo);
			List<Info> list_info = (List<Info>) map.get("list_info");
			grid_fu(list_info);
		};
	};

	private void setdesc(WeatherInfo weatherInfo) {
		// TODO Auto-generated method stub

		riqi.setText(weatherInfo.getDate());
		wendu.setText(weatherInfo.getTemperature());
		fengxiang.setText(weatherInfo.getWind());
		shidu.setText(weatherInfo.getHumidity());
		String weather_ = weatherInfo.getWeather();
		tianqiqk.setText(weather_);
		if (weather_.equals("晴") || weather_.equals("晴转多云")
				|| weather_.equals("晴转雨") || weather_.equals("晴天")) {
			imageview.setImageResource(R.drawable.qing);
		} else if (weather_.equals("阴") || weather_.equals("多云")
				|| weather_.equals("少云") || weather_.equals("局部多云")) {
			imageview.setImageResource(R.drawable.yin);
		} else if (weather_.equals("小雨") || weather_.equals("中雨")
				|| weather_.equals("大雨") || weather_.equals("雷阵雨")
				|| weather_.equals("阵雨")) {
			imageview.setImageResource(R.drawable.xiaoyu1);
		} else {
			imageview.setImageResource(R.drawable.qingzhuanduoyun);
		}

	}

	private void grid_fu(List<Info> list_info) {
		// TODO Auto-generated method stub
		System.out.println(list_info);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 1; i < list_info.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("week", list_info.get(i).getWeek());
			map.put("temperature", list_info.get(i).getTemperature());
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

		SimpleAdapter simple = new SimpleAdapter(MainActivity.this, list,
				R.layout.grid_list, new String[] { "week", "temperature",
						"dayTime" }, new int[] { R.id.grid_week,
						R.id.grid_temperature, R.id.grid_image });

		gridView.setAdapter(simple);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tianqixq);
		bn = (Button) findViewById(R.id.btnchakan);
		tianqiqk = (TextView) findViewById(R.id.jintiantqqktext01);
		riqi = (TextView) findViewById(R.id.riqitext02);
		wendu = (TextView) findViewById(R.id.wendutext03);
		shidu = (TextView) findViewById(R.id.shidutext04);
		fengxiang = (TextView) findViewById(R.id.fengxiangtext05);
		gridView = (GridView) findViewById(R.id.http_grid);
		gridView.getBackground().setAlpha(50);
		imageview = (ImageView) findViewById(R.id.tqimageview);
		chakan = (Button) findViewById(R.id.btnchakan);
		chakan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hqsheng = sheng[shengPosition];
				hqshi = shi[shengPosition][shiposition];
				// TODO Auto-generated method stub
				Toast.makeText(
						MainActivity.this,
						"你选择的：" + sheng[shengPosition] + "  "
								+ shi[shengPosition][shiposition],
						Toast.LENGTH_SHORT).show();
				new Thread(network).start();
				System.out.println("市----------------------------" + hqshi);

			}
		});
		setSpinner();

	}

	Runnable network = new Runnable() {

		@Override
		public void run() {
			HttpGet httpget = new HttpGet(
					"http://apicloud.mob.com/v1/weather/query?key=13292cda522db&city="
							+ hqshi + "&province=" + hqsheng);
			System.out.println("+++++++++++++++++++++++++" + hqshi);
			HttpClient httpclient = new DefaultHttpClient();
			InputStream input = null;

			try {
				httpresponse = httpclient.execute(httpget);
				httpentity = httpresponse.getEntity();
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

	private void setSpinner() {
		shengSpinner = (Spinner) findViewById(R.id.spinner_sheng);
		shiSpinner = (Spinner) findViewById(R.id.spinner_shi);
		chakan = (Button) findViewById(R.id.btnchakan);
		// 绑定适配器和值
		shengAdapter = new ArrayAdapter<String>(MainActivity.this,
				android.R.layout.simple_spinner_item, sheng);
		shengSpinner.setAdapter(shengAdapter);
		shengSpinner.setSelection(0, true);

		shiAdapter = new ArrayAdapter<String>(MainActivity.this,
				android.R.layout.simple_spinner_item, shi[0]);
		shiSpinner.setAdapter(shiAdapter);
		shiSpinner.setSelection(0, true);
		shengSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				shiAdapter = new ArrayAdapter<String>(MainActivity.this,
						android.R.layout.simple_spinner_item, shi[position]);
				// 设置二级下拉列表的选项内容适配器
				shiSpinner.setAdapter(shiAdapter);
				shengPosition = position; // 记录当前省级序号，留给下面修改县级适配器时用
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		shiSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				shiAdapter = new ArrayAdapter<String>(MainActivity.this,
						android.R.layout.simple_spinner_item, shi[position]);
				shiposition = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

	}

}
