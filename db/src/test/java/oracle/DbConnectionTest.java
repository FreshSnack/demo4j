package oracle;

import org.apache.commons.io.FileUtils;
import org.junit.*;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.Assert.*;

/**
 * @Author: mxding
 * @Date: 2018-12-19 08:59
 */
public class DbConnectionTest {

    private static DbConnection dbConnection;

    @BeforeClass
    public static void setUp() throws Exception {
        DbConnection.SidUrl sidUrl = new DbConnection.SidUrl("localhost",1521, "orcl");
        dbConnection = new DbConnection(sidUrl, "scott", "tiger");
    }

    @Test@Ignore
    public void testConnect() {
        Connection connection = dbConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement("select 1 from dual");
            rs = statement.executeQuery();
            if(rs.next()) {
                System.out.println(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null) rs.close();
                if(statement != null) statement.close();
            } catch (Exception e) {
                // do nothing
            }
        }
    }


    @Test
    public void executeCreateUser() throws Exception {
        executeSQL("/createUser.sql");
    }

    @Test
    public void executeProcedure() throws Exception {
        executeSQL("/procedure.sql");
    }

    private void executeSQL(String sqlFilePath) throws Exception {
        File sqlFile = new File(FileUtilTest.class.getResource(sqlFilePath).getPath());
        String sql = FileUtils.readFileToString(sqlFile);
        Connection connection = dbConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            boolean result = statement.execute();
            System.out.println(result);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            statement.close();
        }
    }

    @AfterClass
    public static void tearDown() throws Exception {
        dbConnection.close();
    }
}