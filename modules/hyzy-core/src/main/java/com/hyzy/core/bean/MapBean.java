package com.hyzy.core.bean;

import java.util.HashMap;

/**
 * MapBean类.
 * 
 * @author 
 */
public class MapBean extends HashMap<String, Object> {

	private static final long serialVersionUID = 7204015678968120243L;

	public MapBean() {
	}

	/**
	 * MapBean构造函数.
	 * 
	 * eg. MapBean mb = new MapBean("id", 1, "name", "test");
	 */
	public MapBean(Object... props) {
		put(props);
	}

	/**
	 * 获取Integer.
	 */
	public Integer getInt(Object key) {
		return (Integer) get(key);
	}

	/**
	 * 获取Integer, 自定义默认值.
	 */
	public Integer getInt(Object key, int defaultValue) {
		Integer i = (Integer) get(key);
		return (i == null ? defaultValue : i);
	}

	/**
	 * 获取Integer.
	 */
	public Long getLong(Object key) {
		return (Long) get(key);
	}

	/**
	 * 获取Integer, 自定义默认值.
	 */
	public Long getLong(Object key, long defaultValue) {
		Long i = (Long) get(key);
		return (i == null ? defaultValue : i);
	}
	
	/**
	 * 获取String.
	 */
	public String getString(Object key) {
		return (String) get(key);
	}

	/**
	 * 获取String, 自定义默认值.
	 */
	public String getString(Object key, String defaultValue) {
		String i = (String) get(key);
		return (i == null ? defaultValue : i);
	}

	/**
	 * 设置键值对.
	 * 
	 * eg. put("id", 1, "name", "test");
	 */
	public void put(Object... props) {
		for (int i = 1; i < props.length; i += 2) {
			put(String.valueOf(props[i - 1]), props[i]);
		}
	}
}
