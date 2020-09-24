package com.mic.generator.beforedomain.table;

import java.util.List;

/**
 * Created by caoxue on 2016/2/26.
 */
public class TableInfo {

    /// <summary>
    /// 数据库名称
    /// </summary>
    private String db_Name;

    /// <summary>
    /// 数据库下的表名
    /// </summary>
    private String table_Name;

    /// <summary>
    /// 该表下的所有列信息
    /// </summary>
    private List<Column> columnsList;



    public String getDb_Name() {
        return db_Name;
    }

    public void setDb_Name(String db_Name) {
        this.db_Name = db_Name;
    }

    public String getTable_Name() {
        return table_Name;
    }

    public void setTable_Name(String table_Name) {
        this.table_Name = table_Name;
    }

    public List<Column> getColumnsList() {
        return columnsList;
    }

    public void setColumnsList(List<Column> columnsList) {
        this.columnsList = columnsList;
    }
}
