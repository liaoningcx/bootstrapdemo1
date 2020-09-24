package com.mic.generator.beforeservice.analyzer.mysql;

import com.mic.generator.beforedomain.analyzer.mysql.ColumnGenerator;
import com.mic.generator.beforedomain.analyzer.mysql.MysqlMetadataGenerator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * 根据MYSQL元数据生成代码Service层接口类
 * User: admin
 * Date: 13-9-16
 * Time: 下午11:46
 * To change this template use File | Settings | File Templates.
 */
public interface MysqlMetadataGeneratorService {

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

    /**
     * 生成代码-压缩包下载
     */
    public ResponseEntity<byte[]> generatorDownLoad(MysqlMetadataGenerator mysqlMetadataGenerator, HttpHeaders headers);

    /**
     * 生成代码-导入工程
     */
    public void generatorImportProject(MysqlMetadataGenerator mysqlMetadataGenerator);
}
