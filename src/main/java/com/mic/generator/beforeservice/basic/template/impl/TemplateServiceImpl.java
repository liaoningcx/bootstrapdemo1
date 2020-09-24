package com.mic.generator.beforeservice.basic.template.impl;

import com.mic.generator.beforecommon.util.dom4j.Dom4jUtil;
import com.mic.generator.beforeservice.basic.template.TemplateService;
import com.mic.generator.bussiness.autocode.model.TemplateFile;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Node;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 模板管理Service类
 * User: admin
 * Date: 13-6-27
 * Time: 上午9:01
 */
@Service
public class TemplateServiceImpl implements TemplateService {

    private static Logger logger = Logger.getLogger(TemplateServiceImpl.class);
    /**
     * 模板列表查询
     * @param template 模板实体
     * @return 模板列表
     */
    public List<TemplateFile> query(TemplateFile template){
//        String fileName = this.getClass().getResource("/").toString();
//        fileName = fileName.substring("file:/".length(), fileName.length())+ "//data//xml//template.xml";
//        Dom4jUtil dom4jUtil = new Dom4jUtil();
//        Document document =  dom4jUtil.read(fileName);
//        List<Node> list = dom4jUtil.selectNodes(document,"//template");

        List<TemplateFile> templateList = new ArrayList<TemplateFile>();
//        for(Node node : list){
//            TemplateFile t = new TemplateFile();
//            t.setCode(node.valueOf("@code"));
//            t.setName(node.valueOf("@name"));
//            t.setTemplateName(node.valueOf("@templateName"));
//            t.setFilePath(node.valueOf("@filePath"));
//            t.setFileName(node.valueOf("@fileName"));
//            templateList.add(t);
//        }
        return templateList;
    }
}
