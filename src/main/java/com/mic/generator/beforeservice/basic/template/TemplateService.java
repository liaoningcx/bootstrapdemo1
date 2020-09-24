package com.mic.generator.beforeservice.basic.template;


import com.mic.generator.bussiness.autocode.model.TemplateFile;

import java.util.List;

/**
 * 模板管理Service类
 * User: admin
 * Date: 13-6-27
 * Time: 上午9:01
 */
public interface TemplateService {

    /**
     * 模板列表查询
     * @param template 模板实体
     * @return 模板列表
     */
    public List<TemplateFile> query(TemplateFile template);
}
