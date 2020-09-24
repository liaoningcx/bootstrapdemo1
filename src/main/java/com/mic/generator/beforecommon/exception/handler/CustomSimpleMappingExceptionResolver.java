package com.mic.generator.beforecommon.exception.handler;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {
    private static final Logger logger = Logger.getLogger(CustomSimpleMappingExceptionResolver.class);

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
                                              HttpServletResponse response, Object handler, Exception ex) {
        // Expose ModelAndView for chosen error view.
        logger.error(ex);
        String viewName = determineViewName(ex, request);
        if (viewName != null) {// 格式返回
            if (!isAjaxSumbit(request)) {
                // 如果不是异步请求
                // Apply HTTP status code for error views, if specified.
                // Only apply it if we're processing a top-level request.
                Integer statusCode = determineStatusCode(request, viewName);
                if (statusCode != null) {
                    applyStatusCodeIfPossible(request, response, statusCode);
                }
                return getModelAndView(viewName, ex, request);
            } else {// JSON格式返回
                try {
                    response.setContentType("text/xml;charset=UTF-8");//中文乱码
                    PrintWriter writer = response.getWriter();
                    writer.write(ex.getMessage());
                    writer.flush();
                } catch (IOException e) {
                    logger.error(e);
                }
                return null;

            }
        } else {
            return null;
        }
    }

    /**
     * 判断是否ajax提交
     *
     * @param request
     *
     * @return
     */
    public boolean isAjaxSumbit(HttpServletRequest request) {
        return (request.getHeader("accept").indexOf("application/json") > -1 || (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1));
    }
}