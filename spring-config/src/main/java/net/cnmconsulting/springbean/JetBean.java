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
 * Interface for a normal POJO used as a bean
 * 
 * @author Chris Love
 * @since 1.0
 * 
 */
public interface JetBean {

    public abstract String getName();

    public abstract void setName(String name);

    public abstract Long getPrice();

    public abstract void setPrice(Long price);

    public abstract URL getUrl();

    public abstract void setUrl(URL url);

}