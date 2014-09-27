package com.compitation.ticketsystem.dispatchImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.os.Handler;

import com.compitation.ticketsystem.Idispatch.IForgetPassWordDispatch;
import com.compitation.ticketsystem.thread.ForgetPassWordThread;
import com.comtipation.ticketsystem.model.User;

public class ForgetPassWordDispatchImpl implements IForgetPassWordDispatch {

	@Override
	public void forget(Handler handler, User user) {
		// TODO Auto-generated method stub
		ForgetPassWordThread forgetPassword = new ForgetPassWordThread(handler,
				user);
		ExecutorService forgetExecutor = Executors.newSingleThreadExecutor();
		forgetExecutor.execute(forgetPassword);
	}

}
