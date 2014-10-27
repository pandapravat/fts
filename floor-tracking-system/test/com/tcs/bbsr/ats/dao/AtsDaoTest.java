package com.tcs.bbsr.ats.dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class AtsDaoTest {
	
	@Test
	public void  testGetFloorLayout() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:associate-dao.xml");
		AtsDao dao = (AtsDao) applicationContext.getBean("atsDao");
		
	}
}
