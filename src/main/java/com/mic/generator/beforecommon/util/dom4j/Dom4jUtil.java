package com.mic.generator.beforecommon.util.dom4j;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * DOM4J解析XML工具类
 * User: other
 * Date: 13-6-27
 * Time: 上午10:07
 */
public class Dom4jUtil {



    /**
     * 建立一个XML文档
     * @param filename 需建立的文件名
     * @param document 文档对象
     * @return 返回操作结果, 0表失败, 1表成功
     */
    public int createXMLFile(String filename, Document document){
        int returnValue = 1;
        try{
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter writer = new XMLWriter(new FileOutputStream(new File(filename)), format);
            writer.write(document);
            writer.close();
        }catch(Exception ex){
            ex.printStackTrace();
            returnValue = 0;
        }

        return returnValue;
    }

    /**
     * 建立一个XML文档
     * @param filename 需建立的文件名
     * @param document 文档对象
     * @param formatType 格式化类型
     * @return  返回操作结果, 0表失败, 1表成功
     */
    public int createXMLFile(String filename, Document document, String formatType){
        int returnValue = 1;
        try{
            XMLWriter writer = new XMLWriter(new FileOutputStream(new File(filename)), this.formatXML(formatType));
//            XMLWriter writer = new XMLWriter(new FileWriter(new File(filename)), this.formatXML(formatType));
            writer.write(document);
            writer.close();
        }catch(Exception ex){
            ex.printStackTrace();
            returnValue = 0;
        }

        return returnValue;
    }

    /**
     * 建立一个XML文档
     * @param filename 需建立的文件名
     * @param text 文档字符串
     * @return 返回操作结果, 0表失败, 1表成功
     */
    public int createXMLFile(String filename, String text){
        int returnValue = 0;
        try {
            returnValue = this.createXMLFile(filename, DocumentHelper.parseText(text));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    /**
     *  建立一个XML文档
     * @param filename 需建立的文件名
     * @param text 文档字符串
     * @param formatType  格式化类型
     * @return 返回操作结果, 0表失败, 1表成功
     */
    public int createXMLFile(String filename, String text, String formatType){
        int returnValue = 0;
        try {
            returnValue = this.createXMLFile(filename, DocumentHelper.parseText(text), formatType);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return returnValue;
    }


    /**
     * 获取根目录下所有元素
     * @param document 文档对象
     * @return 元素列表
     */
    public List<Element> getRootElements(Document document){
        List<Element> list = new ArrayList<Element>();

        Element rootElement =  document.getRootElement();
        // 枚举所有子节点
        for ( Iterator i = rootElement.elementIterator(); i.hasNext(); ) {
            Element element = (Element) i.next();
            list.add(element);
        }
        return list;
    }





    /**
     * 格式化XML
     * @param formatType 格式类型
     * @return  format
     */
    private  OutputFormat formatXML(String formatType){
        OutputFormat format = OutputFormat.createPrettyPrint();
        if(formatType!=null && !formatType.equals(""))
        {
            if(formatType.equals("prettyPrint")){
//              美化格式
                format = OutputFormat.createPrettyPrint();
            }else if(formatType.equals("compactFormat")){
//              缩减格式
                format = OutputFormat.createCompactFormat();
            }
        }
        return format;
    }

}
