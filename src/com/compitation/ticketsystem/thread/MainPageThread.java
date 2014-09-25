package com.compitation.ticketsystem.thread;

import java.net.URL;

import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;

import com.compitation.ticketsystem.utils.SystemContent;

/**
 * 加载主界面信息的线程
 * @author HANCHEN
 *
 */
public class MainPageThread implements Runnable{
	
	private String userId;
	private Handler handler;
	private SharedPreferences mySharedPreferences;
	private SharedPreferences.Editor editor;

	public MainPageThread(){
	}
	public MainPageThread(SharedPreferences mySharedPreferences,Handler handler,String userId){
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
	
	@Override
	public void run(){
		try {
			URL userInfoUrl = new URL(SystemContent.SHOW_USER_INFO+"?userId="+userId);
			URL nowTicketInfo = new URL(SystemContent.GET_BY_DATE+"?userId="+userId);
			URL nowUploadTicketNum = new URL(SystemContent.GET_UP_FINES+"?userid="+userId);
			Log.i("Flag", "这是获取主页面数据的线程");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
