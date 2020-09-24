package com.mic.generator.bussiness.autocode.model;

/**
 * 自动代码的编写模板
 * ——即生成的文件
 */
public class TemplateFile {

    /**
     * 模板标志：是业务bussiness还是框架frame
     */
    private String flag;

    /**
     * 模板编码
     */
    private String code;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 模板相对路径
     */
    private String templatePath;
    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 生成文件的目标路径
     */
    private String filePath;

    /**
     * 生成文件的后缀名
     */
    private String fileName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
