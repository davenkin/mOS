package davenkin.opinions.persistence.jdbc.service;

import davenkin.opinions.persistence.jdbc.JdbcTransactionManager;

import javax.sql.DataSource;

public abstract class JdbcTransactionTemplate
{
    private JdbcTransactionManager transactionManager;

    protected JdbcTransactionTemplate(DataSource dataSource)
    {
        transactionManager = new JdbcTransactionManager(dataSource);
    }

    public void doJobInTransaction()
    {
        try
        {
            transactionManager.start();
            doJob();
            transactionManager.commit();
        } catch (Exception e)
        {
            transactionManager.rollback();
        } finally
        {
            transactionManager.close();
        }
    }

    protected abstract void doJob() throws Exception;
}
