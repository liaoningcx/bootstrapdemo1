package com.auto.controller;

import com.auto.common.CommonResponse;
import com.auto.common.Page;
import com.auto.domain.TaStuPe;
import com.auto.service.TaStuPeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 测试看看2020的Controller实现
 * User: 大胖子
 * DateTime: Thu Apr 30 07:58:28 CST 2020
 **/
@Controller
@RequestMapping("/")
public class BaseController {

    @Autowired
    private TaStuPeService taStuPeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, HttpServletResponse response) {
        return "index";
    }

}