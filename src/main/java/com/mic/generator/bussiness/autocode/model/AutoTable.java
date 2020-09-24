package com.mic.generator.bussiness.autocode.model;

import com.mic.generator.bussiness.autocode.util.UtilAutoColumnFactory;
import com.mic.generator.bussiness.autocode.util.UtilAutoNameChange;
import com.mic.generator.bussiness.autocode.util.string.StringUtil;

import java.util.List;

/**
 * 数据源的一个Table基础信息，for自动生成代码用
 * Created by caoxue on 2016/5/20.
 */
public class AutoTable extends AutoBase{

    public AutoTable(){
        setDeleteFlagColumn(UtilAutoColumnFactory.createDeleteFlagColumn());
        setPrimaryKeyColumn(UtilAutoColumnFactory.createPrimaryKeyColumn());
        setUpdateAccountColumn(UtilAutoColumnFactory.createUpdateAccountColumn());
        setUpdateTimeColumn(UtilAutoColumnFactory.createUpdateTimeColumn());
        setCreateAccountColumn(UtilAutoColumnFactory.createCreateAccountColumn());
        setCreateTimeColumn(UtilAutoColumnFactory.createCreateTimeColumn());
    }

    private String tableSqlName;//表名字。eg：lgd_student
    private String domainPropertyNameEN;//domain用来做属性 -- 首字母小写 波浪式命名  eg:lgdStudent
    private String domainClassNameEN;//domain的类名 -- 首字母大写 波浪式命名；eg:LgdStudent
    //表显示，注释
    private String tableComment;//表的注释；eg：理工大的学生表

    private List<ExtendsClassInfoAuto> extendsClassList;//扩展功能类的信息

    //表中业务属性
    private List<AutoColumn> businessColumnList;

    //表中框架属性
    private AutoColumn deleteFlagColumn;    //逻辑删除标识
    private AutoColumn primaryKeyColumn;    //主键
    private AutoColumn updateAccountColumn; //更新账号列
    private AutoColumn updateTimeColumn;    //更新时间列
    private AutoColumn createAccountColumn; //创建账号列
    private AutoColumn createTimeColumn;    //创建时间列


    public void prepareAllnameBysql_name(){
        if(!StringUtil.isEmpty(this.tableSqlName)){
            prepareAllnameBysqlNameWithSplit("_");
        }
    }
    public void prepareAllnameBysqlNameWithSplit(String splitchar){
        if(!StringUtil.isEmpty(this.tableSqlName)){
            setDomainClassNameEN(UtilAutoNameChange.sqlName2className(this.tableSqlName,splitchar));
            setDomainPropertyNameEN(UtilAutoNameChange.sqlName2propertyName(this.tableSqlName,splitchar));
        }
    }



    public AutoColumn getDeleteFlagColumn() {
        return deleteFlagColumn;
    }

    public void setDeleteFlagColumn(AutoColumn deleteFlagColumn) {
        this.deleteFlagColumn = deleteFlagColumn;
    }

    public AutoColumn getPrimaryKeyColumn() {
        return primaryKeyColumn;
    }

    public void setPrimaryKeyColumn(AutoColumn primaryKeyColumn) {
        this.primaryKeyColumn = primaryKeyColumn;
    }

    public AutoColumn getUpdateAccountColumn() {
        return updateAccountColumn;
    }

    public void setUpdateAccountColumn(AutoColumn updateAccountColumn) {
        this.updateAccountColumn = updateAccountColumn;
    }

    public AutoColumn getUpdateTimeColumn() {
        return updateTimeColumn;
    }

    public void setUpdateTimeColumn(AutoColumn updateTimeColumn) {
        this.updateTimeColumn = updateTimeColumn;
    }

    public AutoColumn getCreateAccountColumn() {
        return createAccountColumn;
    }

    public void setCreateAccountColumn(AutoColumn createAccountColumn) {
        this.createAccountColumn = createAccountColumn;
    }

    public AutoColumn getCreateTimeColumn() {
        return createTimeColumn;
    }

    public void setCreateTimeColumn(AutoColumn createTimeColumn) {
        this.createTimeColumn = createTimeColumn;
    }

    public String getTableSqlName() {
        return tableSqlName;
    }



    public void setTableSqlName(String tableSqlName) {
        this.tableSqlName = tableSqlName;
    }

    public String getDomainPropertyNameEN() {
        return domainPropertyNameEN;
    }

    public void setDomainPropertyNameEN(String domainPropertyNameEN) {
        this.domainPropertyNameEN = domainPropertyNameEN;
    }

    public String getDomainClassNameEN() {
        return domainClassNameEN;
    }

    public void setDomainClassNameEN(String domainClassNameEN) {
        this.domainClassNameEN = domainClassNameEN;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }
    @Deprecated
    public List<ExtendsClassInfoAuto> getExtendsClassList() {
        return extendsClassList;
    }

    @Deprecated
    public void setExtendsClassList(List<ExtendsClassInfoAuto> extendsClassList) {
        this.extendsClassList = extendsClassList;
    }

    public List<AutoColumn> getBusinessColumnList() {
        return businessColumnList;
    }

    public void setBusinessColumnList(List<AutoColumn> businessColumnList) {
        this.businessColumnList = businessColumnList;
    }
}
