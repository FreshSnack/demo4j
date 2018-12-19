package oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author: mxding
 * @Date: 2018-12-18 15:44
 */
public class DbConnection {

    Connection connection;

    /**
     * URL格式
     * SID: jdbc:oracle:thin:@<HOST>:<POST>:<SID>
     * SERVICE: jdbc:oracle:thin:@//<HOST>:<POST>/<SERVICE_NAME>
     * @param url
     * @param username
     * @param password
     */
    public DbConnection(String url, String username, String password) {
        try {
            // 加载驱动
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //获得数据库链接
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // sid 构造函数
    public DbConnection(SidUrl sidUrl, String username, String password) {
        this(sidUrl.get(), username, password);
    }

    public Connection getConnection() { return connection; }

    /**
     * 判断连接是否有效
     * @return
     */
    public boolean isValid() {
        if(connection == null) return false;
        try {
            if(connection.isClosed()) return false;
            return connection.isValid(5000);
        } catch (Exception e) {
            return false;
        }
    }

    // 关闭连接
    public void close() {
        try {
            if(this.connection != null) {
                this.connection.close();
            }
        } catch (SQLException e) {
            // do nothing
        }
    }

    public static class SidUrl {
        private String host;
        private int port;
        private String sid;

        public SidUrl(String host, int port, String sid) {
            this.host = host;
            this.port = port;
            this.sid = sid;
        }

        public String get() { return String.format("jdbc:oracle:thin:@%s:%s:%s", host, port, sid); }
    }

    public static class ServiceUrl {
        private String host;
        private int port;
        private String serviceName;

        public ServiceUrl(String host, int port, String serviceName) {
            this.host = host;
            this.port = port;
            this.serviceName = serviceName;
        }

        public String get() { return String.format("jdbc:oracle:thin:@//%s:%s/%s", host, port, serviceName); }
    }
}
