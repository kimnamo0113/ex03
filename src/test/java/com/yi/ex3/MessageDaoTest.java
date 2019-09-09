package com.yi.ex3;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yi.domain.Message;
import com.yi.persistence.MessageDao;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MessageDaoTest {
	
	@Autowired
	MessageDao dao;
	
//	@Test
	public void test01Create() throws Exception {
		Message vo = new Message();
		vo.setSender("user01");
		vo.setTargetid("user02");
		vo.setMessage("안녕1");
		dao.create(vo);
	}
	@Test
	public void test02SelectMessage() throws Exception{
		Message vo = dao.readMessage(1);
		System.out.println(vo);
		
	}
	@Test
	public void test03Update() throws Exception{
		dao.updateState(1);
	}
	
}
