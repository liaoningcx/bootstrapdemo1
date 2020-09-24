package com.mic.generator.beforedomain.basic.mapping;

/**
 * 数据类型映射Domain
 * User: admin
 * Date: 13-6-26
 * Time: 下午3:17
 */
public class DataTypeMapping {

    /**
     * Database数据类型
     */
    private String databaseType;

    /**
     * Jdbc数据类型
     */
    private String jdbcType;

    /**
     * Java数据类型
     */
    private String javaType;

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
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