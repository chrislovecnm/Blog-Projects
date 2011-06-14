package net.cnmconsulting.spring.config.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.beans.PropTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/applicationContext-properties-el.xml"})
public class CNMPropertiesFactoryBeanFilePasswordTest {

	// Inject the bean for testing
	@Autowired
	private PropTest propTest;

	/*
	 * Test will assure that the propTest bean will have the correct values injects,
	 * and the encypted values are decrypted upon injection
	 */
	@Test
	public void testProp() {
		assertNotNull(propTest.getUsername());
		assertThat(propTest.getUsername(), equalTo("MyPassword"));
		assertThat(propTest.getFoo(), equalTo("clove"));
	}

}
