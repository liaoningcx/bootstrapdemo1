package com.mic.generator.bussiness.autocode.model;

import com.mic.generator.bussiness.autocode.constfield.JavaType;
import com.mic.generator.bussiness.autocode.constfield.MySQLType;
import com.mic.generator.bussiness.autocode.util.UtilAutoNameChange;
import com.mic.generator.bussiness.autocode.util.db.UtilDBTypeChange;
import com.mic.generator.bussiness.autocode.util.string.StringUtil;

import java.util.List;

/**
 * 由客户端传送的表的列信息
 * Created by caoxue on 2016/5/19.
 */
public class AutoColumn extends AutoBase{

    //表
    private String columnTableSqlName;//表名字。eg：lgd_student
    private String columnTableComment;//表的注释；eg：理工大的学生表
    //命名
    private String columnSQLName;//SQL数据库中的列名  eg:stu_name
    private String columnPropertyName;//类文件属性名称.      eg:stuName
    private String columnClassName;//类文件属性名称，首字母大写。 用于get、set方法   eg：StuName
    //类型
    private String columnClassType;//类文件 属性类型     eg：String
    private String columnSQLJDBCNameTypeAllCap;//JDBC列类型--SQL类型所有字母大写    eg:VARCHAR
    private MySQLType columnSQLType;//列类型--SQL类型    eg:varchar
    //学生显示，注释
    private String columnSQLComment;//列注释           eg:学生名字

    //扩展信息字段
    private List<ExtendsClassInfoAuto> extendsClassList;

    public List<ExtendsClassInfoAuto> getExtendsClassList() {
        return extendsClassList;
    }

    public void setExtendsClassList(List<ExtendsClassInfoAuto> extendsClassList) {
        this.extendsClassList = extendsClassList;
    }

    public String getColumnTableSqlName() {
        return columnTableSqlName;
    }

    public void setColumnTableSqlName(String columnTableSqlName) {
        this.columnTableSqlName = columnTableSqlName;
    }

    public String getColumnTableComment() {
        return columnTableComment;
    }

    public void setColumnTableComment(String columnTableComment) {
        this.columnTableComment = columnTableComment;
    }

    public String getColumnSQLName() {
        return columnSQLName;
    }

    public void setColumnSQLName(String columnSQLName) {
        this.columnSQLName = columnSQLName;
    }

    public MySQLType getColumnSQLType() {
        return columnSQLType;
    }

    public void setColumnSQLType(MySQLType columnSQLType) {
        this.columnSQLType = columnSQLType;
    }

    public String getColumnSQLComment() {
        return columnSQLComment;
    }

    public void setColumnSQLComment(String columnSQLComment) {
        this.columnSQLComment = columnSQLComment;
    }

    public String getColumnClassName() {
        return columnClassName;
    }

    public void setColumnClassName(String columnClassName) {
        this.columnClassName = columnClassName;
    }

    public String getColumnPropertyName() {
        return columnPropertyName;
    }

    public void setColumnPropertyName(String columnPropertyName) {
        this.columnPropertyName = columnPropertyName;
    }

    public String getColumnClassType() {
        return columnClassType;
    }

    public void setColumnClassType(String columnClassType) {
        this.columnClassType = columnClassType;
    }

    public String getColumnSQLJDBCNameTypeAllCap() {
        return columnSQLJDBCNameTypeAllCap;
    }

    public void setColumnSQLJDBCNameTypeAllCap(String columnSQLJDBCNameTypeAllCap) {
        this.columnSQLJDBCNameTypeAllCap = columnSQLJDBCNameTypeAllCap;
    }

    public void prepareAllnameBysql_name(){
        if(!StringUtil.isEmpty(this.columnSQLName)){
            prepareAllnameBysqlNameWithSplit("_");
        }
    }
    public void prepareAllnameBysqlNameWithSplit(String splitchar){
        if(!StringUtil.isEmpty(this.columnSQLName)){
            setColumnClassName(UtilAutoNameChange.sqlName2className(this.columnSQLName,splitchar));
            setColumnPropertyName(UtilAutoNameChange.sqlName2propertyName(this.columnSQLName,splitchar));
        }
    }

    public void prepareDataType() {
        if(!StringUtil.isEmpty(this.columnSQLType.name())){
            setColumnClassType(UtilDBTypeChange.formatSqltype2JavaType(this.columnSQLType));
            setColumnSQLJDBCNameTypeAllCap(UtilAutoNameChange.sqltype2jdbctype(this.columnSQLType));
        }
    }
}
