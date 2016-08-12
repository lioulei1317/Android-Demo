package com.example.liulei0901;

import java.net.URL;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	TextView dqkl;
	EditText jiangjl, kl;
	Button dl;
	int k = 0;
	String[] s = { "KL1", "KL2", "KL3", "KL4", "KL5" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dqkl = (TextView) findViewById(R.id.dqkl);
		jiangjl = (EditText) findViewById(R.id.jiangjl);
		kl = (EditText) findViewById(R.id.kl);
		dl = (Button) findViewById(R.id.dl);
		new myclass(this).execute(new URL[0]);
		dl.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				if ((jiangjl.getText().toString().equals("JJL1"))
						&& (kl.getText().toString().equals(dqkl.getText()
								.toString()))) {
					Toast.makeText(MainActivity.this, "µÇÂ½³É¹¦",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(MainActivity.this, "µÇÂ¼Ê§°Ü",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	class myclass extends AsyncTask<URL, Integer, String> {
		Context mContext;

		public myclass(Context arg2) {
		}

		protected String doInBackground(URL[] paramArrayOfURL) {
			publishProgress(k);
			while (true)
				try {
					Thread.sleep(10000);
					k %=s.length;
					publishProgress(k);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}

		protected void onPostExecute(String paramString) {
			dqkl.setText(s[k]);
			
			
		}

		protected void onProgressUpdate(Integer[] paramArrayOfInteger) {
			dqkl.setText(s[k]);
			k = 1 + k;
		}
	}

}
