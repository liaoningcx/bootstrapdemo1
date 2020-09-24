package com.mic.generator.beforeaction;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 默认控制器
 *
 * User: admin
 * DateTime: 12-10-24 下午4:10
 * Version: 1.0
 */
@Controller
@RequestMapping("/")
public class DefaultController {

    private static Logger logger = Logger.getLogger(DefaultController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
     public String index(
            @RequestParam(value = "locale", required = false) Locale locale,
            HttpServletRequest request,
            HttpServletResponse response) {

        return "/common/default-frame";
    }

    @RequestMapping(value = "/top", method = RequestMethod.GET)
    public String top(
            @RequestParam(value = "locale", required = false) Locale locale,
            HttpServletRequest request,
            HttpServletResponse response,
            Model view) {

        return "/common/top";
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String menu(
            @RequestParam(value = "locale", required = false) Locale locale,
            HttpServletRequest request,
            HttpServletResponse response,
            Model view) {

        return "/common/menu";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(
            HttpServletRequest request,
            HttpServletResponse response,
            Model view) {

        return "/index";
    }

    @RequestMapping("/dologout")
    @ResponseBody
    public String logout(HttpServletResponse response) {
        return "success";
    }
}
