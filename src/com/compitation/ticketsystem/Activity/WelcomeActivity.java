package com.compitation.ticketsystem.Activity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.compitation.ticketsystem.R;
import com.compitation.ticketsystem.thread.MainPageThread;
import com.compitation.ticketsystem.utils.SystemContent;

public class WelcomeActivity extends Activity {

	
	private Handler handler;
	private SharedPreferences mySharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		mySharedPreferences = getSharedPreferences(
				SystemContent.PREFERNCE_NAME, Activity.MODE_PRIVATE);
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 5:
					Log.i("Flag", "准备跳转到主界面");
					try {
						startActivity(new Intent(WelcomeActivity.this, ViewPagerActivity.class));
						finish();
					} catch (Exception e) {
						// TODO: handle exception
					}
					break;
				default:
					break;
				}
			}
		};

		if (mySharedPreferences.getBoolean("isLogin", false)) {
			MainPageThread mainPageThread = new MainPageThread(
					mySharedPreferences, handler,
					mySharedPreferences.getString("userId", ""));
			ExecutorService executor = Executors.newSingleThreadExecutor();
			executor.execute(mainPageThread);
		} else {
			startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
			finish();
		}

	}

}
