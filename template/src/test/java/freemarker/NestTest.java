package freemarker;

import freemarker.template.Template;
import org.junit.Test;

import java.io.PrintWriter;
import java.util.*;

/**
 * @Author: mxding
 * @Date: 2018-12-21 14:22
 */
public class NestTest {

    @Test
    public void testNest() throws Exception {
        List fields = Arrays.asList(
                new Field("id", 0.25),
                new Field("code", 1.0)
        );
        Template template = FMUtil.getTemplate("nest.ftl");
        Map data = new HashMap();
        data.put("fields", fields);
        template.process(data, new PrintWriter(System.out));
    }

    @Test
    public void testWidth() throws Exception {
        List fields = Arrays.asList(
                new Field("id", 0.25),
                new Field("code", 1.0),
                new Field("age", 0.25),
                new Field("address", 0.25),
                new Field("phone", 0.5)
        );
        Template template = FMUtil.getTemplate("width.ftl");
        Map data = new HashMap();
        data.put("fields", fields);
        template.process(data, new PrintWriter(System.out));
    }


    public static class Field {
        String name;
        double width;

        public Field(String name, double width) {
            this.name = name;
            this.width = width;
        }

        public String getName() {
            return name;
        }

        public double getWidth() {
            return width;
        }
    }
}
