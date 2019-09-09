package com.yi.domain;

import java.util.Date;

public class Message {
	private int mid;
	private String targetid;
	private String sender;
	private String message;
	private Date opendate;
	private Date senddate;
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Message(int mid, String targetid, String sender, String message, Date opendate, Date senddate) {
		super();
		this.mid = mid;
		this.targetid = targetid;
		this.sender = sender;
		this.message = message;
		this.opendate = opendate;
		this.senddate = senddate;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getTargetid() {
		return targetid;
	}
	public void setTargetid(String targetid) {
		this.targetid = targetid;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getOpendate() {
		return opendate;
	}
	public void setOpendate(Date opendate) {
		this.opendate = opendate;
	}
	public Date getSenddate() {
		return senddate;
	}
	public void setSenddate(Date senddate) {
		this.senddate = senddate;
	}
	@Override
	public String toString() {
		return "Message [mid=" + mid + ", targetid=" + targetid + ", sender=" + sender + ", message=" + message
				+ ", opendate=" + opendate + ", senddate=" + senddate + "]";
	}
	
	
	
}
