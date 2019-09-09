package com.yi.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yi.domain.Message;

@Repository
public class MessageDaoImpl implements MessageDao{
	private static final String namespace = "com.yi.mapper.MessageMapper";

	@Autowired
	SqlSession sqlSession;
	
	
	@Override
	public void create(Message vo) throws Exception {
		sqlSession.insert(namespace+".create",vo);
	}

	@Override
	public Message readMessage(int mid) throws Exception {
		return sqlSession.selectOne(namespace+".readMessage",mid);
	}

	@Override
	public void updateState(int mid) throws Exception {
		sqlSession.update(namespace+".updateState",mid);
	}
}
