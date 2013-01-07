package davenkin.opinions.persistence.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class SingleThreadConnectionFactory
{
    private static ThreadLocal<Connection> localConnection = new ThreadLocal<Connection>();

    public static Connection getConnection(DataSource dataSource) throws SQLException
    {
        Connection connection = localConnection.get();
        if (connection == null || connection.isClosed())
        {
            connection = dataSource.getConnection();
            localConnection.set(connection);
        }
        return connection;
    }

    public static void removeCurrentConnection()
    {
        localConnection.remove();
    }
}
