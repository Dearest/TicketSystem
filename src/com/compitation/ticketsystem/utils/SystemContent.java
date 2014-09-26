package com.compitation.ticketsystem.utils;

public class SystemContent {
	public final static String LOGIN = "http://222.18.158.197:8080/fineSys/user/login";
	public final static String REGISTER = "http://222.18.158.197:8080/fineSys/user/register";
	public final static String CHANGE_USER_INFO = "http://222.18.158.197:8080/fineSys/user/changeUserInfo";
	public final static String LOGIN_OUT = "http://222.18.158.197:8080/fineSys/user/loginOut";
	public final static String FIND_PASSWORD = "http://222.18.158.197:8080/fineSys/user/findPassword";
	public final static String RESET_PASSWORD = "http://222.18.158.197:8080/fineSys/user/reSetPassword";
	public final static String SHOW_USER_INFO = "http://222.18.158.197:8080/fineSys/user/showUserInfo";
	public final static String UP_MESSAGE = "http://222.18.158.197:8080/fineSys/fine/upMessage";
	public final static String FIND_ALL_FINE = "http://222.18.158.197:8080/fineSys/fine/findAllfine";
	/**
	 * 获取当天罚单信息
	 */
	public final static String GET_BY_DATE = "http://222.18.158.197:8080/fineSys/fine/getBydate";
	/**
	 * 当天上传的罚单
	 */
	public final static String GET_UP_FINES = "http://222.18.158.197:8080/fineSys/fine/getUpfines";
	/**
	 * 当天罚单数量
	 */
	public final static String GET_BY_DATE_NUM = "http://222.18.158.197:8080/fineSys/fine/getBydateNum";
	/**
	 * 当天上传的罚单数量
	 */
	public final static String GET_UP_FINES_NUM = "http://222.18.158.197:8080/fineSys/fine/getUpfinesNum";
	public final static String SEND_THANKS = "http://222.18.158.197:8080/fineSys/fine/sendThanks";
	public final static String GET_THANKS = "http://222.18.158.197:8080/fineSys/fine/getThanks";
	public final static String PREFERNCE_NAME = "ticket";

	// sharedpreference里保存的信息的键名
	// 用户id userId
	// 用户名 userName
	// 用户密码 passWord
	// 电话 phone
	// 车牌号 carNum
	// 分数 points
	//当天的罚单数量 finesNum   类型是 int   
	// 当天上传的罚单数量  upNum  int
	
}
