package com.compitation.ticketsystem.dispatchImpl;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.compitation.ticketsystem.Idispatch.IUploadDispatch;
import com.comtipation.ticketsystem.model.Ticket;

public class UploadDispatchImpl implements IUploadDispatch {
	private LocationClient locationClient = null;
	private String address;
	private static final int UPDATE_TIME = 5000;

	@Override
	public void upload(Handler handler, Ticket ticket) {
		// TODO Auto-generated method stub

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
		return address;

	}

	@Override
	public LocationClient initClient(Context context) {
		// TODO Auto-generated method stub
		
		locationClient = new LocationClient(context);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); // 是否打开GPS
		option.setCoorType("bd09ll"); // 设置返回值的坐标类型。
		option.setPriority(LocationClientOption.NetWorkFirst); // 设置定位优先级
		option.setProdName("LocationDemo"); // 设置产品线名称。强烈建议您使用自定义的产品线名称，方便我们以后为您提供更高效准确的定位服务。
		option.setScanSpan(UPDATE_TIME);
		option.setAddrType("all"); // 设置定时定位的时间间隔。单位毫秒
		locationClient.setLocOption(option);
		return locationClient;
	}
	
	

}
