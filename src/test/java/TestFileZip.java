//import com.mic.generator.common.util.file.FileHelper;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * 中文文件压缩测试
 * @author gaoyusi(http://blog.csdn.net/gaoyusi4964238)
 *
 */
public class TestFileZip {

    /*需要压缩的源文件夹路径*/
    private String folderSrcPath="c://temp//";

    /*压缩后的zip文件及其存放位置*/
    private String forderDesPath="c://temp.zip";


    /*需要压缩的源文件路径*/
    private String fileStrPaht="c://TEMP//java//8月14日有偿装卸系统异常问题汇总.docx";

    /*压缩后的zip文件及其存放位置*/
    private String fileDesPath="c://temp//8月14日有偿装卸系统异常问题汇总.zip";

    private  Logger logger=Logger.getLogger(this.getClass().getName());

    /**
     * 测试文件夹中文环境下压缩
     *
     */
    @Test
    public void testFileZip(){
        try{
//            FileHelper.zipCompress(folderSrcPath, forderDesPath);
        }catch(Exception e){
            logger.error(e);
        }
    }

    /**
     * 测试文件中文环境下压缩
     *
     */
    @Test
    public void testFolderZip(){
        try{
//            FileHelper.zipCompress(fileStrPaht, fileDesPath);
        }catch(Exception e){
            logger.error(e);
        }
    }
}