package oracle;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Test;

import java.io.PrintWriter;

/**
 * @Author: mxding
 * @Date: 2018-12-19 10:54
 */
public class ScriptRunnerTest {

    @Test
    public void runSqlFile() throws Exception {
        DbConnection.SidUrl sidUrl = new DbConnection.SidUrl("localhost",1521, "orcl");
        DbConnection dbConnection = new DbConnection(sidUrl, "scott", "tiger");
        // 创建ScriptRunner，用于执行SQL脚本
        ScriptRunner runner = new ScriptRunner(dbConnection.connection);
        runner.setErrorLogWriter(new PrintWriter(System.out));
        runner.setLogWriter(new PrintWriter(System.out));
        // 执行SQL脚本
        runner.runScript(Resources.getResourceAsReader("createUser.sql"));
        dbConnection.close();
    }
}
