package com.mic.generator.bussiness.autocode;

import com.mic.generator.beforecommon.util.dom4j.Dom4jUtil;
import com.mic.generator.beforedomain.basic.config.CommonMsg;
import com.mic.generator.bussiness.autocode.model.TemplateFile;
import com.mic.generator.common.util.Dom4jXMLUtil;
import org.dom4j.Document;
import org.dom4j.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caoxue on 2016/3/3.
 */
public class AutoCodeBussiness {

    private String ss = this.getClass().getResource("/").toString();

    public static final String PACKAGE_NAME_BASE = "com.auto";

    /**
     * 根据文件类型返回不同的
     * @param filetype
     * @return
     */
    public static String getPackageNameStr(String filetype){
        if(CommonMsg.DAOFILE_TYPE.equals(filetype)){
            return PACKAGE_NAME_BASE+CommonMsg.DAO_PACKAGE;
        }
        if(CommonMsg.DAOIMPLFILE_TYPE.equals(filetype)){
            return PACKAGE_NAME_BASE+CommonMsg.DAOIMPL_PACKAGE;
        }
        if(CommonMsg.MANAGERFILE_TYPE.equals(filetype)){
            return PACKAGE_NAME_BASE+CommonMsg.MANAGER_PACKAGE;
        }
        if(CommonMsg.MANAGERIMPLFILE_TYPE.equals(filetype)){
            return PACKAGE_NAME_BASE+CommonMsg.MANAGERIMPL_PACKAGE;
        }
        if(CommonMsg.SERVICEFILE_TYPE.equals(filetype)){
            return PACKAGE_NAME_BASE+CommonMsg.SERVICE_PACKAGE;
        }
        if(CommonMsg.SERVICEIMPLFILE_TYPE.equals(filetype)){
            return PACKAGE_NAME_BASE+CommonMsg.SERVICEIMPL_PACKAGE;
        }
        return "";
    }

    //获取代码模板
    public static List<TemplateFile> getTemplateList() {
        List<TemplateFile> templateList = new ArrayList<TemplateFile>();
        String fileName = new AutoCodeBussiness().getClass().getResource("/").toString();
        fileName = fileName.substring("file:/".length(), fileName.length())+ "//data//xml//template.xml";
        Document document =  Dom4jXMLUtil.read(fileName);
        List<Node> list = Dom4jXMLUtil.selectNodes(document,"//template");
        for(Node node : list){
            TemplateFile t = new TemplateFile();
            t.setCode(node.valueOf("@code"));
            t.setName(node.valueOf("@name"));
            t.setTemplateName(node.valueOf("@templateName"));
            t.setFilePath(node.valueOf("@filePath"));
            t.setFileName(node.valueOf("@fileName"));
            templateList.add(t);
        }
        return templateList;
    }
}
