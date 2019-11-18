package com.dream.city.base.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dream.city.base.exception.BLKException;
import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 用于实现PayOrder与JSON字符串之间的转换。
 * 
 * @author duanshao
 */
@SuppressWarnings("unchecked")
public class JSONHelper {

	private static ObjectMapper mapper;
	static {
		mapper = new ObjectMapper();
		// 忽略不存在的属性
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

	}

	/**
	 * 通过JSON字符串生成对象
	 * 
	 * @param json
	 *            JSON字符串
	 * @param type
	 *            返回值的类型
	 * @return 如果能够封装为指定对象，则返回该值，否则返回null
	 */
	public static <T> T fromJson(String json, Class<T> type) {
		T result = null;
		try {
			if (StringUtils.isNotBlank(json)) {
				result = mapper.readValue(json, type);
			}
			return result;
		} catch (Exception e) {
			throw new BLKException(500,
					"Error converting json to obj: " + json, e);
		}
	}

	/**
	 * 如需读取集合如List/Map,且不是List<String>这种简单类型时使用如下语句: List<MyBean> beanList =
	 * binder.getMapper().readValue(listString, new
	 * TypeReference<List<MyBean>>() {});
	 * 
	 * @param json
	 * @param tr
	 * @return
	 */
	public static <T> T fromJson(String json, TypeReference<T> tr) {
		T result = null;
		try {
			if (StringUtils.isNotBlank(json)) {
				result = mapper.readValue(json, tr);
			}
		} catch (Exception e) {
			throw new BLKException(500,
					"Error converting json to obj: " + json, e);
		}
		return result;
	}

	/**
	 * 生成JSON字符串.生成字符串会自动进行HTML转义及不可直接被解释.
	 * 
	 * @param obj
	 *            对象实例
	 * @return 返回生成的字符串
	 */
	public static String toJson(Object obj) {
		String result = null;
		try {
			if (obj != null) {
				result = mapper.writeValueAsString(obj);
			}
			return result;
		} catch (Exception e) {
			throw new BLKException(500, "Error converting obj to json: " + obj,
					e);
		}
	}

	/**
	 * 简单地组装一个json对象
	 * 
	 * @param keyVals
	 *            键值对，偶数序系列(0,2,4...)是键名，必须是字符串， 基数序系列(1,3,5...)是值，可为空
	 * @return
	 */
	public static String formJson(Object... keyVals) {
		if (keyVals == null || keyVals.length == 0)
			return null;

		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < keyVals.length; i += 2) {
			map.put((String) keyVals[i], i + 1 == keyVals.length ? null
					: keyVals[i + 1]);
		}
		return toJson(map);
	}

	/**
	 * 
	 * @param json
	 *            JSON字符串 以{key:value} 形式
	 * @param key
	 *            key
	 * @return value
	 */

	public static String fromJson(String json, String key) {
		Map<String, String> map = fromJson(json, Map.class);
		if (map != null) {
			return map.get(key);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static Map toMap(Object obj) {
		String json = toJson(obj);
		return fromJson(json, Map.class);
	}

	@SuppressWarnings("rawtypes")
	public static List<Map> toListMap(List list) {
		String json = toJson(list);
		return fromJson(json, new TypeReference<List<Map>>() {
		});
	}
}
