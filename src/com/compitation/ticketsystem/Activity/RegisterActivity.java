package com.compitation.ticketsystem.Activity;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import com.compitation.ticketsystem.R;
import com.compitation.ticketsystem.Idispatch.ILoginAndRegisterDispatch;
import com.compitation.ticketsystem.utils.SysApplication;
import com.comtipation.ticketsystem.model.User;

public class RegisterActivity extends Activity {
	private EditText username;
	private EditText password;
	private EditText onfirmpassword;
	private EditText answer;
	private EditText carnum;
	private Spinner security_question;
	private Button submit;
	private RadioGroup sex;
	private RadioButton male;
	private RadioButton femal;

	private String s_username;
	private String s_password;
	private String s_password_ag;
	private String s_answer;
	private String s_carnum;
	private String s_sex;

	private List<String> questionlist = new ArrayList<String>();
	private ArrayAdapter<String> adapter;
	private final String WARNTEXT = "除车牌号以外，其余都必须填写";
	private final String ERRORTEXT = "两次填写的密码不一样";
	private final String NONETCONNECTED = "无网络连接，请检查网络";
	private final String SUCCESS = "注册成功";
	private final String REPEAT = "用户名重复，请重新注册";
	private ILoginAndRegisterDispatch register;
	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		Looper looper = Looper.myLooper();
		handler = new RegisterHandler(looper);
		username = (EditText) findViewById(R.id.re_account);
		password = (EditText) findViewById(R.id.re_password);
		onfirmpassword = (EditText) findViewById(R.id.re_password_ag);
		carnum = (EditText) findViewById(R.id.car_account);
		answer = (EditText) findViewById(R.id.answer);
		security_question = (Spinner) findViewById(R.id.security_question);
		submit = (Button) findViewById(R.id.register_ok);
		sex = (RadioGroup) findViewById(R.id.sex);
		male = (RadioButton) findViewById(R.id.male);
		femal = (RadioButton) findViewById(R.id.female);

		questionType();// 密保问题下拉菜单

		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				confirm();
			}

		});
	}

	/**
	 * 设置密保问题下拉菜单
	 */
	private void questionType() {
		// TODO Auto-generated method stub
		questionlist.add("您最喜欢的一组数字是？");
		questionlist.add("您的出生地是？");
		questionlist.add("您的学号（或工号）是？");
		questionlist.add("您父亲的姓名是？");
		questionlist.add("您母亲的姓名是？");
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, questionlist);
		// 设置下拉菜单样式
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		security_question.setAdapter(adapter);
		security_question
				.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent, View v,
							int position, long arg3) {
						// TODO Auto-generated method stub
						parent.setVisibility(View.VISIBLE);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						arg0.setVisibility(View.VISIBLE);
					}

				});

	}

	protected void confirm() {
		// TODO Auto-generated method stub
		// 得到控件值
		s_username = username.getText().toString();
		s_password = password.getText().toString();
		s_password_ag = onfirmpassword.getText().toString();
		s_answer = answer.getText().toString();
		s_carnum = carnum.getText().toString();

		Log.i("Flag", "_______________________________");

		// 得到性别
		if (male.isChecked()) {
			s_sex = "男";
			Log.i("Flag", "you have checked the male");
		} else if (femal.isChecked()) {
			s_sex = "女";
			Log.i("Flag", "you have checked the female");
		}

		// 判断重要项是否为空
		if (!(TextUtils.isEmpty(s_username))
				&& !(TextUtils.isEmpty(s_password))
				&& !(TextUtils.isEmpty(s_password_ag))
				&& !(TextUtils.isEmpty(s_answer))) {
			// 判断两次密码是否一致
			if (s_password.equals(s_password_ag)) {
				// 判断是否有网
				if (isNetworkConnected()) {
					User user = new User();
					user.setUserName(s_username);
					user.setPassWord(s_password);
					user.setCarNumber(s_carnum);
					user.setSecurityQuestionNum(security_question
							.getSelectedItemPosition());
					user.setSecurityQuestionAnwser(s_answer);
					register.register(handler, user);
				} else {
					Toast.makeText(RegisterActivity.this, NONETCONNECTED,
							Toast.LENGTH_LONG).show();
				}

			} else {
				Toast.makeText(RegisterActivity.this, ERRORTEXT,
						Toast.LENGTH_LONG).show();
			}

		} else {
			Toast.makeText(RegisterActivity.this, WARNTEXT, Toast.LENGTH_LONG)
					.show();
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

	/**
	 * 内部类 处理注册页面的消息
	 * 
	 * @author HANCHEN
	 * 
	 */
	class RegisterHandler extends Handler {
		public RegisterHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				Toast.makeText(RegisterActivity.this, SUCCESS,
						Toast.LENGTH_LONG).show();
				break;
			case -1:
				Toast.makeText(RegisterActivity.this, REPEAT, Toast.LENGTH_LONG)
						.show();
				break;
			case 2:
				Toast.makeText(RegisterActivity.this, "服务器出错，请稍候再试",
						Toast.LENGTH_LONG).show();
				break;
			default:
				break;
			}
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
