package com.mic.generator.beforedomain.analyzer.mysql;

/**
 * 字段信息实体类
 * User: admin
 * Date: 13-9-16
 * Time: 下午11:32
 * To change this template use File | Settings | File Templates.
 */
public class Column {

    /**
     * 字段中文名称
     */
    private String columnNameCH;
    /**
     * 字段英文名称
     */
    private String columnNameEN;
    /**
     * 字段属性名称
     */
    private String columnPropertyName;
    /**
     * 字段方法名称
     */
    private String columnMethodName;
    /**
     * Jdbc数据类型
     */
    private String jdbcType;
    /**
     * Java数据类型
     */
    private String javaType;

    public String getColumnNameCH() {
        return columnNameCH;
    }

    public void setColumnNameCH(String columnNameCH) {
        this.columnNameCH = columnNameCH;
    }

    public String getColumnNameEN() {
        return columnNameEN;
    }

    public void setColumnNameEN(String columnNameEN) {
        this.columnNameEN = columnNameEN;
    }

    public String getColumnPropertyName() {
        return columnPropertyName;
    }

    public void setColumnPropertyName(String columnPropertyName) {
        this.columnPropertyName = columnPropertyName;
    }

    public String getColumnMethodName() {
        return columnMethodName;
    }

    public void setColumnMethodName(String columnMethodName) {
        this.columnMethodName = columnMethodName;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }
}
