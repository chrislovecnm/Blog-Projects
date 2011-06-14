package net.cnmconsulting.spring.config.util;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.jasypt.encryption.pbe.PBEStringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.beans.ITestBean;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath*:/applicationContext-properties-test.xml"
		})
public class PropertyTest {
	
	@Autowired
	@Resource(name="configurationEncryptor")
	PBEStringEncryptor pbeStringEncryptor;
	
	@Autowired
	@Resource(name="foo")
	ITestBean bean;
	
	@Test
	public void testConfig() {
	 String foo =	pbeStringEncryptor.encrypt("MyPassword");
	 System.out.println(foo);
	 String bar =  pbeStringEncryptor.decrypt(foo);
	 System.out.println(bar);
	 assertNotNull(bean.getFoo());
	}
}
