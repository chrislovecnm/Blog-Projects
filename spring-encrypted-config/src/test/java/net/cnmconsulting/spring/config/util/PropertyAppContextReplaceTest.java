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
