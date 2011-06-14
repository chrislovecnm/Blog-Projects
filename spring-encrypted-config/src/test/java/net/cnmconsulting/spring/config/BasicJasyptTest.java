package net.cnmconsulting.spring.config;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.beans.ITestBean;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/applicationContext-jasypt.xml"})
public class BasicJasyptTest {
	
	@Autowired
	private ITestBean testBean;
	
	@BeforeClass
	public static void setupEnvVar() {
		System.setProperty("APP_PASS", "151987thisismysalt!!");
	}
	
	@Test
	public void testBean() {
		assertThat(testBean.getFoo(), equalTo("MyPassword"));
	}

	

}
