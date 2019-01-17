package com.amar.jacbo;

import com.amar.poi.JacobUtils;
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
        JacobUtils.wordConveter(wordFile);
    }
}
