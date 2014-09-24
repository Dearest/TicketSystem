package com.compitation.ticketsystem.Idispatch;

import com.comtipation.ticketsystem.model.User;

public interface ILoginAndRegisterDispatch {
	public void login(String userName,String passWord);
	public void register(User user);
}
