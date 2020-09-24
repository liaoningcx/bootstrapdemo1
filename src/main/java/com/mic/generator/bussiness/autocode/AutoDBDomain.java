package com.mic.generator.bussiness.autocode;

/**
 * Created by dblc on 2018/8/31.
 */

import com.mic.generator.bussiness.autocode.model.AutoTable;

import java.util.List;

/**
 * 客户端传入的初始DB值
 */
public class AutoDBDomain {

    private String dbName;

    private List<AutoTable> tableList;

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public List<AutoTable> getTableList() {
        return tableList;
    }

    public void setTableList(List<AutoTable> tableList) {
        this.tableList = tableList;
    }
}
