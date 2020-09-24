package com.mic.generator.beforeservice.basic.mapping.impl;

import com.mic.generator.beforecommon.util.dom4j.Dom4jUtil;
import com.mic.generator.beforedomain.basic.mapping.DataTypeMapping;
import com.mic.generator.beforeservice.basic.mapping.DataTypeMappingService;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Node;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据类型映射Service实现类
 * User: admin
 * Date: 13-6-27
 * Time: 上午9:01
 * To change this template use File | Settings | File Templates.
 */
@Service
public class DataTypeMappingServiceImpl implements DataTypeMappingService {

    private static Logger logger = Logger.getLogger(DataTypeMappingServiceImpl.class);

    /**
     * 数据类型映射列表查询
     * @param dataTypeMapping 数据类型映射实体
     * @return
     */
    public List<DataTypeMapping> query(DataTypeMapping dataTypeMapping){
        List<DataTypeMapping> dataTypeMappingList = new ArrayList<DataTypeMapping>();

//        String fileName = this.getClass().getResource("/").toString();
//        fileName = fileName.substring("file:/".length(), fileName.length())+ "//data//xml//data-type-mapping.xml";
//        Dom4jUtil dom4jUtil = new Dom4jUtil();
//        Document document =  dom4jUtil.read(fileName);
//        List<Node> list = dom4jUtil.selectNodes(document,"//dataType");
//        for(Node node : list){
//            DataTypeMapping dataType = new DataTypeMapping();
//            dataType.setDatabaseType(node.valueOf("@databaseType"));
//            dataType.setJdbcType(node.valueOf("@jdbcType"));
//            dataType.setJavaType(node.valueOf("@javaType"));
//            dataTypeMappingList.add(dataType);
//        }
        return dataTypeMappingList;
    }

    /**
     * 数据类型映射更新
     * @param dataTypeMappingList 数据类型映射实体列表
     * @return
     */
    public Boolean update(List<DataTypeMapping> dataTypeMappingList){

        boolean flag = true;
        try
        {
            String fileName = this.getClass().getResource("/").toString();
            fileName = fileName.substring("file:/".length(), fileName.length())+ "data/xml/data-type-mapping.xml";

            StringBuffer buffer = new StringBuffer();
            buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            buffer.append("<dataTypes>");
            for(DataTypeMapping dataTypeMapping : dataTypeMappingList){
                buffer.append("<dataType databaseType=\""+dataTypeMapping.getDatabaseType()+"\" jdbcType=\""+dataTypeMapping.getJdbcType()+"\" javaType=\""+dataTypeMapping.getJavaType()+"\"></dataType>");
            }
            buffer.append("</dataTypes>");

            Dom4jUtil dom4jUtil = new Dom4jUtil();
            dom4jUtil.createXMLFile(fileName, buffer.toString(), "prettyPrint");
        }catch(Exception e){
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }



}
