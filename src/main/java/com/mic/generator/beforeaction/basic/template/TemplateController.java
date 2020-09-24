package com.mic.generator.beforeaction.basic.template;

import com.mic.generator.beforeservice.basic.template.TemplateService;
import com.mic.generator.bussiness.autocode.model.TemplateFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;

/**
 * 模板管理Controller类
 * User: admin
 * Date: 13-9-22
 * Time: 下午7:52
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/template")
public class TemplateController {

    @Resource
    private TemplateService templateService;

    /**
     * 模板管理初始页面
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

        return "/basic/template/template-index";
    }

    /**
     * 模板管理列表查询
     * @param template
     * @param model
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Model query(TemplateFile template, Model model) {

        List<TemplateFile> templateList = templateService.query(template);
        model.addAttribute("rows", templateList);
        return model;
    }

    /**
     * 模板维护页面
     * @return 返回页面
     */
    @RequestMapping(value = "/update")
    public String update(TemplateFile template, Model model) {
        return "/basic/template/template-update";
    }

    /**
     * 模板查看页面
     * @return 返回页面
     */
    @RequestMapping(value = "/show")
    public String show(TemplateFile template, Model model) {
        StringBuffer stringBuffer = new StringBuffer();
        String fileName = this.getClass().getResource("/").toString();
        fileName = fileName.substring("file:/".length(), fileName.length())+ "//templet//" + template.getTemplateName();
        try {
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
            String tempString = reader.readLine();
            while (tempString != null) {
                stringBuffer.append(tempString+"\n");
                tempString = reader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("");
        }
        String[] type = template.getFileName().split("\\.");
        model.addAttribute("type", type[1]);
        model.addAttribute("context",stringBuffer.toString());
        return "/basic/template/template-show";
    }

    /**
     * 保存模板
     * @return 返回页面
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Model save(TemplateFile template, Model model) {
        System.out.println("模板内容======================================");
        System.out.println(template);
        System.out.println("模板内容======================================");
        return model;
    }
}
