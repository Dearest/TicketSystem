package com.compitation.ticketsystem.Activity;

import com.compitation.ticketsystem.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
	}

}
