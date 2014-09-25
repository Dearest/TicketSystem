package com.compitation.ticketsystem.Idispatch;

import android.content.SharedPreferences;
import android.os.Handler;

import com.comtipation.ticketsystem.model.User;

/**
 * 登录注册的调度层接口
 * 
 * @author HANCHEN
 * 
 */
public interface ILoginAndRegisterDispatch {
	public void login( SharedPreferences sharedPreferences, Handler handler, String userName, String passWord);
	public boolean register(SharedPreferences sharedPreferences, Handler handler,User user);
}
