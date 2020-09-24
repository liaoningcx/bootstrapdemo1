package com.mic.generator.beforebussiness.beforeautocode;

import com.mic.generator.beforebussiness.beforeautocode.templatefile.DaoFileTemplate;
import com.mic.generator.beforedomain.table.Column;
import com.mic.generator.beforedomain.table.TableInfo;
import org.springframework.http.HttpHeaders;

import java.util.*;

/**
 * Created by caoxue on 2016/2/29.
 */
public class AutoMakeCodeBussiness {

    /**
     * 按照table制作程序
     * @param tableInfo
     */
    public static void makeCodeByTable(TableInfo tableInfo){
        String tablename = tableInfo.getTable_Name();
        List<Column> columnList = tableInfo.getColumnsList();

        HttpHeaders header = new HttpHeaders();
        makeCodeByTable(tableInfo,header);
    }
/**
     * 按照table制作程序
     * @param tableInfo
     */
    public static void makeCodeByTable(TableInfo tableInfo,HttpHeaders headers){
        String tablename = tableInfo.getTable_Name();
        List<Column> columnList = tableInfo.getColumnsList();

        //将action，beforeservice，manager，beforedao，dbsql都压缩，将压缩写成文件二进制传给客户端，由客户端的文件存储地址写项目
        DaoFileTemplate.writeToFile(columnList);

    }


}
