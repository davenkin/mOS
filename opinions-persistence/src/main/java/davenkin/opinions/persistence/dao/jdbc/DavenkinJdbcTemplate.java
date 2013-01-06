package davenkin.opinions.persistence.dao.jdbc;

import davenkin.opinions.persistence.DataAccessException;
import davenkin.opinions.persistence.dao.jdbc.mapper.JdbcResultSetExtractor;
import davenkin.opinions.persistence.dao.jdbc.mapper.JdbcResultSetRowMapper;
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

    public void update(String sql, Object[] objects) throws DataAccessException
    {
        Connection connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            connection = getConnection();
            preparedStatement = createPreparedStatement(sql, objects, connection);
            preparedStatement.executeUpdate();

        } catch (Exception e)
        {
            throw new DataAccessException(e);
        } finally
        {
            closeResources(resultSet, preparedStatement);
        }
    }

    public <T> List<T> queryForList(String sql, Object[] objects, JdbcResultSetRowMapper<T> mapper) throws DataAccessException
    {
        Connection connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            connection = getConnection();
            preparedStatement = createPreparedStatement(sql, objects, connection);
            resultSet = preparedStatement.executeQuery();
            List<T> list = new ArrayList<T>();
            while (resultSet.next())
            {
                list.add(mapper.map(resultSet));
            }
            return list;
        } catch (Exception e)
        {
            throw new DataAccessException(e);
        } finally
        {
            closeResources(resultSet, preparedStatement);
        }
    }

    public <T> List<T> queryForList(String sql, Object[] objects, Class<T> type) throws DataAccessException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            connection = getConnection();
            preparedStatement = createPreparedStatement(sql, objects, connection);
            resultSet = preparedStatement.executeQuery();
            connection.commit();
            List<T> list = new ArrayList<T>();
            while (resultSet.next())
            {
                list.add(type.cast(resultSet.getObject(1)));
            }
            return list;
        } catch (Exception e)
        {
            rollbackAndThrowException(connection, e);
        } finally
        {
            closeResources(resultSet, preparedStatement);
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
            connection = getConnection();
            preparedStatement = createPreparedStatement(sql, objects, connection);
            resultSet = preparedStatement.executeQuery();
            connection.commit();
            while (resultSet.next())
            {
                return type.cast(resultSet.getObject(1));
            }
        } catch (Exception e)
        {
            rollbackAndThrowException(connection, e);
        } finally
        {
            closeResources(resultSet, preparedStatement);
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
            connection = getConnection();
            preparedStatement = createPreparedStatement(sql, objects, connection);
            resultSet = preparedStatement.executeQuery();
            connection.commit();
            return extractor.extract(resultSet);
        } catch (Exception e)
        {
            rollbackAndThrowException(connection, e);
        } finally
        {
            closeResources(resultSet, preparedStatement);
        }
        return null;
    }


    private PreparedStatement createPreparedStatement(String sql, Object[] objects, Connection connection) throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        populatePreparedStatement(preparedStatement, objects);
        logger.info(extractSqlString(preparedStatement));
        return preparedStatement;
    }

    private String extractSqlString(PreparedStatement preparedStatement)
    {
        String statementString = preparedStatement.toString();
        if (!statementString.contains(":"))
            return null;

        return statementString.substring(statementString.indexOf(":") + 1);
    }

    private Connection getConnection() throws SQLException
    {
        long before = System.currentTimeMillis();
        Connection connection = SingleThreadDataSourceUtils.getConnection(dataSource);
        long after = System.currentTimeMillis();
        long interval = after - before;
        logger.info("Connection[" + connection.hashCode() + "] created in " + interval + " milliseconds");
        logger.info("User connection[" + connection.hashCode() + "]");
        return connection;
    }

    private void rollbackAndThrowException(Connection connection, Exception e) throws DataAccessException
    {
        throw new DataAccessException(e);
    }


    private void populatePreparedStatement(PreparedStatement preparedStatement, Object... objects) throws SQLException
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

    private void closeResources(ResultSet resultSet, PreparedStatement preparedStatement)
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

    public void setDataSource(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
}