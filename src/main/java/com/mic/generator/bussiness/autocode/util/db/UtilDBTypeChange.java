package com.mic.generator.bussiness.autocode.util.db;

import com.mic.generator.bussiness.autocode.constfield.JavaType;
import com.mic.generator.bussiness.autocode.constfield.MySQLType;

/**
 * Created by cao_x on 2020/4/21.
 */
public class UtilDBTypeChange {


    public static String formatSqltype2JavaType(MySQLType mySQLType){
        switch (mySQLType){
            //大文本
            case BLOB:
            case LONGBLOB:
            case TINYBLOB:
            case MEDIUMBLOB:
                return JavaType.BYTE.getKey();
            case BINARY:
            case VARBINARY:
                return JavaType.STRING.getKey();
            //单字节
            case BIT:
                return JavaType.BOOLEAN.getKey();
            case CHAR:
            case TEXT:
            case VARCHAR:
            case LONGTEXT:
            case TINYTEXT:
            case MEDIUMTEXT:
                return JavaType.STRING.getKey();
            //数字
            case INT:
            case TINYINT:
            case SMALLINT:
            case MEDIUMINT:
                return JavaType.INTEGER.getKey();
            case BIGINT:
                return JavaType.BIGINTEGER.getKey();
            case FLOAT:
                return JavaType.FLOAT.getKey();
            case DOUBLE:
                return JavaType.DOUBLE.getKey();
            case DECIMAL:
                return JavaType.BIGDECIMAL.getKey();
            //日期
            case DATE:
            case YEAR:
                return JavaType.DATE.getKey();
            case TIME:
                return JavaType.TIME.getKey();
            case DATETIME:
            case TIMESTAMP:
                return JavaType.TIMESTAMP.getKey();



            default:
                return null;
        }
    }

}
