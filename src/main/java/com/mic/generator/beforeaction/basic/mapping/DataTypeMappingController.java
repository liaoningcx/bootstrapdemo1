package com.mic.generator.beforeaction.basic.mapping;

import com.mic.generator.beforecommon.util.json.JsonUtil;
import com.mic.generator.beforedomain.basic.mapping.DataTypeMapping;
import com.mic.generator.beforeservice.basic.mapping.DataTypeMappingService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

/**
 * 数据类型映射Controller类
 *
 * User: admin
 * DateTime: 12-10-24 下午4:10
 * Version: 1.0
 */
@Controller
@RequestMapping("/dataTypeMapping")
public class DataTypeMappingController {

    private static Logger logger = Logger.getLogger(DataTypeMappingController.class);

    @Resource
    private DataTypeMappingService dataTypeMappingService;

    /**
     * 数据类型映射初始页面
     * @param locale
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(
            @RequestParam(value = "locale", required = false) Locale locale,
            HttpServletRequest request,
            HttpServletResponse response) {

        return "/basic/mapping/data-type-mapping";
    }

    /**
     * 数据类型映射列表查询
     * @param model
     * @return 返回页面
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Model query(DataTypeMapping dataTypeMapping, Model model) {

        List<DataTypeMapping> dataTypeMappingList = dataTypeMappingService.query(dataTypeMapping);

        model.addAttribute("rows", dataTypeMappingList);
        return model;
    }

    /**
     * 数据类型映射更新
     * @param model
     * @return 返回页面
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Model update(String params, Model model) {

        List<DataTypeMapping> dataTypeMappingList = (List<DataTypeMapping>) JsonUtil.jsonToList(params);
//        List<DataTypeMapping> dataTypeMappingList = JsonUtil.fromJsonByGoogle(params, new TypeToken<List<DataTypeMapping>>() {
//        });
        Boolean flag = dataTypeMappingService.update(dataTypeMappingList);
        model.addAttribute("flag", flag);
        return model;
    }
}
