package com.compitation.ticketsystem.Activity;

import com.compitation.ticketsystem.R;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;

public class ChangePasswordActivity extends Activity {
	private EditText re_acount;
	private EditText re_pass;
	private EditText re_pass_ag;
	private Button ch_ok;
	
	private String s_re_acount;
	private String s_re_pass;
	private String s_pass_ag;
	
	private String WARNTEXT = "有选项没有填写";
	private String ERRORTEXT = "两次填写的密码不一样";
	private String NONETCONNECTED = "无网络连接，请检查网络";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_password);
		
		re_acount = (EditText)findViewById(R.id.re_account);
		re_pass = (EditText)findViewById(R.id.re_password);
		re_pass_ag = (EditText)findViewById(R.id.re_password_ag);
		ch_ok = (Button)findViewById(R.id.ch_ok);
		
		ch_ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				s_re_acount = re_acount.getText().toString();
				s_re_pass = re_pass.getText().toString();
				s_pass_ag = re_pass_ag.getText().toString();
				if (!(TextUtils.isEmpty(s_re_acount))
						&& !(TextUtils.isEmpty(s_re_pass))
						&& !(TextUtils.isEmpty(s_pass_ag))) {
					// 判断两次密码是否一致
					if (s_re_pass.equals(s_pass_ag)) {
						// 判断是否有网
						if (isNetworkConnected()) {

						} else {
							Toast.makeText(ChangePasswordActivity.this, NONETCONNECTED,
									Toast.LENGTH_LONG).show();
						}

					} else {
						Toast.makeText(ChangePasswordActivity.this, ERRORTEXT, Toast.LENGTH_LONG)
								.show();
					}

				} else {
					Toast.makeText(ChangePasswordActivity.this, WARNTEXT, Toast.LENGTH_LONG).show();
				}
			}
		});
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
