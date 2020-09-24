package com.mic.generator.beforeservice.basic.tools;

import com.mic.generator.beforedomain.basic.tools.Tools;

import java.util.List;

/**
 * 工具类Service类
 * User: admin
 * Date: 13-6-27
 * Time: 上午9:01
 */
public interface ToolsManagementService {

    /**
     * 工具类列表查询
     * @param tools 工具类实体
     * @return
     */
    public List<Tools> query(Tools tools);

    /**
     * 工具类更新
     * @param toolsList 工具类实体列表
     * @return
     */
    public Boolean update(List<Tools> toolsList);
}
