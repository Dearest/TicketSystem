package com.compitation.ticketsystem.Activity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.compitation.ticketsystem.R;
import com.compitation.ticketsystem.Idispatch.ILoginAndRegisterDispatch;
import com.compitation.ticketsystem.dispatchImpl.LoginAndRegisterDispatchImpl;
import com.compitation.ticketsystem.thread.MainPageThread;
import com.compitation.ticketsystem.utils.MD5Helper;
import com.compitation.ticketsystem.utils.SysApplication;
import com.compitation.ticketsystem.utils.SystemContent;

public class LoginActivity extends Activity {
	private EditText username;
	private EditText password;
	private TextView ticket_system;
	private TextView forgetorregister;
	private Button login;
	private String userName;
	private String passWord;
	private LinearLayout rl;
	private Animation my_Translate; // 位移动画
	private ILoginAndRegisterDispatch loginDispatch;
	private ProgressDialog progressDialog;
	private LoginHandler handler;
	private SharedPreferences mySharedPreferences;
	private SharedPreferences.Editor editor;
	private long exitTime = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Looper looper = Looper.myLooper();
		handler = new LoginHandler(looper);
		mySharedPreferences = getSharedPreferences(
				SystemContent.PREFERNCE_NAME, Activity.MODE_PRIVATE);
		editor = mySharedPreferences.edit();

		// 实例化调度层实现类
		loginDispatch = new LoginAndRegisterDispatchImpl();
		// 系统title操作，添加字体库
		ticket_system = (TextView) findViewById(R.id.ticket_system);
		Typeface face = Typeface.createFromAsset(getAssets(),
				"fonts/yinbixinshu.TTF");
		ticket_system.setTypeface(face);
		// 设置输入框载人时的动画
		rl = (LinearLayout) findViewById(R.id.rl);
		anim();
		rl.startAnimation(my_Translate);
		// 忘记密码与注册点击，设置下划线
		forgetorregister = (TextView) findViewById(R.id.forgetorregister);
		forgetorregister.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		forgetorregister.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(LoginActivity.this,
						ForgetAndRegister.class));
			}
		});
		// 输入框以及登录按钮
		username = (EditText) findViewById(R.id.user_account);
		password = (EditText) findViewById(R.id.password);
		login = (Button) findViewById(R.id.login);
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				userName = username.getText().toString();
				passWord = password.getText().toString();
				Log.i("Flag", userName);
				passWord = MD5Helper.getMD5(passWord);
				if (userName.equals("") || passWord.equals("")) {
					Toast.makeText(LoginActivity.this, "用户名或密码不能为空",
							Toast.LENGTH_SHORT).show();
				} else {
					if (isNetworkConnected()) {
						// 在这里执行后续操作
						loginDispatch.login(mySharedPreferences, handler,
								userName, passWord);
						progressDialog = ProgressDialog.show(LoginActivity.this, "Loading...",
								"Please wait...");
					} else {
						Toast.makeText(LoginActivity.this, "无网络连接，请检查网络",
								Toast.LENGTH_LONG).show();
					}
				}
			}

		});
	}

	/**
	 * 载入动画
	 */
	private void anim() {
		my_Translate = AnimationUtils.loadAnimation(this, R.anim.my_translate);
		AnimationUtils.loadAnimation(this, R.anim.my_rotate);
	}

	/**
	 * 判断是否连接到网络 这段代码不能复用
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

	// 内部类 Handler
	class LoginHandler extends Handler {
		private ExecutorService singleThread = Executors
				.newSingleThreadExecutor();

		public LoginHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			// 在这里处理消息
			switch (msg.what) {
			case 1:
				MainPageThread mainPageThread = new MainPageThread(
						mySharedPreferences, handler,
						mySharedPreferences.getString("userId", ""));
				singleThread.execute(mainPageThread);
				Log.i("Flag", "LoginActivity 登录成功");
				progressDialog.dismiss();
				Intent intent = new Intent(LoginActivity.this,
						ViewPagerActivity.class);
				startActivity(intent);
				finish();

				break;
			case 2:
				// 登录失败 用户名或密码错误
				progressDialog.dismiss();
				Toast.makeText(LoginActivity.this, "用户名或密码错误",
						Toast.LENGTH_LONG).show();
				break;
			case 3:
				// 登录失败 服务器出错
				progressDialog.dismiss();
				Toast.makeText(LoginActivity.this, "服务器出错，请稍候再试",
						Toast.LENGTH_LONG).show();
				break;
			case 4:
				progressDialog.dismiss();
				Toast.makeText(LoginActivity.this, "获取用户信息失败，请稍候再试",
						Toast.LENGTH_LONG).show();
				break;
			default:
				progressDialog.dismiss();
				break;
			}
		}
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "再按一次返回键退出程序",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				finish();
				SysApplication.getInstance().exit();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
