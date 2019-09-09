package com.yi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yi.domain.Message;
import com.yi.persistence.MessageDao;
import com.yi.persistence.PointDao;

@Repository
public class MessageServiceImpl implements MessageService{
	@Autowired
	MessageDao messageDao;
	
	@Autowired
	PointDao pointDao;

	@Override
	@Transactional
	public void addMessage(Message vo) throws Exception {
		messageDao.create(vo);
		pointDao.updatePoint(vo.getSender(), 10);
	}

	@Override
	@Transactional
	public Message readMessage(String uid, int mid) throws Exception {
		
		messageDao.updateState(mid);
		pointDao.updatePoint(uid, 5);
		
		Message vo = messageDao.readMessage(mid);
		
		return vo;
	}

	
	
}
