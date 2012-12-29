package davenkin.opinions.persistence;

import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DavenkinJdbcTemplate
{
    private DataSource dataSource;
    private Logger logger = Logger.getLogger(DavenkinJdbcTemplate.class);

    public DavenkinJdbcTemplate(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    public <T> List<T> queryForList(String sql, Object[] objects, JdbcResultSetRowMapper<T> mapper) throws DataAccessException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            populatePreparedStatement(preparedStatement, objects);
            logger.info(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            connection.commit();
            List<T> list = new ArrayList<T>();
            while (resultSet.next())
            {
                list.add(mapper.map(resultSet));
            }
            return list;
        } catch (Exception e)
        {
            rollbackAndThrowException(connection);
        } finally
        {
            try
            {
                closeResources(resultSet, preparedStatement, connection);
            } catch (Exception e)
            {
                logger.error("Couldn't close database resources.");
            }

        }
        return null;

    }

    public <T> T queryForObject(String sql, Object[] objects, Class<T> type) throws DataAccessException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            populatePreparedStatement(preparedStatement, objects);
            logger.info(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            connection.commit();
            while (resultSet.next())
            {
                return type.cast(resultSet.getObject(1));
            }
        } catch (Exception e)
        {
            rollbackAndThrowException(connection);
        } finally
        {
            try
            {
                closeResources(resultSet, preparedStatement, connection);
            } catch (Exception e)
            {
                logger.error("Couldn't close database resources.");
            }

        }
        return null;
    }


    public <T> T queryForObject(String sql, Object[] objects, JdbcResultSetExtractor<T> extractor) throws DataAccessException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            populatePreparedStatement(preparedStatement, objects);
            logger.info(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            connection.commit();
            return extractor.extract(resultSet);
        } catch (Exception e)
        {
            rollbackAndThrowException(connection);
        } finally
        {
            try
            {
                closeResources(resultSet, preparedStatement, connection);
            } catch (Exception e)
            {
                logger.error("Couldn't close database resources.");
            }

        }
        return null;
    }


    private void rollbackAndThrowException(Connection connection) throws DataAccessException
    {
        logger.error("Couldn't execute query, trying to rollback.");
        if (connection != null)
        {
            try
            {
                connection.rollback();
            } catch (SQLException ee)
            {
                logger.error("Couldn't rollback.");
            }
        }
        throw new DataAccessException();
    }

    private void closeResources(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) throws SQLException
    {
        if (resultSet != null)
        {
            resultSet.close();
        }
        if (preparedStatement != null)
        {
            preparedStatement.close();
        }
        if (connection != null)
        {
            connection.setAutoCommit(true);
            connection.close();
        }
    }

    public void populatePreparedStatement(PreparedStatement preparedStatement, Object... objects) throws SQLException
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

    public void setDataSource(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
}
