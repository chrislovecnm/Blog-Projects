/*
 * Copyright 2002-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package test.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Juergen Hoeller
 * @since 11.11.2003
 */
public class IndexedTestBean {

	private BeanTestImpl[] array;

	private Collection<?> collection;

	@SuppressWarnings("rawtypes")
	private List list;

	private Set<? super Object> set;

	private SortedSet<? super Object> sortedSet;

	@SuppressWarnings("rawtypes")
	private Map map;

	@SuppressWarnings("rawtypes")
	private SortedMap sortedMap;


	public IndexedTestBean() {
		this(true);
	}

	public IndexedTestBean(boolean populate) {
		if (populate) {
			populate();
		}
	}

	@SuppressWarnings("unchecked")
	public void populate() {
		BeanTestImpl tb0 = new BeanTestImpl("name0", 0);
		BeanTestImpl tb1 = new BeanTestImpl("name1", 0);
		BeanTestImpl tb2 = new BeanTestImpl("name2", 0);
		BeanTestImpl tb3 = new BeanTestImpl("name3", 0);
		BeanTestImpl tb4 = new BeanTestImpl("name4", 0);
		BeanTestImpl tb5 = new BeanTestImpl("name5", 0);
		BeanTestImpl tb6 = new BeanTestImpl("name6", 0);
		BeanTestImpl tb7 = new BeanTestImpl("name7", 0);
		BeanTestImpl tbX = new BeanTestImpl("nameX", 0);
		BeanTestImpl tbY = new BeanTestImpl("nameY", 0);
		this.array = new BeanTestImpl[] {tb0, tb1};
		this.list = new ArrayList<Object>();
		this.list.add(tb2);
		this.list.add(tb3);
		this.set = new TreeSet<Object>();
		this.set.add(tb6);
		this.set.add(tb7);
		this.map = new HashMap<Object, Object>();
		this.map.put("key1", tb4);
		this.map.put("key2", tb5);
		this.map.put("key.3", tb5);
		@SuppressWarnings("rawtypes")
		List list = new ArrayList();
		list.add(tbX);
		list.add(tbY);
		this.map.put("key4", list);
	}


	public BeanTestImpl[] getArray() {
		return array;
	}

	public void setArray(BeanTestImpl[] array) {
		this.array = array;
	}

	public Collection<?> getCollection() {
		return collection;
	}

	public void setCollection(Collection<?> collection) {
		this.collection = collection;
	}

	@SuppressWarnings("rawtypes")
	public List getList() {
		return list;
	}

	@SuppressWarnings("rawtypes")
	public void setList(List list) {
		this.list = list;
	}

	public Set<?> getSet() {
		return set;
	}

	public void setSet(Set<? super Object> set) {
		this.set = set;
	}

	public SortedSet<? super Object> getSortedSet() {
		return sortedSet;
	}

	public void setSortedSet(SortedSet<? super Object> sortedSet) {
		this.sortedSet = sortedSet;
	}

	@SuppressWarnings("rawtypes")
	public Map getMap() {
		return map;
	}

	@SuppressWarnings("rawtypes")
	public void setMap(Map map) {
		this.map = map;
	}

	@SuppressWarnings("rawtypes")
	public SortedMap getSortedMap() {
		return sortedMap;
	}

	@SuppressWarnings("rawtypes")
	public void setSortedMap(SortedMap sortedMap) {
		this.sortedMap = sortedMap;
	}

}