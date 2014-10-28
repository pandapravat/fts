package com.pravatpanda.apps.ats.bi;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

public class TestAtsBiSerive {
	@Test
	public void TestBiService() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:associate-app.xml");
		AtsBiService service = (AtsBiService) applicationContext.getBean("atsService");
		//FloorLayout floorPlan = service.getFloorPlan("KP_ODC6");
		
		Assert.notNull(service);
		System.out.println("hello");
	}
}
