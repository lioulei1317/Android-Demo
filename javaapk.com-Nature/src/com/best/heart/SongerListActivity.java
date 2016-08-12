package com.best.heart;

import com.best.heart.adapter.SongerAdapter;
import com.best.heart.tool.MusicLoader;
import com.example.nature.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Window;
import android.widget.ListView;

public class SongerListActivity extends Activity {
	ListView songer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_songer_list);
		songer = (ListView) findViewById(R.id.songers);
		songer.setAdapter(new SongerAdapter(this,MusicLoader.getMusicList()));
	}
}
