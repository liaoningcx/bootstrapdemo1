package com.mic.generator.bussiness.autocode.util;

import com.mic.generator.beforecommon.util.dom4j.Dom4jUtil;
import com.mic.generator.bussiness.autocode.model.TemplateFile;
import com.mic.generator.common.util.Dom4jXMLUtil;
import org.dom4j.Document;
import org.dom4j.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * 模板管理工具类——负责管理自动的添加模板
 * Created by caoxue on 2016/6/8.
 */
public class TemplateFileUtil {

    /**
     * 根据绝对路径查看xml结构的模板列表
     * @param filePath 文件所在绝对路径
     * @return
     */
    public static List<TemplateFile> getAutoBussinessTemplateFileListFromPath(String filePath){
        String targetFile = filePath+ "//data//xml//template.xml";
        return getAutoTemplateFileListByTargetFile(targetFile);

    }

    /**
     * 根据绝对路径查看xml结构的模板列表,获取基础框架的模板
     * @param currentProjectPath 文件所在绝对路径
     * @return
     */
    public static List<TemplateFile> getAutoFrameTemplateFileListFromPath(String currentProjectPath) {
        String targetFile = currentProjectPath+ "//data//xml//templateframe.xml";
        return getAutoTemplateFileListByTargetFile(targetFile);
    }

    /**
     * 根据目标文件，获取
     * @param targetFile
     * @return
     */
    private static  List<TemplateFile> getAutoTemplateFileListByTargetFile(String targetFile){
        Document document =  Dom4jXMLUtil.read(targetFile);
        List<Node> list = Dom4jXMLUtil.selectNodes(document,"//template");

        List<TemplateFile> templateList = new ArrayList<TemplateFile>();
        for(Node node : list){
            TemplateFile t = new TemplateFile();
            t.setCode(node.valueOf("@code"));
            t.setName(node.valueOf("@name"));
            t.setTemplatePath(node.valueOf("@templatePath"));
            t.setTemplateName(node.valueOf("@templateName"));
            t.setFilePath(node.valueOf("@filePath"));
            t.setFileName(node.valueOf("@fileName"));
            t.setFlag(node.valueOf("@flag"));
            templateList.add(t);
        }

        return templateList;
    }
}
