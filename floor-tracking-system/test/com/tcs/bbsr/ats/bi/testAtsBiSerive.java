package com.tcs.bbsr.ats.bi;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tcs.bbsr.ats.domain.FloorLayout;

public class testAtsBiSerive {
	@Test
	public void testBiService() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:associate-app.xml");
		AtsBiService service = (AtsBiService) applicationContext.getBean("atsService");
		FloorLayout floorPlan = service.getFloorPlan("KP_ODC6");
		
		System.out.println("hello");
	}
}
