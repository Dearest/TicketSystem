package com.compitation.ticketsystem.thread;

import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.compitation.ticketsystem.utils.HttpRequest;
import com.compitation.ticketsystem.utils.SystemContent;

/**
 * 加载主界面信息的线程
 * 
 * @author HANCHEN
 * 
 */
public class MainPageThread implements Runnable {

	private String userId;
	private Handler handler;
	private SharedPreferences mySharedPreferences;
	private SharedPreferences.Editor editor;

	@Override
	public void run() {
		Log.i("Falg", "进入获取主界面信息的线程");
		editor = mySharedPreferences.edit();
		Message msg = handler.obtainMessage();
		try {
			URL userInfoUrl = new URL(SystemContent.SHOW_USER_INFO + "?userId="
					+ userId);
			URL nowTicketNum = new URL(SystemContent.GET_BY_DATE_NUM + "?userId="
					+ userId);
			URL nowUploadTicketNum = new URL(SystemContent.GET_UP_FINES
					+ "?userid=" + userId);
			HttpRequest userInfoRequest = HttpRequest.get(userInfoUrl).connectTimeout(
					3000);
			if (userInfoRequest.ok()) {
				Log.i("Falg", "请求用户信息成功");
				JSONObject userJSON = new JSONObject(userInfoRequest.body());
				Log.i("Falg", userJSON.toString());
				JSONArray userArray = userJSON.getJSONArray("userInfo");
				JSONObject userInfo = userArray.getJSONObject(0);
				Log.i("Falg", userInfo.toString());
				editor.putString("userName", userInfo.getString("name"));
				editor.putString("passWord", userInfo.getString("password"));
				editor.putString("phone", userInfo.getString("phone"));
				editor.putString("caeNum", userInfo.getString("carnum"));
				editor.putString("points", userInfo.getString("points"));
				editor.commit();
			} else {
				msg.what = 4;//获取用户信息失败
			}
			
			HttpRequest finesNumRequest = HttpRequest.get(nowTicketNum).connectTimeout(3000);
			if (finesNumRequest.ok()) {
				JSONObject finesJson = new JSONObject(finesNumRequest.body());
				editor.putInt("finesNum", finesJson.getInt("num"));
				editor.commit();
			}else {
				msg.what = 4;//获取用户信息失败
			}
			
			HttpRequest upNum = HttpRequest.get(nowUploadTicketNum).connectTimeout(3000);
			if (upNum.ok()) {
				JSONObject upJSON = new JSONObject(upNum.body());
				editor.putInt("upNum", upJSON.getInt("num"));
				editor.commit();
			}
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public MainPageThread() {
	}

	public MainPageThread(SharedPreferences mySharedPreferences,
			Handler handler, String userId) {
		this.handler = handler;
		this.userId = userId;
		this.mySharedPreferences = mySharedPreferences;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
}
