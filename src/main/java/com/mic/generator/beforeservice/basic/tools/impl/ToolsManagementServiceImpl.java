package com.mic.generator.beforeservice.basic.tools.impl;

import com.mic.generator.beforecommon.util.dom4j.Dom4jUtil;
import com.mic.generator.beforedomain.basic.tools.Tools;
import com.mic.generator.beforeservice.basic.tools.ToolsManagementService;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Node;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 工具类Service实现类
 * User: admin
 * Date: 13-6-27
 * Time: 上午9:01
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ToolsManagementServiceImpl implements ToolsManagementService {

    private static Logger logger = Logger.getLogger(ToolsManagementServiceImpl.class);

    /**
     * 工具类列表查询
     * @param tools 工具类实体
     * @return
     */
    public List<Tools> query(Tools tools){
        List<Tools> toolsList = new ArrayList<Tools>();

//        String fileName = this.getClass().getResource("/").toString();
//        fileName = fileName.substring("file:/".length(), fileName.length())+ "//data//xml//tools-management.xml";
//        Dom4jUtil dom4jUtil = new Dom4jUtil();
//        Document document =  dom4jUtil.read(fileName);
//        List<Node> list = dom4jUtil.selectNodes(document,"//class");
//        for(Node node : list){
//            Tools t = new Tools();
//            t.setClassName(node.valueOf("@name"));
//            t.setPackagePath(node.valueOf("@package"));
//            t.setClassDesc(node.valueOf("@desc"));
//            toolsList.add(t);
//        }
        return toolsList;
    }

    /**
     * 工具类更新
     * @param toolsList 工具类实体列表
     * @return
     */
    public Boolean update(List<Tools> toolsList){

        boolean flag = true;
        try
        {
            String fileName = this.getClass().getResource("/").toString();
            fileName = fileName.substring("file:/".length(), fileName.length())+ "data/xml/tools-management.xml";

            StringBuffer buffer = new StringBuffer();
            buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            buffer.append("<tools>");
            for(Tools tools : toolsList){
                buffer.append("<class name=\""+tools.getClassName()+"\" package=\""+tools.getPackagePath()+"\" desc=\""+tools.getClassDesc()+"\"></class>");
            }
            buffer.append("</tools>");

            Dom4jUtil dom4jUtil = new Dom4jUtil();
            dom4jUtil.createXMLFile(fileName, buffer.toString(), "prettyPrint");
        }catch(Exception e){
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }



}
