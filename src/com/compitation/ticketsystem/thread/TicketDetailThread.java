package com.compitation.ticketsystem.thread;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.compitation.ticketsystem.utils.HttpRequest;
import com.compitation.ticketsystem.utils.SystemContent;
import com.comtipation.ticketsystem.model.Ticket;
import android.os.Handler;
import android.os.Message;
/**
 * 获取当日罚单详情
 * @author HANCHEN
 *
 */
public class TicketDetailThread implements Runnable{
	private Handler handler;
	private String userId;
	private List<Ticket> tickets;
	public TicketDetailThread(Handler handler,String userId){
		this.handler = handler;
		this.userId = userId;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		tickets = new ArrayList<Ticket>();
		Message msg = handler.obtainMessage();
		try {
			URL detailUrl = new URL(SystemContent.FIND_ALL_FINE+"?userId="+userId);
			HttpRequest detailreRequest = HttpRequest.get(detailUrl).connectTimeout(3000);
			if (detailreRequest.ok()) {
				JSONObject detailJson = new JSONObject(detailreRequest.body());
				JSONArray detialArray = detailJson.getJSONArray("Allfinelist");
				for (int i = 0;i<detialArray.length();i++) {
					JSONObject detail = detialArray.getJSONObject(i);
					Ticket ticket = new Ticket();
					ticket.setId(detail.getString("id"));
					ticket.setIrregularity(detail.getString("irregularity"));
					ticket.setAddress(detail.getString("address"));
					ticket.setTime(detail.getString("tickettime"));
					ticket.setUploadTime(detail.getString("uploadtime"));
					tickets.add(ticket);
				}
				
				if (tickets.isEmpty()) {
					msg.what = 2;//没有记录
				}else {
					//传递一个list到主线程
					msg.what = 1;
					msg.obj = tickets;
					handler.sendMessage(msg);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
