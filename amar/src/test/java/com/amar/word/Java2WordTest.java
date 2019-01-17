package com.amar.word;

import org.junit.Test;
import com.heavenlake.wordapi.Document;

/**
 * @author dingmx
 * @date 2019/1/18 00:22
 */
public class Java2WordTest {

    @Test
    public void testInsert() throws Exception {
        Document doc = new Document();
        doc.open(Java2WordTest.class.getResource("/test.doc").getPath());//打开文件
        doc.insert("ppppp");
        doc.close(true);
    }
}
