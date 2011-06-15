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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Example class for utilizing Spring Framework 3.0 configuration via
 * annotations.
 * 
 * <p>
 * This example configures a JetBean bean for use in the applicationContext
 * </p>
 * 
 * @author Chris Love
 * @since 1.0
 * @see org.springframework.beans.factory.annotation.Value
 * @see org.springframework.context.annotation.Bean
 * @see org.springframework.context.annotation.Configuration
 * @see org.springframework.context.annotation.ImportResource
 */
@Configuration
// spring config that loads the properties file
@ImportResource("classpath:/properties-config.xml")
public class AppConfig {

    /**
     * Using property 'EL' syntax to load values from the jetProperties value
     */
    private @Value("#{jetProperties['jetBean.name']}") String name;
    private @Value("#{jetProperties['jetBean.price']}") Long price;
    private @Value("#{jetProperties['jetBean.url']}") URL url;

    /**
     * Create a jetBean within the Spring Application Context
     * @return a bean
     */
    public @Bean(name = "jetBean")
    JetBean jetBean() {
        JetBean bean = new JetBeanImpl();
        bean.setName(name);
        bean.setPrice(price);
        bean.setUrl(url);
        return bean;
    }

}