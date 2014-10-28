package com.pravatpanda.apps.ats.dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pravatpanda.apps.ats.dao.AtsDao;


public class AtsDaoTest {
	
	@Test
	public void  testGetFloorLayout() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:associate-dao.xml");
		AtsDao dao = (AtsDao) applicationContext.getBean("atsDao");
		
	}
}
