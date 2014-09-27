package com.compitation.ticketsystem.thread;

import java.net.URL;

import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;

import com.compitation.ticketsystem.utils.HttpRequest;
import com.compitation.ticketsystem.utils.SystemContent;
import com.comtipation.ticketsystem.model.Ticket;

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
		Message msg = handler.obtainMessage();
		try {
			URL uploadUrl = new URL(SystemContent.UP_MESSAGE + "?irregularity="
					+ ticket.getIrregularity() + "&fines=10&address="
					+ ticket.getAddress() + "&tickettime=" + ticket.getTime()
					+ "&carnum=" + ticket.getCarNum() + "&userId="
					+ ticket.getUserId());
			HttpRequest uploadRequest = HttpRequest.get(uploadUrl).connectTimeout(3000);
			if (uploadRequest.ok()) {
				JSONObject upJson = new JSONObject(uploadRequest.body());
				if (upJson.getString("status").equals("1")) {
					msg.what = 1;//上传成功
				}else {
					msg.what = 2;//上传失败
				}
				
			}else {
				msg.what = -1;//服务器出错
			}
			handler.sendMessage(msg);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
