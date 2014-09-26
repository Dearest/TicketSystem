package com.compitation.ticketsystem.Activity;

import com.compitation.ticketsystem.R;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.Typeface;

public class View2 extends Activity {
	private TextView temp;
	private TextView sign;
	private TextView cload_direc;
	private TextView cload_scale;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view2);

		temp = (TextView) findViewById(R.id.temp);
		sign = (TextView) findViewById(R.id.sign);
		cload_direc = (TextView) findViewById(R.id.cloud_direction);
		cload_scale = (TextView) findViewById(R.id.cloud_scale);

		// 系统title操作，添加字体库
		Typeface face = Typeface.createFromAsset(getAssets(),
				"fonts/huakangwawa.TTF");
		temp.setTypeface(face);
		sign.setTypeface(face);
		cload_direc.setTypeface(face);
		cload_scale.setTypeface(face);
	}

}
