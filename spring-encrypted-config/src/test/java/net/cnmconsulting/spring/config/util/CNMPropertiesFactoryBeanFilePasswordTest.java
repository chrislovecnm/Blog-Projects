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
		assertThat(propTest.getFoo(), equalTo("MyPassword"));
		assertThat(propTest.getUsername(), equalTo("clove"));
	}

}
