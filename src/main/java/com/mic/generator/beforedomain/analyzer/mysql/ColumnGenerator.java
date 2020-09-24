package com.mic.generator.beforedomain.analyzer.mysql;

/**
 * 字段信息(用于解析模板)
 * User: admin
 * Date: 13-9-16
 * Time: 下午11:32
 * To change this template use File | Settings | File Templates.
 */
public class ColumnGenerator {

    private String tableSchema;
    private String tableSqlName;
    private String columnName;
    private String dataType;
    private String columnKey;
    private String extra;
    private String columnComment;
    private String flag;
    private String filter;

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

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
