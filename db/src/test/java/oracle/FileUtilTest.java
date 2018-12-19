package oracle;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @Author: mxding
 * @Date: 2018-12-19 09:51
 */
public class FileUtilTest {

    @Test
    public void testReadFile() throws IOException {
        File file = new File(FileUtilTest.class.getResource("/createUser.sql").getPath());
        String s = FileUtils.readFileToString(file);
        System.out.println(s);
    }

    @Test
    public void testGetFile() {
        File file = FileUtils.getFile("createUser.sql");
        System.out.println(file.getAbsolutePath());
    }

    @Test
    public void testGetFile2() {
        File file = new File("createUser.sql");
        System.out.println(file.getAbsolutePath());
    }
}
