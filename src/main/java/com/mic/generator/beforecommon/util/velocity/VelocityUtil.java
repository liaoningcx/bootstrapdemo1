package com.mic.generator.beforecommon.util.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.util.StringUtils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * 模板工具类
 * User: other
 * Date: 13-10-6
 * Time: 上午10:43
 * To change this template use File | Settings | File Templates.
 */
public class VelocityUtil {

    public static StringWriter getGeneratorCode(String templatePath, String templateName, HashMap<String, Object> map){
        Properties p = new Properties();
        p.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
        p.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, templatePath);
        Velocity.init(p);
        VelocityContext context = new VelocityContext();
        Iterator it = map.entrySet().iterator();
        while(it.hasNext()){
            Entry entry = (Entry)it.next();
            context.put(entry.getKey().toString(), entry.getValue());
        }
        StringWriter writer = new StringWriter();
        Velocity.mergeTemplate(templateName, "UTF-8", context, writer );
        return writer;
    }

    /**
     * 将某个vm模板（templatePath + templateName）根据对应的map值，制作文件到importFilePath里
     * @param templatePath   vm模板所在目录
     * @param templateName   vm模板名称
     * @param map               vm模板中对应的键值对
     * @param importFilePath    vm赋值后所导出的模板文件目录
     */
    public static void generatorCode(String templatePath, String templateName, HashMap<String, Object> map, String importFilePath){
        try {
            Properties p = new Properties();
            p.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
            p.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
            p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, templatePath);
            System.out.println("111" + p.getProperty(Velocity.FILE_RESOURCE_LOADER_PATH));
            Velocity.init(p);
            VelocityContext context = new VelocityContext();
            Iterator it = map.entrySet().iterator();
            while(it.hasNext()){
                Entry entry = (Entry)it.next();
                context.put(entry.getKey().toString(), entry.getValue());
            }
            Template template = Velocity.getTemplate(templateName);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(importFilePath), "UTF-8"));
            template.merge(context, writer);
            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
