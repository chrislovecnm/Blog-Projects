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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JetBeanTest {

    @Test
    public void testJetBean() {
        // create the spring container using the AppConfig @Configuration class
        ApplicationContext ctx = new AnnotationConfigApplicationContext(
                AppConfig.class);
        JetBean jetBean = ctx.getBean(JetBean.class);
        assertThat(jetBean.getName(), equalTo("Gulf Stream G550"));
        assertThat(jetBean.getPrice(), equalTo(Long.valueOf(60000000)));
        URL gulfstream;
        try {
            gulfstream = new URL("http://www.gulfstream.com/products/g550/");
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            fail("error creating URL");
            throw new RuntimeException("error creating URL");
        }
        assertThat(jetBean.getUrl(), equalTo(gulfstream));
    }

}
