package freemarker;

import freemarker.template.Template;
import org.junit.Test;

import java.io.PrintWriter;

/**
 * @Author: mxding
 * @Date: 2019-01-04 09:40
 */
public class OverrideTest {

    @Test
    public void testPrint() throws Exception {
        Template template = FMUtil.getTemplate("override.ftl");
        template.process(null, new PrintWriter(System.out));
    }
}
