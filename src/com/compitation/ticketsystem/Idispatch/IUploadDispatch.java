package com.compitation.ticketsystem.Idispatch;

import android.content.Context;
import android.os.Handler;

import com.baidu.location.LocationClient;
import com.comtipation.ticketsystem.model.Ticket;

public interface IUploadDispatch {
	/**
	 * 上传罚单
	 * 
	 * @param handler
	 * @param ticket
	 */
	public void upload(Handler handler, Ticket ticket);

	/**
	 * 调用代码扫描车牌
	 */
	public String getCarNum(Handler handler);

	/**
	 * 定位
	 */
	public String getAddress(Handler handler, Context context);

	

}
