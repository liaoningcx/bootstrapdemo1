package com.mic.generator.beforeaction.analyzer.mysql;

import com.mic.generator.beforedomain.analyzer.mysql.ColumnGenerator;
import com.mic.generator.beforedomain.analyzer.mysql.MysqlMetadataGenerator;
import com.mic.generator.beforeservice.analyzer.mysql.MysqlMetadataGeneratorService;
import com.mic.generator.common.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * MYSQL元数据代码生成Controller
 * @author: dbadmin
 * @time: 13-9-4  下午4:37
 */
@Controller
@RequestMapping("/mysqlMetadataGenerator")
public class MysqlMetadataGeneratorController {
    private static Logger logger = Logger.getLogger(MysqlMetadataGeneratorController.class);

    @Resource
    private MysqlMetadataGeneratorService mysqlMetadataGeneratorService;

    /**
     * 代码生成初始页面
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(
            @RequestParam(value = "locale", required = false) Locale locale,
            HttpServletRequest request,
            HttpServletResponse response, Model model) {

        List<ColumnGenerator> columnGeneratorList =  mysqlMetadataGeneratorService.selectDatabase();
        model.addAttribute("columnGeneratorList", columnGeneratorList);
        return "/analyzer/mysql/mysql-metadata-generator-index";
    }

    /**
     * 数据库表信息查询
     */
    @RequestMapping(value = "/selectTableNames", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public List<ColumnGenerator> selectTableNames(ColumnGenerator columnGenerator) {

        List<ColumnGenerator> columnGeneratorList =  mysqlMetadataGeneratorService.selectTableNames(columnGenerator);
        return columnGeneratorList;
    }

    /**
     * 字段信息查询
     */
    @RequestMapping(value = "/selectColumnNames", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public List<ColumnGenerator> selectColumnNames(ColumnGenerator columnGenerator) {

        List<ColumnGenerator> columnGeneratorList =  mysqlMetadataGeneratorService.selectColumnNames(columnGenerator);
        return columnGeneratorList;
    }

    /**
     * 生成代码-压缩包下载
     */
    @RequestMapping(value = "/generator-downLoad")
    @ResponseBody
    public ResponseEntity<byte[]> generatorDownLoad(MysqlMetadataGenerator mysqlMetadataGenerator){
        HttpHeaders headers = new HttpHeaders();
        try {
            String jsonMsg = JsonUtil.toJson(mysqlMetadataGenerator);
            logger.info("前台入参："+jsonMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mysqlMetadataGeneratorService.generatorDownLoad(mysqlMetadataGenerator, headers);
    }

    /**
     * 生成代码-导入工程
     */
    @RequestMapping(value = "/generator-importProject", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public void generatorImportProject(MysqlMetadataGenerator mysqlMetadataGenerator) {
        mysqlMetadataGeneratorService.generatorImportProject(mysqlMetadataGenerator);
    }
}
