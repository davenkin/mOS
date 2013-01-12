package davenkin.opinions.persistence.jdbc;

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
        return SingleThreadConnectionFactory.getConnection(dataSource);
    }

    public final void start() throws SQLException
    {
        Connection connection = getConnection();
        connection.setAutoCommit(false);
        logger.info("Started transaction on connection[" + connection.hashCode() + "]");
    }

    public final void commit() throws SQLException
    {
        Connection connection = getConnection();
        connection.commit();
        logger.info("Committed transaction on connection[" + connection.hashCode() + "]");
    }

    public final void rollback()
    {
        Connection connection = null;
        try
        {
            connection = getConnection();
            connection.rollback();
            logger.info("Rolled back transaction on connection[" + connection.hashCode() + "]");

        } catch (SQLException e)
        {
            throw new RuntimeException("Couldn't rollback on connection[" + connection + "].", e);
        }
    }

    public final void close()
    {
        Connection connection = null;
        int connectionId;
        try
        {
            connection = getConnection();
            connectionId = connection.hashCode();
            connection.setAutoCommit(true);
            connection.setReadOnly(false);
            connection.close();
            SingleThreadConnectionFactory.removeCurrentConnection();
        } catch (SQLException e)
        {
            throw new RuntimeException("Couldn't close connection[" + connection + "].", e);
        }
        logger.info("Close and remove connection[" + connectionId + "] from current thread[" + Thread.currentThread().getId() + "]");
    }
}
