package davenkin.opinions.persistence.dao.jdbc;

import davenkin.opinions.persistence.dao.jdbc.mapper.JdbcResultSetRowMapper;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlExecutor
{
    private Logger logger = Logger.getLogger(SqlExecutor.class);

    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    public void execute(String sql, Object[] parameters, Connection connection) throws SQLException
    {
        preparedStatement = connection.prepareStatement(sql);
        populateStatement(parameters);
        if (preparedStatement.execute())
        {
            resultSet = preparedStatement.getResultSet();
        }
    }

    public <T> List<T> execute(String sql, Object[] parameters, Connection connection, JdbcResultSetRowMapper<T> mapper) throws SQLException
    {
        execute(sql, parameters, connection);
        List<T> list = new ArrayList<T>();
        while (resultSet.next())
        {
            list.add(mapper.map(resultSet));
        }
        return list;
    }

    public void releaseResource()
    {
        try
        {
            if (resultSet != null)
            {
                resultSet.close();
            }
            if (preparedStatement != null)
            {
                preparedStatement.close();
            }

        } catch (Exception e)
        {
            logger.error("Couldn't closeResources database resources.");
        }

    }


    private void populateStatement(Object... objects) throws SQLException
    {
        if (objects == null)
            return;
        int index = 1;
        for (Object object : objects)
        {
            preparedStatement.setObject(index, object);
            index++;
        }
    }
}
