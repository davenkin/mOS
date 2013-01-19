package davenkin.opinions.persistence.jdbc.service;

import davenkin.opinions.persistence.DataSourceUtil;
import davenkin.opinions.persistence.jdbc.JdbcTransactionManager;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransactionEnabledProxyTest
{

    @Test
    public void shouldProxy()
    {
        ProxyTest proxyTest = new ProxyTest();
        JdbcTransactionManager transactionManager = new JdbcTransactionManager(DataSourceUtil.createDataSource());
        ProxyTestInterface proxy = (ProxyTestInterface) new TransactionEnabledProxy(transactionManager).proxyFor(proxyTest);
        String test = proxy.test();
        assertEquals("testProxy", test);
    }

}

interface ProxyTestInterface
{
    public String test();
}

class ProxyTest implements ProxyTestInterface
{
    public String test()
    {
        return "testProxy";
    }
}
