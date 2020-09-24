package com.mic.generator.beforeaction.basic.tools;

import com.mic.generator.beforecommon.util.json.JsonUtil;
import com.mic.generator.beforedomain.basic.tools.Tools;
import com.mic.generator.beforeservice.basic.tools.ToolsManagementService;
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
 * 工具类管理Controller类
 *
 * User: admin
 * DateTime: 12-10-24 下午4:10
 * Version: 1.0
 */
@Controller
@RequestMapping("/toolsManagement")
public class ToolsManagementController {

    private static Logger logger = Logger.getLogger(ToolsManagementController.class);

    @Resource
    private ToolsManagementService toolsManagementService;

    /**
     * 工具类管理初始页面
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

        return "/basic/tools/tools-management-index";
    }

    /**
     * 工具类列表查询
     * @param model
     * @return 返回页面
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Model query(Tools tools, Model model) {

        List<Tools> toolsList = toolsManagementService.query(tools);

        model.addAttribute("rows", toolsList);
        return model;
    }

    /**
     * 工具类更新
     * @param model
     * @return 返回页面
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Model update(String params, Model model) {

        List<Tools> toolsList = (List<Tools>) JsonUtil.jsonToList(params);
//        List<Tools> toolsList = JsonUtil.fromJsonByGoogle(params, new TypeToken<List<Tools>>() {
//        });
        Boolean flag = toolsManagementService.update(toolsList);
        model.addAttribute("flag", flag);
        return model;
    }
}
