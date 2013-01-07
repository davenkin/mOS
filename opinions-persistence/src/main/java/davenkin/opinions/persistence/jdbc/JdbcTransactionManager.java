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

    public final void rollback() throws SQLException
    {
        Connection connection = getConnection();
        connection.rollback();
        logger.info("Rolled back transaction on connection[" + connection.hashCode() + "]");
    }

    public final void close() throws SQLException
    {
        Connection connection = getConnection();
        connection.setAutoCommit(true);
        connection.setReadOnly(false);
        logger.info("Try to close and remove connection[" + connection.hashCode() + "] from current thread[" + Thread.currentThread().getId() + "]");
        connection.close();
        SingleThreadConnectionFactory.removeCurrentConnection();
    }
}
