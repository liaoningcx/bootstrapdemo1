package com.mic.generator.beforebussiness.beforeautocode.templatefile;

import com.mic.generator.beforecommon.util.velocity.VelocityUtil;
import com.mic.generator.beforedomain.basic.config.CommonMsg;
import com.mic.generator.beforedomain.table.Column;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Created by caoxue on 2016/3/3.
 */
public class DaoFileTemplate {

    public static void writeToFile(List<Column> column){
//        StringBuilder fileMsg = new StringBuilder();
//        fileMsg.append(AutoCodeUtil.getPackageNameStr(CommonMsg.DAO_PACKAGE));
//        String packageNameBase = AutoCodeUtil.PACKAGE_NAME_BASE;
//        List<Template> templateList = AutoCodeUtil.getTemplateList();
//        String currentProjectPath =  new DaoFileTemplate().getClass().getResource("/").toString().substring("file:/".length());
//        String templatePath = currentProjectPath + "templet"+File.separator;
//        for(Template template : templateList){
//            String importFilePath = currentProjectPath + "temp"+File.separator + "testPath";
//            File file = new File(importFilePath);
//            if (!file.exists()) {
//                File filePath = new File(file.getParent());
//                filePath.mkdirs();
//            }
//            HashMap hashMap = new HashMap();
//            hashMap.put("author","wiwiwiwi");
//            hashMap.put("author","wiwiwiwi");
//            //解析并创建文件
//            VelocityUtil.generatorCode(templatePath, template.getTemplateName(), hashMap, importFilePath);
//            break;
//        }
//        System.out.print("It's OK!!!");
    }

    private static String getSelTemplate(){
        return null;
    }

}
