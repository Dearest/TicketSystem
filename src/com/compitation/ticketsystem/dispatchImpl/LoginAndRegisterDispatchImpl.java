package com.compitation.ticketsystem.dispatchImpl;

import com.comtipation.ticketsystem.model.User;

/**
 * 登录注册的调度层接口类
 * @author Administrator
 *
 */
public interface LoginAndRegisterDispatchImpl {
	/**
	 * 登录
	 * @param userName
	 * @param passWord
	 */
	public void login(String userName,String passWord);
	/**注册
	 * @param user
	 */
	public void register(User user);
}
