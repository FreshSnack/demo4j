package com.amar.poi;

import com.amar.util.AmarUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.junit.Test;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Author: mxding
 * @Date: 2019-01-17 19:15
 */
public class HWPFDocumentTest {

    @Test
    public void testHWPFDoc() throws Exception {
        HWPFDocument doc = new HWPFDocument(AmarUtils.getResStream("test.doc"));
        Range range = doc.getRange();
        range.replaceText("{NAME}", "TOM");
        OutputStream os = new FileOutputStream(AmarUtils.getTmpPath() + "test.doc");
        doc.write(os);
    }

    @Test
    public void convertDoc2Docx() {
        //InputStream inputStream = AmarUtils.getResStream("test.doc");

    }
}
