package com.mic.generator.common.util;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * vm工具
 * Created by caoxue on 2016/5/16.
 */
public class VmUtil {

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
            Velocity.init(p);
            VelocityContext context = new VelocityContext();
            Iterator it = map.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry entry = (Map.Entry)it.next();
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
