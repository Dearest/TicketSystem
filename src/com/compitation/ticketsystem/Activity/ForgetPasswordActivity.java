package com.compitation.ticketsystem.Activity;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.compitation.ticketsystem.R;
import com.compitation.ticketsystem.Idispatch.IForgetPassWordDispatch;
import com.compitation.ticketsystem.dispatchImpl.ForgetPassWordDispatchImpl;
import com.compitation.ticketsystem.utils.SysApplication;
import com.comtipation.ticketsystem.model.User;

public class ForgetPasswordActivity extends Activity {
	private EditText user_name, answer;
	private RadioGroup question;
	private RadioButton ques1, ques2, ques3, ques4, ques5;
	private Button btn_find;

	private String s_user_name;
	private String s_question; // 获得问题号
	private String s_answer;
	private static String WARNTEXT = "有选项要填写";
	private static String NONETCONNECTED = "无网络连接，请检查网络";
	private IForgetPassWordDispatch forgetPassword;
	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forget_password);
		forgetPassword = new ForgetPassWordDispatchImpl();
		Looper looper = Looper.myLooper();
		handler = new ForgetPasswordHandler(looper);

		user_name = (EditText) findViewById(R.id.find_account);
		answer = (EditText) findViewById(R.id.find_answer);
		question = (RadioGroup) findViewById(R.id.find_question_group);
		ques1 = (RadioButton) findViewById(R.id.question1);
		ques2 = (RadioButton) findViewById(R.id.question2);
		ques3 = (RadioButton) findViewById(R.id.question3);
		ques4 = (RadioButton) findViewById(R.id.question4);
		ques5 = (RadioButton) findViewById(R.id.question5);
		btn_find = (Button) findViewById(R.id.find_ok);

		btn_find.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				FindPassword();
			}
		});
	}

	protected void FindPassword() {
		// TODO Auto-generated method stub
		s_user_name = user_name.getText().toString();
		s_answer = answer.getText().toString();

		if (ques1.isChecked()) {

		} else if (ques2.isChecked()) {

		} else if (ques3.isChecked()) {

		} else if (ques4.isChecked()) {

		} else if (ques5.isChecked()) {

			// 判断重要项是否为空
			if (!(TextUtils.isEmpty(s_user_name))
					&& !(TextUtils.isEmpty(s_answer))) {
				// 判断是否有网
				if (isNetworkConnected()) {
					// 缺少密保问题序号
					User user = new User();
					user.setUserName(s_user_name);
					user.setSecurityQuestionAnwser(s_answer);
					forgetPassword.forget(handler, user);
				} else {
					Toast.makeText(ForgetPasswordActivity.this, NONETCONNECTED,
							Toast.LENGTH_LONG).show();
				}

			} else {
				Toast.makeText(ForgetPasswordActivity.this, WARNTEXT,
						Toast.LENGTH_LONG).show();
			}
		}

	}

	class ForgetPasswordHandler extends Handler {
		public ForgetPasswordHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				// 密保问题答案正确可以修改密码
				break;
			case -1:
				// 服务器出错
				Toast.makeText(ForgetPasswordActivity.this, "服务器出错，请稍候再试",
						Toast.LENGTH_LONG).show();
			case 2:
				Toast.makeText(ForgetPasswordActivity.this, "密保答案不正确",
						Toast.LENGTH_LONG).show();
			default:
				break;
			}
		}
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
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		long exitTime = 0;
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
