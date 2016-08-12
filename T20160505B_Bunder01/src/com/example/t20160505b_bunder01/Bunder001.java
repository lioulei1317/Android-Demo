package com.example.t20160505b_bunder01;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class Bunder001 extends Activity {
	EditText name, passwd;
	RadioButton famle;
	Button dl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bunder001);
		name = (EditText) findViewById(R.id.bunderet001);
		passwd = (EditText) findViewById(R.id.bunderet002);
		famle = (RadioButton) findViewById(R.id.bunderrbtn001);
		dl = (Button) findViewById(R.id.bunderbtn001);

		dl.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Bunder001.this, Bunder003.class);
				Bundle bundle = new Bundle();
				String sex = famle.isChecked() ? "ÄÐ" : "Å®";
				Bunder002 p = new Bunder002(name.getText().toString(), passwd
						.getText().toString(), sex);
				bundle.putSerializable("Bander002", p);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}

}
