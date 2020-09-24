package com.mic.generator.bussiness.autocode.util;

import com.mic.generator.bussiness.autocode.constfield.MySQLType;
import com.mic.generator.bussiness.autocode.model.AutoColumn;
import com.mic.generator.bussiness.autocode.model.AutoTable;

/**
 * 自动创建项目bean的工厂类，用于创建自动表，自动列（如主键/逻辑删除标识列）
 * Created by cao_x on 2020/4/20.
 */
public class UtilAutoColumnFactory {

    /**
     * 创建逻辑删除标识列
     * @return
     */
    public static AutoColumn createDeleteFlagColumn() {
        return createColumn("delflag",MySQLType.TINYINT,"逻辑删除标识");
    }
    /**
     * 创建主键
     * @return
     */
    public static AutoColumn createPrimaryKeyColumn() {
        return createColumn("V_ID",MySQLType.VARCHAR,"主键");
    }
    /**
     * 创建更新用户
     * @return
     */
    public static AutoColumn createUpdateAccountColumn() {
        return createColumn("UPDATE_ACCOUNT",MySQLType.VARCHAR,"更新用户");
    }
    /**
     * 创建更新时间
     * @return
     */
    public static AutoColumn createUpdateTimeColumn() {
        return createColumn("UPDATE_TIME",MySQLType.DATETIME,"更新时间");
    }
    /**
     * 创建创建用户
     * @return
     */
    public static AutoColumn createCreateAccountColumn() {
        return createColumn("CREATE_ACCOUNT",MySQLType.VARCHAR,"创建用户");
    }

    /**
     * 创建创建时间
     * @return
     */
    public static AutoColumn createCreateTimeColumn() {
        return createColumn("CREATE_TIME",MySQLType.DATETIME,"创建时间");
    }
    /**
     * 创建列
     * @param columnSqlName sql的列名
     * @param mySQLType sql的类型
     * @param columnSqlComment sql注释
     * @return
     */
    public static AutoColumn createColumn(String columnSqlName,MySQLType mySQLType,String columnSqlComment) {
        AutoColumn autoColumn = new AutoColumn();
        autoColumn.setColumnSQLName(columnSqlName);
        autoColumn.prepareAllnameBysql_name();
        autoColumn.setColumnSQLType(mySQLType);
        autoColumn.prepareDataType();
        autoColumn.setColumnSQLComment(columnSqlComment);
        return autoColumn;
    }


}
