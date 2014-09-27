package com.compitation.ticketsystem.dispatchImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.os.Handler;

import com.compitation.ticketsystem.Idispatch.IMainPageDispatch;
import com.compitation.ticketsystem.thread.TicketDetailThread;

public class MainPageDispatchImpl implements IMainPageDispatch{

	@Override
	public void getDetail(Handler handler, String userId) {
		// TODO Auto-generated method stub
		TicketDetailThread detailThread = new TicketDetailThread(handler, userId);
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(detailThread);
	}


}
