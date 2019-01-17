package com.amar.poi;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Range;
import org.junit.Test;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @Author: mxding
 * @Date: 2019-01-17 19:15
 */
public class HWPFDocumentTest {



    @Test
    public void testHWPFDoc() throws Exception {
        HWPFDocument doc = new HWPFDocument(HWPFDocumentTest.class.getResourceAsStream("/test.doc"));
        Range range = doc.getRange();
        CharacterRun cr = range.getCharacterRun(0);
        range.replaceText("{NAME}", "TOM");
        OutputStream os = new FileOutputStream("F:\\idea\\workspace\\demo4j\\amar\\output\\test.doc");
        doc.write(os);
    }
}
