package com.mic.generator.bussiness.autocode.util;

import com.mic.generator.common.util.FileDirUtil;
import org.apache.log4j.Logger;

/**
 * 自动代码的框架生成
 * Created by caoxue on 2016/6/16.
 */
public class FrameAutoUtil {

    private static Logger logger=Logger.getLogger("FrameAutoUtil");

    public static boolean AutoFrameMake(String framePath, String autoProjBasePath){
        boolean isSuccess = false;
        try {
            FileDirUtil.copyAll(framePath, autoProjBasePath);
            isSuccess = true;
        }catch (Exception ex){
            logger.error("FrameAutoUtil,ERROR:",ex);
            isSuccess = false;
        }
        return isSuccess;
    }

}
