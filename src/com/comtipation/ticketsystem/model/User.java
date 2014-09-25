package com.comtipation.ticketsystem.model;

public class User {
	private String userId;
	private String userName;
	
	private String passWord;
	/**
	 * 车牌号
	 */
	private String carNumber;
	/**
	 * 性别  0 男 1女
	 */
	private String sex;
	/**
	 * 手机号
	 */
	private int phoneNumber;
	
	/**
	 * 密保问题序号
	 */
	private int securityQuestionNum;
	/**
	 * 密保问题
	 */
	private String securityQuestionAnwser;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getSecurityQuestionNum() {
		return securityQuestionNum;
	}
	public void setSecurityQuestionNum(int securityQuestionNum) {
		this.securityQuestionNum = securityQuestionNum;
	}
	public String getSecurityQuestionAnwser() {
		return securityQuestionAnwser;
	}
	public void setSecurityQuestionAnwser(String securityQuestionAnwser) {
		this.securityQuestionAnwser = securityQuestionAnwser;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
