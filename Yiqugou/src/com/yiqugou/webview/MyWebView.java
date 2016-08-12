package com.yiqugou.webview;

import com.example.yiqugou.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebView extends Activity {
	private String url = "https://item.taobao.com/item.htm?id=532050870200&ali_refid=a3_430582_1006:1121376662:N:秒杀:4c76e4955266ce51f9936898feb48fa7&ali_trackid=1_4c76e4955266ce51f9936898feb48fa7&spm=a230r.1.14.6.hZqNe7#detail/";
	WebView webView;
	private ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.web);
		// Uri uri = Uri.parse(url);// url为你要连接的地址
		// Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		// startActivity(intent);
		init();
	}

	private void init() {
		webView = (WebView) findViewById(R.id.webView);
		// WebView加载本地资源
		// webView.loadUrl("file:///android_asset/example.html");
		// WebView加载web资源
		webView.loadUrl(url);
		// 覆盖webview默认通过第三方或者是系统浏览器打开的行为，使得网页可以在webview中打开
		webView.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// 返回值是true时，控制网页在webview中打开，为false调用系统浏览器或第三方浏览器去打开
				view.loadUrl(url);
				return true;
			}
			// WebViewClient帮助webview去处理一些页面控制和请求通知

		});
		// 启用支持JavaScript
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		// webview加载页面优先使用缓存加载
		settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		webView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
				// newProgress 1-100之间的整数
				if (newProgress == 100) {
					// 网页加载完毕，关闭ProgressDialog
					closeDialog();
				} else {
					// 网页正在加载,打开ProgressDialog
					openDialog(newProgress);
				}
			}

			private void closeDialog() {
				// TODO Auto-generated method stub
				if (dialog != null && dialog.isShowing()) {
					dialog.dismiss();
					dialog = null;
				}
			}

			private void openDialog(int newProgress) {
				// TODO Auto-generated method stub
				if (dialog == null) {
					dialog = new ProgressDialog(MyWebView.this);
					dialog.setTitle("亲，正在努力加载中，请稍等!");
					// 设置进度条样式
					dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
					dialog.setProgress(newProgress);
					dialog.show();
				} else {
					dialog.setProgress(newProgress);
				}
			}
		});
	}

	// 改写手机物理按键--返回的逻辑
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// Toast.makeText(this, webView.getUrl(),
			// Toast.LENGTH_SHORT).show();
			if (webView.canGoBack()) {
				webView.goBack();// 返回上一页面
				return true;
			} else {
				System.exit(0);// 退出程序
			}
		}

		return super.onKeyDown(keyCode, event);
	}
}
