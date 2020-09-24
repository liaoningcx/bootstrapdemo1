package com.mic.generator.beforedao.analyzer.mysql.impl;

import com.mic.generator.frame.dao.BaseDao;
import com.mic.generator.beforedao.analyzer.mysql.MysqlMetadataGeneratorDao;
import com.mic.generator.beforedomain.analyzer.mysql.ColumnGenerator;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 根据MYSQL元数据生成代码Dao层实现类
 * User: admin
 * Date: 13-9-16
 * Time: 下午11:46
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class MysqlMetadataGeneratorDaoImpl extends BaseDao implements MysqlMetadataGeneratorDao {

    public static final String NAME_SPACE = "MysqlMetadataGeneratorDao";             //sqlMap命名空间

    /**
     * 查询数据库列表
     * @return 数据库列表
     */
    public List<ColumnGenerator> selectDatabase(){
        return this.queryForList(this.getNamespace("selectDatabase"));
    }

    /**
     * 根据数据库名查询所有表
     * @param columnGenerator 字段信息实体类
     * @return 表列表
     */
    public List<ColumnGenerator> selectTableNames(ColumnGenerator columnGenerator){
        return this.queryForList(this.getNamespace("selectTableNames"), columnGenerator);
    }

    /**
     * 根据数据库名查询所有字段
     * @param columnGenerator 字段信息实体类
     * @return 字段列表
     */
    public List<ColumnGenerator> selectColumnNames(ColumnGenerator columnGenerator){
        return this.queryForList(this.getNamespace("selectColumnNames"), columnGenerator);
    }


    /**
     * mybatis的namespace
     * @param statement
     * @return 命名空间
     */
    private String getNamespace(String statement) {
        return NAME_SPACE + "." + statement;
    }

}
