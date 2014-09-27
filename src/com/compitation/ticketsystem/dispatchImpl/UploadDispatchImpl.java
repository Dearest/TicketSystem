package com.compitation.ticketsystem.dispatchImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.compitation.ticketsystem.Idispatch.IUploadDispatch;
import com.compitation.ticketsystem.thread.UploadThread;
import com.comtipation.ticketsystem.model.Ticket;

public class UploadDispatchImpl implements IUploadDispatch {
	

	@Override
	public void upload(Handler handler, Ticket ticket) {
		// TODO Auto-generated method stub
		UploadThread uploadThread = new UploadThread(handler, ticket);
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(uploadThread);
	}

	@Override
	public String getCarNum(Handler handler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAddress(Handler handler, Context context) {
		// TODO Auto-generated method stub
		Log.i("Flag", "开始定位");
		return null;

	}


}
