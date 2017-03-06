package com.crane.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * ClassName: JacksonMapper
 * date: 2015年8月9日 下午12:06:43 
 * @author 多多洛
 * @version 
 * @since JDK 1.7
 */
public class JacksonMapper {

	private static final Logger logger = LoggerFactory.getLogger(JacksonMapper.class);

	private static ObjectMapper objectMapper = new ObjectMapper();
	private static JsonFactory jsonFactory = new JsonFactory();

	/**
	 * @param String
	 *            jsonAsString
	 *            JSON字符串
	 * @param Class
	 *            <T> pojoClass
	 *            目标映射对象
	 * **/
	public static <T> Object fromJson(String jsonAsString, Class<T> pojoClass) {
		try {
			// 如要这个string是一个array类型的json，则可以直接转换成数组类型的业务对象
			objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			objectMapper.disable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
			if (jsonAsString.startsWith(JsonToken.START_ARRAY.asString())
					&& jsonAsString.endsWith(JsonToken.END_ARRAY.asString())) {
				return objectMapper.readValue(jsonAsString,
						objectMapper.getTypeFactory().constructCollectionType(List.class, pojoClass));
			} else {
				return objectMapper.readValue(jsonAsString, pojoClass);
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			if (logger.isErrorEnabled()) {
				logger.error("JsonParseException", e);
			}
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			if (logger.isErrorEnabled()) {
				logger.error("JsonMappingException", e);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			if (logger.isErrorEnabled()) {
				logger.error("IOException", e);
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("JSON Deserialize throw a exception，返回目标POJO");
		}
		return pojoClass;
	}

	/**
	 * @param Object
	 *            pojo
	 *            业务对象
	 * @param boolean prettyPrint
	 *        设置格式输出可读性
	 * **/
	public static String toJson(Object pojo, boolean prettyPrint) {
		StringWriter sw = new StringWriter();
		JsonGenerator jg = null;
		try {
			jg = jsonFactory.createGenerator(sw);
			if (prettyPrint) {
				jg.useDefaultPrettyPrinter();
			}
			objectMapper.writeValue(jg, pojo);
			sw.flush();
			return sw.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			if (logger.isErrorEnabled()) {
				logger.error("IOException", e);
			}
			e.printStackTrace();
		}

		if (logger.isInfoEnabled()) {
			logger.info("JSON serialize throw a exception，返回空串");
		}
		return StringUtils.EMPTY;
	}

	/**
	 * @param Object
	 *            pojo
	 *            业务对象
	 *            默认格式输出为可读性较好
	 * **/
	public static String toJson(Object pojo) {
		return toJson(pojo, true);
	}

	// map to json

	/**
	 * json串转换成MAP
	 * 
	 * @param String
	 *            jsonString
	 * ***/
	public static Map<?, ?> fromJson2Map(String jsonStr) {
		return (Map<?, ?>) fromJson(jsonStr, Map.class);
	}

	/**
	 * 
	 * MAP对象转成业务对象
	 * 
	 * @return
	 * ***/
	public static <T> Object map2Json(Map<?, ?> pojoMap, Class<T> pojoClass) {
		String pojoAsMapString = JacksonMapper.toJson(pojoMap);
		return JacksonMapper.fromJson(pojoAsMapString, pojoClass);
	}
}