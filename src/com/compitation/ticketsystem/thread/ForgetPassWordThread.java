package com.compitation.ticketsystem.thread;

import java.net.URL;

import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;

import com.compitation.ticketsystem.utils.HttpRequest;
import com.compitation.ticketsystem.utils.SystemContent;
import com.comtipation.ticketsystem.model.User;

public class ForgetPassWordThread implements Runnable {
	private Handler handler;
	private User user;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Message msg = handler.obtainMessage();
		try {
			URL forgetUrl = new URL(SystemContent.FIND_PASSWORD + "?name="
					+ user.getUserName() + "&question="
					+ user.getSecurityQuestionNum() + "&anwser="
					+ user.getSecurityQuestionAnwser());
			HttpRequest forgetRequest = HttpRequest.get(forgetUrl)
					.connectTimeout(3000);
			if (forgetRequest.ok()) {
				JSONObject forgetJSON = new JSONObject(forgetRequest.body());
				if (forgetJSON.getString("status").equals("1")) {
					msg.what = 1;// 密保问题正确
				} else {
					msg.what = 2;// 密保问题不正确
				}
			} else {
				// 服务器出错
				msg.what = -1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ForgetPassWordThread(Handler handler, User user) {
		this.handler = handler;
		this.user = user;
	}

}
