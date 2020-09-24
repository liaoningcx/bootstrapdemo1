package com.auto.common.util;

import org.apache.commons.lang.StringUtils;

/**
 * Created by caoxue on 2016/7/12.
 */
public class StringUtil {


    public static boolean isEmpty(String studentInfoId) {
        return !StringUtils.isNotEmpty(studentInfoId);
    }
}
