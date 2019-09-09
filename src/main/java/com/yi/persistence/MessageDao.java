package com.yi.persistence;

import com.yi.domain.Message;

public interface MessageDao {
	public void create(Message vo) throws Exception;
	public Message readMessage(int mid) throws Exception;
	public void updateState(int mid) throws Exception;
}
