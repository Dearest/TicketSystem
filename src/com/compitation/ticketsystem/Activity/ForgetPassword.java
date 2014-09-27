package com.compitation.ticketsystem.Activity;

import com.compitation.ticketsystem.R;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class ForgetPassword extends Activity {
	private EditText user_name, answer;
	private RadioGroup question;
	private RadioButton ques1, ques2, ques3, ques4, ques5;
	private Button btn_find;

	private String s_user_name;
	private String s_question; // 获得问题号
	private String s_answer;

	private String WARNTEXT = "有选项要填写";
	private String NONETCONNECTED = "无网络连接，请检查网络";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forget_password);

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
			
		}

		// 判断重要项是否为空
		if (!(TextUtils.isEmpty(s_user_name)) && !(TextUtils.isEmpty(s_answer))) {

			// 判断是否有网
			if (isNetworkConnected()) {
				startActivity(new Intent(ForgetPassword.this,
						ChangePasswordActivity.class));

			} else {
				Toast.makeText(ForgetPassword.this, NONETCONNECTED,
						Toast.LENGTH_LONG).show();
			}

		} else {
			Toast.makeText(ForgetPassword.this, WARNTEXT, Toast.LENGTH_LONG)
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

}
