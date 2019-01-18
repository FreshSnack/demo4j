package com.amar.poi;

import com.amar.util.AmarUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author dingmx
 * @date 2019/1/17 17:09
 */
public class XWPFDocumentTest {

    @Test
    public void testXWPFDoc() throws Exception {
        File htmlFile = new File(XWPFDocumentTest.class.getResource("/test.html").getPath());
        String htmlStr = FileUtils.readFileToString(htmlFile);
        XWPFDocument doc = new XWPFDocument();
        XWPFParagraph p = doc.createParagraph();
        XWPFRun r = p.createRun();
        r.setText(htmlStr);
        FileOutputStream output = new FileOutputStream(AmarUtils.getTmpPath() + "test.doc");
        doc.write(output);
        output.close();
        doc.close();
    }

    @Test
    public void testInsertPic() throws Exception {
        XWPFDocument doc = new XWPFDocument();
        XWPFParagraph p = doc.createParagraph();
        XWPFRun r = p.createRun();
        r.setText("logo.png");
        r.addBreak();
        r.addPicture(AmarUtils.getResStream("logo.png"), XWPFDocument.PICTURE_TYPE_PNG,
                "logo.png", Units.toEMU(200), Units.toEMU(200));
        r.addBreak(BreakType.TEXT_WRAPPING);
        r.addTab();
        r.setText("logo.png");
        FileOutputStream outputStream = new FileOutputStream(AmarUtils.getTmpPath() + "pic.doc");
        doc.write(outputStream);
        outputStream.close();
        doc.close();
    }
}
