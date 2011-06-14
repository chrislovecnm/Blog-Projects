package net.cnmconsulting.spring.config.util;

import javax.annotation.Resource;

import org.jasypt.encryption.pbe.PBEStringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/applicationContext-properties.xml","classpath:/applicationContext-properties-el.xml"})
public class PropertyAppContextReplaceTest {
	
	@Autowired()
	@Resource(name="configurationEncryptor")
	PBEStringEncryptor pbeStringEncryptor;
	
	@Test
	public void testConfig() {
	 String foo =	pbeStringEncryptor.encrypt("MyPassword");
	 pbeStringEncryptor.decrypt(foo);
	}
}
