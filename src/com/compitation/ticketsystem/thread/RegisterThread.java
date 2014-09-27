package com.compitation.ticketsystem.thread;

import java.net.URL;

import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;

import com.compitation.ticketsystem.utils.HttpRequest;
import com.compitation.ticketsystem.utils.SystemContent;
import com.comtipation.ticketsystem.model.User;

public class RegisterThread implements Runnable {
	private Handler handler;
	private User user;

	@Override
	public void run() {
		Message msg = handler.obtainMessage();
		try {
			URL registerUrl = new URL(SystemContent.REGISTER + "?name="
					+ user.getUserName() + "&password=" + user.getPassWord()
					+ "&carnum=" + user.getCarNumber() + "&sex="
					+ user.getSex() + "&phone=" + user.getPhoneNumber()
					+ "&question=" + user.getSecurityQuestionNum() + "&answer="
					+ user.getSecurityQuestionAnwser());
			HttpRequest registerRequest = HttpRequest.get(registerUrl)
					.connectTimeout(3000);
			if (registerRequest.ok()) {
				JSONObject registerJson = new JSONObject(registerRequest.body());
				if (registerJson.getString("status").equals("-1")) {
					// 用户已经注册
					msg.what = -1;
				} else if (registerJson.getString("status").equals("1")) {
					// 注册成功
					msg.what = 1;
				}
			} else {
				// 网络问题 注册失败
				msg.what = 2;
			}
			handler.sendMessage(msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public RegisterThread(Handler handler, User user) {
		this.user = user;
	}

}
