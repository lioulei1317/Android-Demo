package com.example.volleytest;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 使用Get方式请求数据返回StringRequest对象
 * 
 * 当想返回String类型的请求结果数据或者不清楚返回什么类型时可以用StringRequest对象。
 * 
 * 下面使用Get请求方式返回一个String类型的手机归属地信息。
 * 
 * @author Administrator
 * 
 */
public class MainActivity extends Activity {
	Button button1, button2, button3, button4, button5, button6;
	ImageView image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button1 = (Button) findViewById(R.id.Button1);
		button2 = (Button) findViewById(R.id.Button2);
		button3 = (Button) findViewById(R.id.Button3);
		button4 = (Button) findViewById(R.id.Button4);
		button5 = (Button) findViewById(R.id.Button5);
		button6 = (Button) findViewById(R.id.Button6);
		image = (ImageView) findViewById(R.id.image);
		button1.setOnClickListener(listener);
		button2.setOnClickListener(listener);
		button3.setOnClickListener(listener);
		button4.setOnClickListener(listener);
		button5.setOnClickListener(listener);
		button6.setOnClickListener(listener);
	}

	/**
	 * new StringRequest(int method,String url,Listener listener,ErrorListener
	 * errorListener)
	 * 
	 * method：请求方式，Get请求为Method.GET;Post请求为Method.POST;
	 * 
	 * url：请求地址
	 * 
	 * listener：请求成功后的回调 errorListener：请求失败的回调
	 */
	private void volleyGet1() {
		String url = "https://www.baidu.com/";
		System.out.println("asd==" + url);
		StringRequest request = new StringRequest(Method.GET, url,
				new Listener<String>() {
					@Override
					public void onResponse(String s) {// s为请求返回的字符串数据
						Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG)
								.show();
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError volleyError) {
						Toast.makeText(MainActivity.this,
								volleyError.toString(), Toast.LENGTH_LONG)
								.show();
					}
				});
		// 设置请求的Tag标签，可以在全局请求队列中通过Tag标签进行请求的查找
		request.setTag("testGet");
		// 将请求加入全局队列中
		MyApplication.getHttpQueues().add(request);
	}

	/**
	 * new JsonObjectRequest(int method,String url,JsonObject
	 * jsonObject,Listener listener,ErrorListener errorListener)
	 * method：请求方式，Get请求为Method.GET，Post请求为Method.POST url：请求地址
	 * JsonObject：Json格式的请求参数。如果使用的是Get请求方式，请求参数已经包含在url中，所以可以将此参数置为null
	 * listener：请求成功后的回调 errorListener：请求失败的回调
	 */
	private void volleyGet2() {
		String url = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=218.4.255.255";
		JsonObjectRequest request = new JsonObjectRequest(Method.GET, url,
				null, new Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject jsonObject) {// jsonObject为请求返回的Json格式数据
						Toast.makeText(MainActivity.this,
								jsonObject.toString(), Toast.LENGTH_LONG)
								.show();
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError volleyError) {
						Toast.makeText(MainActivity.this,
								volleyError.toString(), Toast.LENGTH_LONG)
								.show();
					}
				});

		// 设置请求的Tag标签，可以在全局请求队列中通过Tag标签进行请求的查找
		request.setTag("testGet");
		// 将请求加入全局队列中
		MyApplication.getHttpQueues().add(request);
	}

	/**
	 * 使用Post方式返回String类型的请求结果数据
	 * 
	 * new StringRequest(int method,String url,Listener listener,ErrorListener
	 * errorListener) method：请求方式，Get请求为Method.GET，Post请求为Method.POST url：请求地址
	 * listener：请求成功后的回调 errorListener：请求失败的回调
	 */
	private void volleyPost3() {
		String url = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm";
		StringRequest request = new StringRequest(Method.POST, url,
				new Listener<String>() {
					@Override
					public void onResponse(String s) {// s为请求返回的字符串数据
						Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG)
								.show();
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError volleyError) {
						Toast.makeText(MainActivity.this,
								volleyError.toString(), Toast.LENGTH_LONG)
								.show();
					}
				}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				// 将请求参数名与参数值放入map中
				map.put("tel", "15850781443");
				return map;
			}
		};
		// 设置请求的Tag标签，可以在全局请求队列中通过Tag标签进行请求的查找
		request.setTag("testPost");
		// 将请求加入全局队列中
		MyApplication.getHttpQueues().add(request);
	}

	/**
	 * 使用Post方式返回JsonObject类型的请求结果数据
	 * 
	 * new JsonObjectRequest(int method,String url,JsonObject
	 * jsonObject,Listener listener,ErrorListener errorListener)
	 * method：请求方式，Get请求为Method.GET，Post请求为Method.POST url：请求地址
	 * JsonObject：Json格式的请求参数。如果使用的是Get请求方式，请求参数已经包含在url中，所以可以将此参数置为null
	 * listener：请求成功后的回调 errorListener：请求失败的回调
	 */
	private void volleyPost4() {
		String url = "http://www.kuaidi100.com/query";
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", "yuantong");
		map.put("postid", "229728279823");
		// 将map转化为JSONObject对象
		JSONObject jsonObject = new JSONObject(map);

		JsonObjectRequest request = new JsonObjectRequest(Method.POST, url,
				jsonObject, new Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject jsonObject) {// jsonObject为请求返回的Json格式数据
						Toast.makeText(MainActivity.this,
								jsonObject.toString(), Toast.LENGTH_LONG)
								.show();
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError volleyError) {
						Toast.makeText(MainActivity.this,
								volleyError.toString(), Toast.LENGTH_LONG)
								.show();
					}
				});
		// 设置请求的Tag标签，可以在全局请求队列中通过Tag标签进行请求的查找
		request.setTag("testPost");
		// 将请求加入全局队列中
		MyApplication.getHttpQueues().add(request);
	}

	/**
	 * 通过Volley加载网络图片
	 * 
	 * new ImageRequest(String url,Listener listener,int maxWidth,int
	 * maxHeight,Config decodeConfig,ErrorListener errorListener) url：请求地址
	 * listener：请求成功后的回调 maxWidth、maxHeight：设置图片的最大宽高，如果均设为0则表示按原尺寸显示
	 * decodeConfig
	 * ：图片像素的储存方式。Config.RGB_565表示每个像素占2个字节，Config.ARGB_8888表示每个像素占4个字节等。
	 * errorListener：请求失败的回调
	 */
	private void loadImageByVolley() {
		String url = "http://s2.nuomi.bdimg.com/upload/deal/2014/1/V_L/623682-1391756281052.jpg";
		ImageRequest request = new ImageRequest(url, new Listener<Bitmap>() {
			@Override
			public void onResponse(Bitmap bitmap) {
				image.setImageBitmap(bitmap);
			}
		}, 0, 0, Config.RGB_565, new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {
				image.setImageResource(R.drawable.ic_launcher);
			}
		});

		// 设置请求的Tag标签，可以在全局请求队列中通过Tag标签进行请求的查找
		request.setTag("loadImage");
		// 通过Tag标签取消请求队列中对应的全部请求
		MyApplication.getHttpQueues().add(request);
	}

	/**
	 * 通过ImageLoader加载及缓存网络图片 　　　* new ImageLoader(RequestQueue queue,ImageCache
	 * imageCache) queue：请求队列 imageCache：一个用于图片缓存的接口，一般需要传入它的实现类
	 * 
	 * getImageListener(ImageView view, int defaultImageResId, int
	 * errorImageResId) view：ImageView对象 defaultImageResId：默认的图片的资源Id
	 * errorImageResId：网络图片加载失败时显示的图片的资源Id
	 */
	private void loadImageWithCache() {
		String url = "http://zhanhui.3158.cn/data/attachment/exhibition/data/attachment/exhibition/article/2015/12/17/4d80cdd4500e52ff5c7fbd600c0a7a84.jpg";
		ImageLoader loader = new ImageLoader(MyApplication.getHttpQueues(),
				new BitmapCache());
		ImageListener listener = loader.getImageListener(image,
				R.drawable.ic_launcher, R.drawable.ic_launcher);
		// 加载及缓存网络图片
		loader.get(url, listener);
	}

	// @Override
	// protected void onStop() {
	// super.onStop();
	// //通过Tag标签取消请求队列中对应的全部请求
	// MyApplication.getHttpQueues().cancelAll("abcGet");
	// }

	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch (arg0.getId()) {
			case R.id.Button1:
				volleyGet1();
				break;
			case R.id.Button2:
				volleyGet2();
				break;
			case R.id.Button3:
				volleyPost3();
				break;
			case R.id.Button4:
				volleyPost4();
				break;
			case R.id.Button5:
				loadImageByVolley();
				break;
			case R.id.Button6:
				loadImageWithCache();
				break;
			}
		}
	};

}
