package com.compitation.ticketsystem.Idispatch;

import android.os.Handler;

import com.comtipation.ticketsystem.model.User;

/**
 * 登录注册的调度层接口
 * @author HANCHEN
 *
 */
public interface ILoginAndRegisterDispatch {
	public void login(Handler handler,String userName,String passWord);
	public boolean register(User user);
}
