package com.compitation.ticketsystem.thread;

import java.net.URL;

import org.json.JSONObject;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.compitation.ticketsystem.utils.HttpRequest;
import com.compitation.ticketsystem.utils.SystemContent;

public class LoginThread implements Runnable {

	private String userName;
	private String passWord;
	private Handler handler;
	private SharedPreferences mySharedPreferences;
	private SharedPreferences.Editor editor;

	public LoginThread(SharedPreferences mySharedPreferences, Handler handler,
			String userName, String passWord) {
		// 这两种对属性赋值都是可以的
		this.setUserName(userName);
		this.setPassWord(passWord);
		this.setHandler(handler);
		this.mySharedPreferences = mySharedPreferences;

	}

	public LoginThread() {

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		editor = mySharedPreferences.edit();
		try {
			Log.i("Flag", "进入登录线程");
			Message msg = handler.obtainMessage();
			URL url = new URL(SystemContent.LOGIN + "?name=" + userName
					+ "&password=" + passWord);
			HttpRequest request = HttpRequest.get(url).connectTimeout(3000);
			if (request.ok()) {
				String responseString = request.body();
				Log.i("Flag", responseString);
				JSONObject responseJSON = new JSONObject(responseString);
				if (responseJSON.getInt("status") == 1) {
					Log.i("Flag", "登录成功");
					editor.putString("userId", responseJSON.getString("id"));
					editor.putString("sex", responseJSON.getString("sex"));
					editor.commit();
					msg.what = 1;// 登录成功

					handler.sendMessage(msg);
				} else {
					Log.i("Flag", "登录失败 用户名或密码错误");
					msg.what = 2;// 登录失败 用户名或密码错误
					handler.sendMessage(msg);
				}
			} else {
				Log.i("Flag", "登录失败 服务器出错");
				msg.what = 3;// 登录失败 服务器出错
				handler.sendMessage(msg);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public SharedPreferences getMySharedPreferences() {
		return mySharedPreferences;
	}

	public void setMySharedPreferences(SharedPreferences mySharedPreferences) {
		this.mySharedPreferences = mySharedPreferences;
	}

}
