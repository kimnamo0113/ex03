package com.yi.service;

import org.springframework.stereotype.Repository;

import com.yi.domain.Message;


public interface MessageService {
	
	public void addMessage(Message vo) throws Exception;
	
	public Message readMessage(String uid,int mid) throws Exception;
}
