package com.compitation.ticketsystem.Activity;

import com.compitation.ticketsystem.R;
import com.compitation.ticketsystem.R.layout;
import com.compitation.ticketsystem.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class View1 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view1, menu);
		return true;
	}

}
