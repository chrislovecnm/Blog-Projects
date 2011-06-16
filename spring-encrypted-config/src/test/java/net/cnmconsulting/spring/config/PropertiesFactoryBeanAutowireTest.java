/*
 * =============================================================================
 * 
 *   Copyright (c) 2007-2011, The CNM Consulting (http://cnmconsulting.net) and
 *   chrislovecnm (http://chrislovecnm.com)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package net.cnmconsulting.spring.config;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Properties;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.beans.PropTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/applicationContext-properties-test.xml"})
public class PropertiesFactoryBeanAutowireTest {

	@Autowired
	private StringEncryptor stringEncryptor;
	
	@Autowired
	private PropTest propTest;

	@Test
	public void testCreateProperties() {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean(stringEncryptor);
		Resource location = new ClassPathResource("props-test.properties");
		propertiesFactoryBean.setLocation(location);
		try {
			Properties props = propertiesFactoryBean.createProperties();
			assertNotNull(props);
			String pass = (String)props.getProperty("pass");
			assertNotNull(pass);
			
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.toString());
		}
		
		
		
	}
	
	@Test
	public void testProp() {
		assertNotNull(propTest.getUsername());
	}

}
