package com.mic.generator.beforeservice.analyzer.mysql.impl;

import com.google.gson.reflect.TypeToken;
import com.mic.generator.beforecommon.util.file.FileZipUtil;
import com.mic.generator.bussiness.autocode.util.UtilAutoNameChange;
import com.mic.generator.bussiness.autocode.util.string.StringUtil;
import com.mic.generator.beforecommon.util.velocity.VelocityUtil;
import com.mic.generator.beforedao.analyzer.mysql.MysqlMetadataGeneratorDao;
import com.mic.generator.beforedomain.analyzer.mysql.Column;
import com.mic.generator.beforedomain.analyzer.mysql.ColumnGenerator;
import com.mic.generator.beforedomain.analyzer.mysql.MysqlMetadataGenerator;
import com.mic.generator.beforedomain.basic.mapping.DataTypeMapping;
import com.mic.generator.beforedomain.basic.tools.Tools;
import com.mic.generator.beforeservice.analyzer.mysql.MysqlMetadataGeneratorService;
import com.mic.generator.beforeservice.basic.mapping.DataTypeMappingService;
import com.mic.generator.beforeservice.basic.template.TemplateService;
import com.mic.generator.beforeservice.basic.tools.ToolsManagementService;
import com.mic.generator.bussiness.autocode.model.TemplateFile;
import com.mic.generator.common.util.JsonUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 根据MYSQL元数据生成代码Dao层实现类
 * User: admin
 * Date: 13-9-16
 * Time: 下午11:46
 * To change this template use File | Settings | File Templates.
 */
@Service
public class MysqlMetadataGeneratorServiceImpl implements MysqlMetadataGeneratorService {

    @Resource
    private MysqlMetadataGeneratorDao mysqlMetadataGeneratorDao;
    @Resource
    private TemplateService templateService;
    @Resource
    private DataTypeMappingService dataTypeMappingService;
    @Resource
    private ToolsManagementService toolsManagementService;



    /**
     * 查询数据库列表
     * @return 数据库列表
     */
    public List<ColumnGenerator> selectDatabase(){
        return mysqlMetadataGeneratorDao.selectDatabase();
    }

    /**
     * 根据数据库名查询所有表
     * @param columnGenerator 字段信息实体类
     * @return 表列表
     */
    public List<ColumnGenerator> selectTableNames(ColumnGenerator columnGenerator){
        return mysqlMetadataGeneratorDao.selectTableNames(columnGenerator);
    }

    /**
     * 根据数据库名查询所有字段
     * @param columnGenerator 字段信息实体类
     * @return 字段列表
     */
    public List<ColumnGenerator> selectColumnNames(ColumnGenerator columnGenerator){
        return mysqlMetadataGeneratorDao.selectColumnNames(columnGenerator);
    }

//    public static void main(String [] args){
//        String currentProjectPath =  new MysqlMetadataGeneratorServiceImpl().getClass().getResource("/").toString();
//        String currentProjectPath2 =  currentProjectPath.substring("file:/".length());
//        String templatePath = currentProjectPath + "templet"+File.separator;
//        System.out.print(currentProjectPath);
//        System.out.print(templatePath);
//    }

    /**
     * 生成代码-压缩包下载
     */
    public ResponseEntity<byte[]> generatorDownLoad(MysqlMetadataGenerator mysqlMetadataGenerator,HttpHeaders headers){
        try {
            HashMap<String, Object> hashMap = this.getTemplateVariables(mysqlMetadataGenerator);

            String currentProjectPath =  this.getClass().getResource("/").toString().substring("file:/".length());
            String templatePath = currentProjectPath + "templet"+File.separator;

            //模板列表,根据xml获得相应的vm模板
            List<TemplateFile> templateList = templateService.query(new TemplateFile());
            for(TemplateFile template : templateList){
                String importFilePath = currentProjectPath + "temp"+File.separator + hashMap.get("tableClassNameEN");
                importFilePath =importFilePath + File.separator + hashMap.get("packagePath").toString().replaceAll("\\.","/");
                importFilePath =importFilePath + File.separator + template.getFilePath().replaceAll("\\.","/");
                importFilePath =importFilePath + File.separator + hashMap.get("tableClassNameEN") + template.getFileName();
                if(template.getCode().equals("sqlMapperTemplet")){
                    importFilePath = currentProjectPath + "temp"+File.separator+hashMap.get("tableClassNameEN");
                    importFilePath = importFilePath + File.separator + hashMap.get("tableClassNameEN") + template.getFileName();
                }
                File file = new File(importFilePath);
                if (!file.exists()) {
                    File filePath = new File(file.getParent());
                    filePath.mkdirs();
                }
                //解析并创建文件
                VelocityUtil.generatorCode(templatePath, template.getTemplateName(), hashMap, importFilePath);
            }

            //生成压缩包
            String folderSrcPath = currentProjectPath + "temp"+File.separator+hashMap.get("tableClassNameEN")+File.separator;
            String forderDesPath = currentProjectPath + "temp"+File.separator+hashMap.get("tableClassNameEN")+".zip";
            FileZipUtil.zip(folderSrcPath,forderDesPath,"");
            //下载文件
            File file = new File(forderDesPath);
            InputStream inputStream = new FileInputStream(file);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", new String((hashMap.get("tableClassNameEN")+".zip").getBytes("gbk"),"iso-8859-1"));

            return new ResponseEntity<byte[]>(IOUtils.toByteArray(inputStream), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 生成代码-导入工程 -- service的第一步
     */
    public void generatorImportProject(MysqlMetadataGenerator mysqlMetadataGenerator){
        try {

            //获取参数值
            HashMap<String, Object> hashMap = this.getTemplateVariables(mysqlMetadataGenerator);

            String currentProjectPath =  this.getClass().getResource("/").toString().substring("file:/".length());
            String templatePath = currentProjectPath + "templet"+File.separator;

            //模板列表
            List<TemplateFile> templateList = templateService.query(new TemplateFile());
            for(TemplateFile template : templateList){
                String importFilePath = mysqlMetadataGenerator.getProjectDirectory();
                importFilePath = importFilePath + File.separator + "src/main/java";
                importFilePath = importFilePath + File.separator + hashMap.get("packagePath").toString().replaceAll("\\.","/");
                importFilePath = importFilePath + File.separator + template.getFilePath().replaceAll("\\.","/");
                importFilePath = importFilePath + File.separator + hashMap.get("tableClassNameEN") + template.getFileName();
                if(template.getCode().equals("sqlMapperTemplet")){
                    importFilePath = mysqlMetadataGenerator.getProjectDirectory();
                    importFilePath = importFilePath + File.separator + "src/main/resources";
                    importFilePath = importFilePath + File.separator + template.getFilePath().replaceAll("\\.","/");
                    importFilePath = importFilePath + File.separator + hashMap.get("tableClassNameEN") + template.getFileName();
                }
                File file = new File(importFilePath);
                if (!file.exists()) {
                    File filePath = new File(file.getParent());
                    filePath.mkdirs();
                }
                //解析并创建文件
                VelocityUtil.generatorCode(templatePath, template.getTemplateName(), hashMap, importFilePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 获取模板变量
     * @param mysqlMetadataGenerator
     * @return
     */
    private HashMap<String, Object> getTemplateVariables(MysqlMetadataGenerator mysqlMetadataGenerator){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("packagePath", mysqlMetadataGenerator.getPackagePath());          //包路径
        hashMap.put("tableNameCH", mysqlMetadataGenerator.getTableComment());         //中文表名
        hashMap.put("tableNameEN", mysqlMetadataGenerator.getTableName());            //英文表名
        hashMap.put("author", mysqlMetadataGenerator.getAuthor());                    //创建者
        hashMap.put("dateTime", sdf.format(new Date()));                              //创建日期

        hashMap.put("tableClassNameEN",  UtilAutoNameChange.sql_name2className(mysqlMetadataGenerator.getTableName()));
//        hashMap.put("tableClassNameEN", StringUtil.humptoUpperCaseFirstOne(mysqlMetadataGenerator.getTableName(), "_"));
//        hashMap.put("tablePropertyNameEN", StringUtil.humpToLowerCaseFirstOne(mysqlMetadataGenerator.getTableName(), "_"));
        hashMap.put("tablePropertyNameEN", UtilAutoNameChange.sql_name2className(mysqlMetadataGenerator.getTableName()));

        //数据类型映射
        HashMap<String, DataTypeMapping> dataTypeMappingHashMap = new HashMap<String, DataTypeMapping>();
        List<DataTypeMapping> dataTypeMappingList = dataTypeMappingService.query(new DataTypeMapping());
        for(DataTypeMapping dataTypeMapping : dataTypeMappingList){
            dataTypeMappingHashMap.put(dataTypeMapping.getDatabaseType(), dataTypeMapping);
        }

        //工具类
        List<Tools> toolsList = toolsManagementService.query(new Tools());
        hashMap.put("toolsList", toolsList);

//        List<ColumnGenerator> columnGeneratorList2 = (List<ColumnGenerator>) JsonUtil.jsonToList(mysqlMetadataGenerator.getMysqlMetadataGeneratorJson());
//        Gson gson = new Gson();
        List<ColumnGenerator> columnGeneratorList = JsonUtil.fromJsonByGoogle(mysqlMetadataGenerator.getMysqlMetadataGeneratorJson(), new TypeToken<List<ColumnGenerator>>() {
        });
//        List<ColumnGenerator> columnGeneratorList =  null;

        //主键字段
        Column pkColumn = new Column();
        //逻辑标识字段
        Column flagColumn = new Column();
        //普通字段
        List<Column> columnList = new ArrayList<Column>();
        for(ColumnGenerator column : columnGeneratorList){
            DataTypeMapping dataTypeMapping = dataTypeMappingHashMap.get(column.getDataType());
            if(StringUtils.isBlank(column.getFilter())) {
                if(StringUtils.isNotBlank(column.getFlag())){
                    flagColumn.setColumnNameCH(column.getColumnComment());
                    flagColumn.setColumnNameEN(column.getColumnName());
                    flagColumn.setColumnPropertyName(UtilAutoNameChange.sql_name2className(column.getColumnName()));
                    flagColumn.setColumnMethodName(UtilAutoNameChange.sql_name2className(column.getColumnName()));
                    flagColumn.setJdbcType(StringUtils.upperCase(dataTypeMapping.getJdbcType()));
                    flagColumn.setJavaType(dataTypeMapping.getJavaType());
                }else if(StringUtils.isBlank(column.getColumnKey()) || !column.getColumnKey().equals("PRI")){
                    Column commonColumn = new Column();
                    commonColumn.setColumnNameCH(column.getColumnComment());
                    commonColumn.setColumnNameEN(column.getColumnName());
                    commonColumn.setColumnPropertyName(UtilAutoNameChange.sql_name2className(column.getColumnName()));
                    commonColumn.setColumnMethodName(UtilAutoNameChange.sql_name2className(column.getColumnName()));
                    commonColumn.setJdbcType(StringUtils.upperCase(dataTypeMapping.getJdbcType()));
                    commonColumn.setJavaType(dataTypeMapping.getJavaType());
                    columnList.add(commonColumn);
                }else{
                    pkColumn.setColumnNameCH(column.getColumnComment());
                    pkColumn.setColumnNameEN(column.getColumnName());
                    pkColumn.setColumnPropertyName(UtilAutoNameChange.sql_name2className(column.getColumnName()));
                    pkColumn.setColumnMethodName(UtilAutoNameChange.sql_name2className(column.getColumnName()));
                    pkColumn.setJdbcType(StringUtils.upperCase(dataTypeMapping.getJdbcType()));
                    pkColumn.setJavaType(dataTypeMapping.getJavaType());
                }
            }
        }

        hashMap.put("pkColumn",pkColumn);
        hashMap.put("flagColumn",flagColumn);
        hashMap.put("columnList",columnList);

        return hashMap;
    }


}
