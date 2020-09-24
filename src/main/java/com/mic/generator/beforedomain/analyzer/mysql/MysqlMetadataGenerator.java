package com.mic.generator.beforedomain.analyzer.mysql;

/**
 * 字段信息实体类
 * User: admin
 * Date: 13-9-16
 * Time: 下午11:32
 * To change this template use File | Settings | File Templates.
 */
public class MysqlMetadataGenerator {

    private String tableSchema;
    private String tableSqlName;
    private String tableComment;
    private String packagePath;
    private String author;
    private String mysqlMetadataGeneratorJson;
    private String generatorType;
    private String projectDirectory;

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getTableName() {
        return tableSqlName;
    }

    public void setTableName(String tableSqlName) {
        this.tableSqlName = tableSqlName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMysqlMetadataGeneratorJson() {
        return mysqlMetadataGeneratorJson;
    }

    public void setMysqlMetadataGeneratorJson(String mysqlMetadataGeneratorJson) {
        this.mysqlMetadataGeneratorJson = mysqlMetadataGeneratorJson;
    }

    public String getGeneratorType() {
        return generatorType;
    }

    public void setGeneratorType(String generatorType) {
        this.generatorType = generatorType;
    }

    public String getProjectDirectory() {
        return projectDirectory;
    }

    public void setProjectDirectory(String projectDirectory) {
        this.projectDirectory = projectDirectory;
    }
}
