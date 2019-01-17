package com.amar.itext;

import com.amar.util.AmarUtils;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.rtf.RtfWriter2;
import org.junit.Test;

import java.io.FileOutputStream;

/**
 * @author dingmx
 * @date 2019/1/17 23:42
 */
public class ITextTest {

    @Test
    public void testAddPic() throws Exception {
        Rectangle rectPageSize = new Rectangle(PageSize.A4);
        rectPageSize = rectPageSize.rotate();
        Document doc = new Document(PageSize.A4);
        RtfWriter2.getInstance(doc, new FileOutputStream(AmarUtils.getTmpPath() + "text.doc"));
        doc.open();
        Image img = Image.getInstance(ITextTest.class.getResource("/logo.png").getPath());
        img.scalePercent(70);
        doc.add(img);
        doc.add(img);
        doc.close();
    }
}
