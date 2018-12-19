package oracle;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * @Author: mxding
 * @Date: 2018-12-19 09:17
 */
@FixMethodOrder(MethodSorters.JVM)
public class UrlTest {

    @Test
    public void testGetSidUrl() {
        System.out.println(Thread.currentThread().getName());
        DbConnection.SidUrl sidUrl = new DbConnection.SidUrl("localhost", 1521, "orcl");
        System.out.println(sidUrl.get());
    }

    @Test
    public void testGetServiceUrl() {
        System.out.println(Thread.currentThread().getName());
        DbConnection.ServiceUrl serviceUrl = new DbConnection.ServiceUrl("localhost", 1521, "orcl");
        System.out.println(serviceUrl.get());
    }
}
