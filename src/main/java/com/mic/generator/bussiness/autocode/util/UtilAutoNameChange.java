package com.mic.generator.bussiness.autocode.util;

import com.mic.generator.bussiness.autocode.constfield.MySQLType;
import com.mic.generator.bussiness.autocode.util.string.StringUtil;

/**
 * 自动项目的名称转换工具
 * Created by cao_x on 2020/4/20.
 */
public class UtilAutoNameChange {

    /**
     * 名字转换mic_factory  -->  micFactory
     * @param sqlname
     * @return
     */
    public static String sql_name2propertyName(String sqlname){
        return sqlName2propertyName(sqlname,"_");
    }

    /**
     * 名字转换mic[splitChar]factory  -->  micFactory
     * @param sqlname
     * @param splitChar
     * @return
     */
    public static String sqlName2propertyName(String sqlname,String splitChar){
        String [] s = sqlname.split(splitChar);
        StringBuilder resultsb = new StringBuilder();
        for(int i=0;i<s.length;i++){
            if(i==0){
                resultsb.append(StringUtil.toLowerCaseFirstOne(s[i]));
            }else {
                resultsb.append(StringUtil.toUpperCaseFirstOne(s[i]));
            }
        }
        return resultsb.toString();
    }

    /**
     * 名字转换mic_factory -->  MicFactory
     * @param sqlname
     * @return
     */
    public static String sql_name2className(String sqlname){
        return sqlName2className(sqlname,"_");
    }
    /**
     * 名字转换mic[splitChar]factory -->  MicFactory
     * @param sqlname sql命名方式
     * @param splitChar 业务分隔符
     * @return
     */
    public static String sqlName2className(String sqlname,String splitChar){
        String [] s = sqlname.split(splitChar);
        StringBuilder resultsb = new StringBuilder();
        for(int i=0;i<s.length;i++){
            resultsb.append(StringUtil.toUpperCaseFirstOne(s[i]));
        }
        return resultsb.toString();
    }

    /**
     * 格式化sql的数据类型blob，varchar等变为jdbc的数据类型VARCHAR,INTEGER等
     * @param columnSQLType
     * @return
     */
    public static String sqltype2jdbctype(MySQLType columnSQLType) {
        return columnSQLType.name().toUpperCase();
    }
}
