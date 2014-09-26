package com.comtipation.ticketsystem.model;

/**
 * 罚单对象
 * 
 * @author HANCHEN
 * 
 */
public class Ticket {
	private String id;
	private String userId;
	/**
	 * 违规原因
	 */
	private String irregularity;
	private String address;
	private String time;
	private String uploadTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIrregularity() {
		return irregularity;
	}

	public void setIrregularity(String irregularity) {
		this.irregularity = irregularity;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
}
