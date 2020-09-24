package com.mic.generator.beforeservice.basic.mapping;

import com.mic.generator.beforedomain.basic.mapping.DataTypeMapping;

import java.util.List;

/**
 * 数据类型映射Service类
 * User: admin
 * Date: 13-6-27
 * Time: 上午9:01
 */
public interface DataTypeMappingService {

    /**
     * 数据类型映射列表查询
     * @param dataTypeMapping 数据类型映射实体
     * @return
     */
    public List<DataTypeMapping> query(DataTypeMapping dataTypeMapping);

    /**
     * 数据类型映射更新
     * @param dataTypeMappingList 数据类型映射实体列表
     * @return
     */
    public Boolean update(List<DataTypeMapping> dataTypeMappingList);
}
