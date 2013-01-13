package davenkin.opinions.persistence.service.jdbc;


import davenkin.opinions.persistence.jdbc.JdbcTransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TransactionEnabledProxy
{
    private JdbcTransactionManager transactionManager;

    public TransactionEnabledProxy(JdbcTransactionManager transactionManager)
    {

        this.transactionManager = transactionManager;
    }

    public Object proxyFor(Object object)
    {
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), new TransactionInvocationHandler(object, transactionManager));
    }
}

class TransactionInvocationHandler implements InvocationHandler
{
    private Object proxy;
    private JdbcTransactionManager transactionManager;

    TransactionInvocationHandler(Object object, JdbcTransactionManager transactionManager)
    {
        this.proxy = object;
        this.transactionManager = transactionManager;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable
    {
        transactionManager.start();
        Object result = null;
        try
        {
            result = method.invoke(proxy, objects);
            transactionManager.commit();
        } catch (Exception e)
        {
            transactionManager.rollback();
        } finally
        {
            transactionManager.close();
        }
        return result;
    }
}
