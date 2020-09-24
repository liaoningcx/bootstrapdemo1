package com.mic.generator.common.util;

import com.mic.generator.beforecommon.util.dom4j.Dom4jUtil;
import com.mic.generator.bussiness.autocode.model.TemplateFile;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * dom4j的xml工具类。
 * Created by caoxue on 2016/6/7.
 */
public class Dom4jXMLUtil {

    /**
     * 加载XML文件到document
     * @param file XML文件
     * @return document对象
     */
    public static Document read(String file) {
        Document document = null;
        try {
            SAXReader saxReader = new SAXReader();
            document = saxReader.read(new File(file));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return document;

    }

    /**
     * 根据XPath表达式获取节点列表
     * @param document 文档对象
     * @param XPathExpression XPath表达式
     * @return 节点列表
     */
    public static List<Node> selectNodes(Document document, String XPathExpression) {
        List<Node> list = document.selectNodes(XPathExpression);
        return list;
    }

}
