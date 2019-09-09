package com.yi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yi.domain.Message;
import com.yi.service.MessageService;



@RestController
@RequestMapping("/message")
public class SampleController {
	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	@Autowired
	MessageService service;
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public ResponseEntity<String> addMessage(@RequestBody Message vo){
		logger.info("----------------- addMessage,vo="+vo);
		
		ResponseEntity<String> entity = null;
		try {
			service.addMessage(vo);
			entity = new ResponseEntity<String>("success",HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public ResponseEntity<Message> readMessage(String uid, int mid){
		logger.info("----------------------- readMessage");
		
		ResponseEntity<Message> entity = null;
		
		try {
			Message vo = service.readMessage(uid, mid);
			entity= new ResponseEntity<Message>(vo,HttpStatus.OK);
			
		}catch (Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<Message>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
