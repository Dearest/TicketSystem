package com.compitation.ticketsystem.Activity;

import com.compitation.ticketsystem.R;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;

public class View1 extends Activity {
	private TextView name;
	private TextView score;
	
	private String s_name;
	private String s_score;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view1);
		
		name = (TextView)findViewById(R.id.view_name);
		score = (TextView)findViewById(R.id.view_score);
	}


}
