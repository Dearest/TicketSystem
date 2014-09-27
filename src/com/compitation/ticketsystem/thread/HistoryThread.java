package com.compitation.ticketsystem.thread;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;

import com.compitation.ticketsystem.utils.HttpRequest;
import com.compitation.ticketsystem.utils.SystemContent;
import com.comtipation.ticketsystem.model.Ticket;

/**
 * 获取历史记录的线程 
 * 如果存在历史记录 则通过handle返回  List<Ticket>
 * @author HANCHEN
 *
 */
public class HistoryThread implements Runnable{
	private Handler handler;
	private String userId;
	private List<Ticket> tickets;
	public HistoryThread(Handler handler,String userId){
		this.handler = handler;
		this.userId = userId;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		tickets = new ArrayList<Ticket>();
		Message msg = handler.obtainMessage();
		try {
			URL historyUrl = new URL(SystemContent.FIND_ALL_FINE+"?userId="+userId);
			HttpRequest historyRequest = HttpRequest.get(historyUrl).connectTimeout(3000);
			if (historyRequest.ok()) {
				JSONObject historyJson = new JSONObject(historyRequest.body());
				JSONArray historyArray = historyJson.getJSONArray("Allfinelist");
				for (int i = 0;i<historyArray.length();i++) {
					JSONObject history = historyArray.getJSONObject(i);
					Ticket ticket = new Ticket();
					ticket.setId(history.getString("id"));
					ticket.setIrregularity(history.getString("irregularity"));
					ticket.setAddress(history.getString("address"));
					ticket.setTime(history.getString("tickettime"));
					ticket.setUploadTime(history.getString("uploadtime"));
					tickets.add(ticket);
				}
				
				if (tickets.isEmpty()) {
					msg.what = 2;//沒有歷史記錄
				}else {
					//有历史激励 传递一个list到主线程
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
