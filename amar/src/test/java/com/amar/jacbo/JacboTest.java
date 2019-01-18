package com.amar.jacbo;

import com.amar.poi.JacobUtils;
import com.amar.poi.MSWordManager;
import com.amar.util.AmarUtils;
import org.junit.Test;

/**
 * @author dingmx
 * @date 2019/1/18 01:06
 */
public class JacboTest {

    @Test
    public void testHtml2Word() {
        String wordFile = AmarUtils.getTmpPath() + "jacbo.doc";
        String htmlFile = JacboTest.class.getResource("/test.html").getPath();
        JacobUtils.htmlToWord(htmlFile, wordFile);
        //JacobUtils.wordConveter(wordFile);
    }

    @Test
    public void testCreateDoc() {
        MSWordManager ms=new MSWordManager(true);
        //生成一个MSwordManager对象,并且设置显示Word程序
        ms.createNewDocument();
        //创建一个新的.doc文件
        ms.insertText("Test jacob");
        //插入文本
        ms.save("D:\\1.doc");
        //保存.doc文件
        ms.close();
        ms.closeDocument();
    }

    @Test
    public void testConvert() {
        JacobUtils.wordConveter("F:\\idea\\workspace\\demo4j\\amar\\out\\html.doc");
    }
}
