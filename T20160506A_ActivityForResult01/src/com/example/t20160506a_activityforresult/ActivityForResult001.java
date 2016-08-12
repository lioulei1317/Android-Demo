package com.example.t20160506a_activityforresult;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ActivityForResult001 extends Activity {
	EditText city;
	Button bn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activityforresult001);
		bn=(Button) findViewById(R.id.activityforbtn001);
		city=(EditText) findViewById(R.id.activityforett001);
		bn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ActivityForResult001.this,ActivityForResult002.class);
				//启动指定的Activity并等待返回结果
				//其中33是请求码，用于标识该请求
				startActivityForResult(intent, 33);
			}
		});
		
	}

	// 重写该方法，该方法以回调的方式来获取指定Activity
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		// 当requestCode=33，resultCode=55，也就是处理特定的结果
		if (requestCode == 33 && resultCode == 55) {
			// 取出Intent里的Extras数据
			Bundle data = intent.getExtras();
			// 取出Bundle中数据
			String resultCity = data.getString("city");
			// 修改city文本框的内容
			city.setText(resultCity);
		}
	}
}
