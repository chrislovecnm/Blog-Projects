package net.cnmconsulting.spring.config.util;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.beans.ITestBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationContext-prop-placeholder-test.xml" )
public class PropPlaceHolderTest {

	@Autowired
	@Resource(name="foo")
	ITestBean bean;
	
	@Test
	public void testConfig() {
		System.out.println(bean.getName());
		System.out.println(bean.getFoo());
	}

}
