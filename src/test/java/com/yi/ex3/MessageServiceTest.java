package com.yi.ex3;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yi.domain.Message;
import com.yi.service.MessageService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MessageServiceTest {
	@Autowired
	MessageService service;
	
	@Test
	public void test01AddMessage() throws Exception {
		Message vo = new Message();
		vo.setSender("user01");
		vo.setTargetid("user02");
		vo.setMessage("user00이 user02에게 보냅니다.");
		service.addMessage(vo);
	}
	
	@Test
	public void test02ReadMessage() throws Exception {
		service.readMessage("user02", 2);
	}
}
