package com.compitation.ticketsystem.thread;

import com.comtipation.ticketsystem.model.Ticket;
import android.os.Handler;

public class UploadThread implements Runnable {
	private Handler handler;
	private Ticket ticket;

	public UploadThread(Handler handler, Ticket ticket) {
		this.handler = handler;
		this.ticket = ticket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}
