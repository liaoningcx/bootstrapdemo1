package com.mic.generator.beforedao.analyzer.mysql;

import com.mic.generator.beforedomain.analyzer.mysql.ColumnGenerator;

import java.util.List;

/**
 * 根据MYSQL元数据生成代码Dao层接口类
 * User: admin
 * Date: 13-9-16
 * Time: 下午11:46
 * To change this template use File | Settings | File Templates.
 */
public interface MysqlMetadataGeneratorDao {

    /**
     * 查询数据库列表
     * @return 数据库列表
     */
    public List<ColumnGenerator> selectDatabase();

    /**
     * 根据数据库名查询所有表
     * @param columnGenerator 字段信息实体类
     * @return 表列表
     */
    public List<ColumnGenerator> selectTableNames(ColumnGenerator columnGenerator);

    /**
     * 根据数据库名查询所有字段
     * @param columnGenerator 字段信息实体类
     * @return 字段列表
     */
    public List<ColumnGenerator> selectColumnNames(ColumnGenerator columnGenerator);
}
