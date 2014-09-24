package com.compitation.ticketsystem.dispatchImpl;

import com.comtipation.ticketsystem.model.User;

public interface LoginAndRegisterDispatchImpl {
	
	public void login(String userName,String passWord);
	
	public void register(User user);
}
