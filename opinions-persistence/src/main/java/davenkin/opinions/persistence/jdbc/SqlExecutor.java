package davenkin.opinions.persistence.jdbc;

import davenkin.opinions.persistence.jdbc.callback.ResultSetCallBack;
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
            createPreparedStatement(connection, sql, parameters);

            if (preparedStatement.execute())
            {
                callBackOnResultSet(callBack);
            }
        } finally
        {
            releaseResource();
        }
    }

    private void createPreparedStatement(Connection connection, String sql, Object[] parameters) throws SQLException
    {
        preparedStatement = connection.prepareStatement(sql);
        populateStatement(parameters);
        logger.info("Executing SQL:" + extractSqlString());
    }

    private void callBackOnResultSet(ResultSetCallBack callBack) throws SQLException
    {
        resultSet = preparedStatement.getResultSet();
        callBack.callBack(resultSet);
    }

    private String extractSqlString()
    {
        String statementString = preparedStatement.toString();
        if (!statementString.contains(":"))
            return null;

        return statementString.substring(statementString.indexOf(":") + 1);
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
            logger.error("Couldn't close database resources.");
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
