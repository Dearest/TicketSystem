package com.compitation.ticketsystem.Activity;

import com.compitation.ticketsystem.R;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;

public class LoginActivity extends Activity {
	private EditText username;
	private EditText password;
	private TextView ticket_system;
	private TextView forgetorregister;
	private Button login;
	private String userString;
	private String pwdString;
	private LinearLayout rl;
	private Animation my_Translate;		// 位移动画
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		//系统title操作，添加字体库
		ticket_system = (TextView) findViewById(R.id.ticket_system);
		Typeface face = Typeface.createFromAsset(getAssets(),
				"fonts/yinbixinshu.TTF");
		ticket_system.setTypeface(face);
		// 设置输入框载人时的动画
		rl = (LinearLayout)findViewById(R.id.rl);
		anim();
		rl.startAnimation(my_Translate);	
        //忘记密码与注册点击，设置下划线
		forgetorregister = (TextView) findViewById(R.id.forgetorregister);
		forgetorregister.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		forgetorregister.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(LoginActivity.this, ForgetAndRegister.class));
			}
		});
		//输入框以及登录按钮
		username = (EditText) findViewById(R.id.user_account);
		password = (EditText) findViewById(R.id.password);
		login = (Button) findViewById(R.id.login);
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				userString = username.getText().toString();
				pwdString = password.getText().toString();
				
				if (userString.equals("") || pwdString.equals("")) {
					Toast.makeText(LoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT)
							.show();
				} else {
					if (isNetworkConnected()) {
						Intent intent = new Intent(LoginActivity.this,MainPage.class);
						startActivity(intent);
						 
					} else {
						Toast.makeText(LoginActivity.this, "无网络连接，请检查网络", Toast.LENGTH_LONG)
								.show();
					}
				}
			}

		});
	}

	/**
	 * 载入动画
	 */
	private void anim() {
//		// TODO Auto-generated method stub
		my_Translate = AnimationUtils.loadAnimation(this, R.anim.my_translate);
		AnimationUtils.loadAnimation(this, R.anim.my_rotate);
	}
	
	/**
	 * 判断是否连接到网络
	 * 
	 * @return true 能连接到网络 false 不能连接到网络
	 */
	public boolean isNetworkConnected() {
		ConnectivityManager con = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
		boolean wifi = con.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.isConnectedOrConnecting();
		boolean internet = con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.isConnectedOrConnecting();
		if (wifi | internet) {
			return true;
		} else {
			return false;
		}

	}

}
