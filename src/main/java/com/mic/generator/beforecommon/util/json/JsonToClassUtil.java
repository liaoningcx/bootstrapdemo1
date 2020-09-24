package com.mic.generator.beforecommon.util.json;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;

/**
 * JSON转换
 * @author: other
 * @time: 13-9-27 下午5:46
 * @version: 1.0
 */
public class JsonToClassUtil {

	private static Logger log = Logger.getLogger(JsonToClassUtil.class);
	
	/**
	 * 对象转换为JSON
	 * @param obj
	 * @return
	 */
	public static String objectConvertJSON(Object obj) {
	    try {
	    	ObjectMapper objectMapper = new ObjectMapper();
	    	return objectMapper.writeValueAsString(obj);
	    } catch (IOException e) {
	    	log.error("对象转换为JSON出错", e);
	    }
	    
	    return null;
	}
    /**
     * 对象转换为JSON
     * @param obj
     * @return
     */
    public static String toJSON(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            log.error("对象转换为JSON出错", e);
        }

        return null;
    }
    /**
     * json转换为对象
     * @param json
     * @param
     * @return
     */
    public static Object jsonConvertObject(String json,TypeReference<?> type){
        Object obj = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, type);
        } catch (IOException e) {
            log.error("JSON转换为对象出错", e);
        }

        return obj;
    }
	

	
}
