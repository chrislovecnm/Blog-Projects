/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.cnmconsulting.springbean;

import java.net.URL;


/**
 * 
 * @author Chris Love
 * @since 1.0
 */
public class JetBeanImpl implements JetBean {
	
	private String name;
	private Long price;
	private URL url;
	
	public JetBeanImpl(String name) {
		this.name = name;
	}
	
	public JetBeanImpl() {
	}
	
	@Override
	public Long getPrice() {
		return price;
	}
	
	@Override
	public void setPrice(Long price) {
		this.price = price;
	}
	
	@Override
	public URL getUrl() {
		return url;
	}
	
	@Override
	public void setUrl(URL url) {
		this.url = url;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
}
