package davenkin.opinions.persistence.dao.jdbc;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlExecutor
{
    private Logger logger = Logger.getLogger(SqlExecutor.class);

    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    public void execute(Connection connection, String sql, Object[] parameters, ResultSetCallBack callBack) throws SQLException
    {
        try
        {
            preparedStatement = connection.prepareStatement(sql);
            populateStatement(parameters);
            if (preparedStatement.execute())
            {
                resultSet = preparedStatement.getResultSet();
                callBack.callBack(resultSet);
            }
        } finally
        {
            releaseResource();
        }
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
