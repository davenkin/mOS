package davenkin.opinions.persistence.dao.jdbc;

import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcTransactionManager
{
    private DataSource dataSource;
    private Logger logger = Logger.getLogger(JdbcTransactionManager.class);

    public JdbcTransactionManager(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    private Connection getConnection() throws SQLException
    {
        return SingleThreadDataSourceUtils.getConnection(dataSource);
    }

    public final void start() throws SQLException
    {
        getConnection().setAutoCommit(false);
    }

    public final void commit() throws SQLException
    {
        getConnection().commit();
    }

    public final void rollback() throws SQLException
    {
        getConnection().rollback();
        logger.info("Rolling back.");
    }

    public final void close() throws SQLException
    {
        Connection connection = getConnection();
        connection.setAutoCommit(true);
        connection.setReadOnly(false);
        connection.close();
        SingleThreadDataSourceUtils.removeCurrentConnection();
    }
}
