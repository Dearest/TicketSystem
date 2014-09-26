package com.compitation.ticketsystem.dispatchImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.SharedPreferences;
import android.os.Handler;

import com.compitation.ticketsystem.Idispatch.ILoginAndRegisterDispatch;
import com.compitation.ticketsystem.thread.LoginThread;
import com.comtipation.ticketsystem.model.User;

public class LoginAndRegisterDispatchImpl implements ILoginAndRegisterDispatch {

	@Override
	public void login(SharedPreferences sharedPreferences, Handler handler,
			String userName, String passWord) {
		LoginThread loginThread = new LoginThread(sharedPreferences, handler,
				userName, passWord);
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		executorService.submit(loginThread);

	}

	@Override
	public boolean register(SharedPreferences sharedPreferences,
			Handler handler, User user) {
		// TODO Auto-generated method stub
		return false;
	}
}
