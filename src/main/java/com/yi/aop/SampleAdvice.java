package com.yi.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.yi.controller.HomeController;

@Aspect
@Component
public class SampleAdvice {
	private static final Logger logger = LoggerFactory.getLogger(SampleAdvice.class);
	
	
	@Before("execution(* com.yi.service.MessageServiceImpl.addMessage(..))")
	public void startLog(JoinPoint jp) {
		logger.info("========================================");
		logger.info("[startLog]");
		logger.info(Arrays.toString(jp.getArgs()) );
		logger.info("========================================");
	}
	
	@Around("execution(* com.yi.service.MessageServiceImpl.readMessage(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		long startTime = System.currentTimeMillis();
		logger.info("========================================");
		logger.info("[startLog]");
		logger.info(Arrays.toString(pjp.getArgs()) );
		logger.info("========================================");
		
		Object result = pjp.proceed(); //target 메소드를 실행함. readMessage함수가 실행됨
		
		long endTime = System.currentTimeMillis();
		logger.info("========================================");
		logger.info(pjp.getSignature().getName()+":"+(endTime - startTime));
		logger.info("[timeLog] END");
		logger.info("========================================");
		
		return result;
	}
	
	
}
