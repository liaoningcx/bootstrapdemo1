package com.mic.generator.webser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mic.generator.beforecommon.util.velocity.VelocityUtil;
import com.mic.generator.beforedomain.table.TableInfo;
import com.mic.generator.bussiness.autocode.constfield.JavaType;
import com.mic.generator.bussiness.autocode.constfield.MySQLType;
import com.mic.generator.bussiness.autocode.model.AutoColumn;
import com.mic.generator.bussiness.autocode.model.AutoTable;
import com.mic.generator.bussiness.autocode.model.TemplateFile;
import com.mic.generator.bussiness.autocode.util.FrameAutoUtil;
import com.mic.generator.bussiness.autocode.util.TemplateFileUtil;
import com.mic.generator.bussiness.autocode.util.UtilAutoColumnFactory;
import com.mic.generator.bussiness.autocode.util.UtilAutoNameChange;
import com.mic.generator.common.constant.CommonConstant;
import com.mic.generator.common.util.JsonUtil;
import com.mic.generator.common.util.UtilRandomFigure;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.File;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.*;
import java.util.List;

/**
 * Created by caoxue on 2015/12/15.
 */
@Controller
@RequestMapping("/micMsg")
public class MicMsg {

    private static Logger logger = Logger.getLogger(MicMsg.class);

    /**
     * 界面的test按钮——暂时
     * @param request
     * @param value
     * @return
     */
    @RequestMapping(value = "/testWebSer2",method = {RequestMethod.GET,RequestMethod.POST} )
    @ResponseBody
    public ResponseEntity testWebSer2(HttpServletRequest request, String value){
        try{
            System.out.println("::::The Value is "+value);

            //当前项目路径
            String currentProjectPath = this.getClass().getResource("/").toString().substring("file:/".length());  //file:/D:/dev_h/realDev/auto_code/target/classes/
            //框架文件目录：  currentProjectPath + 自动代码总目录 + 框架结构
            String frameBasePath = currentProjectPath + "framebean"+File.separator+"springmvcframe";
            //自动代码生成区的目录：   currentProjectPath + 自动代码生成区 + 自动项目的总文件夹
            String autoProjPath = currentProjectPath + "autoprojproduct" +File.separator +"autoProjWin";

            //框架固定文件--如js等
            FrameAutoUtil.AutoFrameMake(frameBasePath,autoProjPath);

            //获取值
            HashMap<String,Object> autoBeanVal = _getAutoCodeName(value);
            autoBeanVal.put("PROJECT_NAME","autoProjectVer1.0");//项目名称
            //获取模板Liat
            List<TemplateFile> templateFileList = TemplateFileUtil.getAutoBussinessTemplateFileListFromPath(currentProjectPath);
            //业务模板目录
            String templatePath = currentProjectPath + "templet";// 模板目录  file:/D:/dev_h/realDev/auto_code/target/classes/templet\
            //mapper文件集合
            List<String> mapperFileList = new ArrayList<String>();
            for (TemplateFile templateFile : templateFileList){
                if("mybatisconfigTemplate".equals(templateFile.getCode())){//需要收集信息，最后生成
                    continue;
                }
                //各个模板对应的文件
                String autoFileNamePath = "";
                if ("frame".equals(templateFile.getFlag())){
                    autoFileNamePath = autoProjPath+"/src/main/"+templateFile.getTemplatePath()+File.separator+templateFile.getFilePath()+File.separator+templateFile.getFileName();
                }
                if("bussiness".equals(templateFile.getFlag())){
                    autoFileNamePath = autoProjPath+"/src/main/"+templateFile.getTemplatePath()+File.separator+templateFile.getFilePath()+File.separator+autoBeanVal.get("domainClassNameEN")+templateFile.getFileName();//D:/dev_h/realDev/auto_code/target/classes/templet/aaa.vm
                }
                if("webvm".equals(templateFile.getFlag())){
                    autoFileNamePath = autoProjPath+"/src/main/"+templateFile.getTemplatePath()+File.separator+templateFile.getFilePath()+File.separator+autoBeanVal.get("domainClassNameEN")+templateFile.getFileName();//D:/dev_h/realDev/auto_code/target/classes/templet/aaa.vm
                }
                if("webjs".equals(templateFile.getFlag())){
                    autoFileNamePath = autoProjPath+"/src/main/"+templateFile.getTemplatePath()+File.separator+autoBeanVal.get("domainPropertyNameEN")+File.separator+autoBeanVal.get("domainPropertyNameEN")+templateFile.getFileName();//D:/dev_h/realDev/auto_code/target/classes/templet/aaa.vm
                }
                if("webinitvm".equals(templateFile.getFlag())){
                    autoFileNamePath = autoProjPath+"/src/main/"+templateFile.getTemplatePath()+File.separator+templateFile.getFilePath()+File.separator+templateFile.getFileName();//D:/dev_h/realDev/auto_code/target/classes/templet/aaa.vm
                }
                if ("".equals(autoFileNamePath)){
                    System.out.println("自动生成文件为空，其模板文件为："+templateFile.getCode());
                }
                File file = new File(autoFileNamePath);
                if (!file.exists()) {
                    File filePath = new File(file.getParent());
                    filePath.mkdirs();
                }
                //解析并创建文件    templatePath:d:/dev/src/aaa/     templateFile.getTemplateName():daoImplTemplate.vm
                VelocityUtil.generatorCode(templatePath, templateFile.getTemplateName(), autoBeanVal, autoFileNamePath);
                if("sqlMapperTemplet".equals(templateFile.getCode())){
                    mapperFileList.add("mybatis"+File.separator+templateFile.getFilePath()+File.separator+autoBeanVal.get("domainClassNameEN")+templateFile.getFileName());
                }
            }

            //根据业务产生的sql文件，对应给mybatis的自动生成
            autoBeanVal.put("sqlMapperFileList",mapperFileList);
            VelocityUtil.generatorCode(templatePath,"mybatisConfigTemplate.vm", autoBeanVal, autoProjPath+"/src/main/resources/mybatis"+File.separator+"mybatis-config.xml");

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    private HashMap<String, Object> _getAutoCodeName(String val) {
        HashMap<String, Object> mapval = new HashMap<String, Object>();

        AutoTable autoTable = JsonUtil.fromJsonByGoogle(val, new TypeToken<AutoTable>() {
        });

        //基本框架 -- dao，impl，service，impl
        mapval.put("packageBasePath", CommonConstant.PACKAGE_BASE_PATH);//包名基础路径，不带dao、service、manager这类的包名，在他们的上一级；
        mapval.put("domainClassNameEN", autoTable.getDomainClassNameEN());//domain名字 -- 与表名一致
        mapval.put("domainPropertyNameEN", autoTable.getDomainPropertyNameEN());//domain用来做属性 -- 首字母小写 用来做domain的属性命名  eg:DomainNameEN domainNameEN
        mapval.put("tableComment", autoTable.getTableComment());//domain名字汉字 --  表名的解释
        mapval.put("author", autoTable.getAuthorName());//作者
        mapval.put("makeDateTime",new Date().toString());//生成时间
        mapval.put("extendsClassList", autoTable.getExtendsClassList());//扩展功能类的信息,根据客户端选择，生成功能性的bean

        //基本框架 -- domain 用到column
        mapval.put("pkColumn", autoTable.getPrimaryKeyColumn());//主键
        mapval.put("deleteFlagColumn", autoTable.getDeleteFlagColumn());//逻辑删除标识
        mapval.put("primaryKeyColumn", autoTable.getPrimaryKeyColumn());//逻辑删除标识
        mapval.put("updateAccountColumn", autoTable.getUpdateAccountColumn());//逻辑删除标识
        mapval.put("updateTimeColumn", autoTable.getUpdateTimeColumn());//逻辑删除标识
        mapval.put("createAccountColumn", autoTable.getCreateAccountColumn());//逻辑删除标识
        mapval.put("createTimeColumn", autoTable.getCreateTimeColumn());//逻辑删除标识

        //业务框架 -- domain 用到column
        mapval.put("basicColumnList", autoTable.getBusinessColumnList());//表中基础属性

        //domain用到的随机serlizer
        mapval.put("radomMathInt", UtilRandomFigure.getRandom100Per()*UtilRandomFigure.getRandom100Per()*UtilRandomFigure.getRandom100Per());//随机

        return mapval;
    }


    private String _getRequestValue(String value){
        try {
            Gson gson=new Gson();
            //gson
            Type type = new TypeToken<Collection<TableInfo>>(){}.getType();
            Collection<TableInfo> tableInfos = gson.fromJson(value,type);
            for(TableInfo each:tableInfos){
//                AutoMakeCodeBussiness.makeCodeByTable(each);
            }
        }catch (Exception ex){
            logger.error("MicMsg",ex);
        }
        return null;
    }

    public static void main(String args [] ){

//        try {
//            URI uri =  new URI("http://erp.jd.com");
//            Desktop dp = Desktop.getDesktop();
//            dp.browse(uri);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        System.out.println("TETETE :: "+JavaType.BYTE.getKey());

        try {
            AutoTable autoTable = new AutoTable();
            autoTable.setAuthorName("大胖子");
            autoTable.setTableSqlName("ta_stu_pe");
            autoTable.prepareAllnameBysql_name();
            autoTable.setTableComment("测试看看2020");
            autoTable.setExtendsClassList(null);

            //业务列
            List<AutoColumn> basicColumns = new ArrayList<AutoColumn>();
            for (int i=0;i<5;i++){
                AutoColumn nomalAuto = new AutoColumn();
                nomalAuto.setColumnSQLComment("业务列的数据库的备注" + i);
                nomalAuto.setColumnSQLType(MySQLType.VARCHAR);
                nomalAuto.prepareDataType();
                nomalAuto.setColumnSQLName("ta_Test_NBA"+i);
                nomalAuto.prepareAllnameBysql_name();
                basicColumns.add(nomalAuto);
            }
            autoTable.setBusinessColumnList(basicColumns);

            String val = JsonUtil.toJson(autoTable);

            ResponseEntity entity = new MicMsg().testWebSer2(null, val);

            System.out.println(entity);
            System.out.println("————END————");
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

}
