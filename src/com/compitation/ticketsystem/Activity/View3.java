package com.compitation.ticketsystem.Activity;

import com.compitation.ticketsystem.R;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.Typeface;

public class View3 extends Activity {
	private TextView text1;
	private TextView text2;
	private TextView text3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view3);

		text1 = (TextView) findViewById(R.id.text1);
		text2 = (TextView) findViewById(R.id.text2);
		text3 = (TextView) findViewById(R.id.text3);

		Typeface face = Typeface.createFromAsset(getAssets(),
				"fonts/huakangwawa.TTF");
		text1.setTypeface(face);
		text2.setTypeface(face);
		text3.setTypeface(face);
	}

}
