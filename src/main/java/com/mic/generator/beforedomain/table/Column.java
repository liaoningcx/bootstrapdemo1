package com.mic.generator.beforedomain.table;

/**
 * Created by caoxue on 2016/2/26.
 */
public class Column {

    /// <summary>
    /// 列名,也用作实例名
    /// </summary>
    private String column_Name;

    /// <summary>
    /// 类型，也用作实例类型
    /// </summary>
    private String date_Type;

    /// <summary>
    /// 列名的注释
    /// </summary>
    private String column_Comment;

    public String getColumn_Comment() {
        return column_Comment;
    }

    public void setColumn_Comment(String column_Comment) {
        this.column_Comment = column_Comment;
    }

    public String getColumn_Name() {
        return column_Name;
    }

    public void setColumn_Name(String column_Name) {
        this.column_Name = column_Name;
    }

    public String getDate_Type() {
        return date_Type;
    }

    public void setDate_Type(String date_Type) {
        this.date_Type = date_Type;
    }
}
