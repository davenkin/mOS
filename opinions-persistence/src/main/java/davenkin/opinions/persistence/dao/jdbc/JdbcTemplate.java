package davenkin.opinions.persistence.dao.jdbc;

import davenkin.opinions.persistence.DataAccessException;
import davenkin.opinions.persistence.dao.jdbc.mapper.JdbcResultSetRowMapper;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate
{
    private DataSource dataSource;
    private Logger logger = Logger.getLogger(JdbcTemplate.class);

    public JdbcTemplate(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }


    public void update(String sql, Object[] objects) throws DataAccessException
    {
        doExecute(sql, objects, null);
    }


    public <T> List<T> queryForList(String sql, Object[] objects, final JdbcResultSetRowMapper<T> mapper) throws DataAccessException
    {
        final ArrayList<T> list = new ArrayList<T>();
        doExecute(sql, objects, new ResultSetCallBack()
        {
            @Override
            public void callBack(ResultSet resultSet) throws SQLException
            {
                while (resultSet.next())
                {
                    list.add(mapper.map(resultSet));
                }
            }
        });
        return list;
    }


    public <T> List<T> queryForList(String sql, Object[] objects, final Class<T> type) throws DataAccessException
    {
        ArrayList<T> list = new ArrayList<T>();

        doExecute(sql, objects, new ResultSetCallBack()
        {
            @Override
            public void callBack(ResultSet resultSet) throws SQLException
            {
                List<T> list = new ArrayList<T>();
                while (resultSet.next())
                {
                    list.add(type.cast(resultSet.getObject(1)));
                }
            }
        });

        return list;

    }


    public <T> T queryForObject(String sql, Object[] objects, final Class<T> type) throws DataAccessException
    {
        final Object[] object = new Object[1];
        doExecute(sql, objects, new ResultSetCallBack()
        {

            @Override
            public void callBack(ResultSet resultSet) throws SQLException
            {
                while (resultSet.next())
                {
                    object[0] = type.cast(resultSet.getObject(1));
                }
            }
        });
        return (T) object[0];
    }


    private Connection getConnection() throws SQLException
    {
        long before = System.currentTimeMillis();
        Connection connection = SingleThreadDataSourceUtils.getConnection(dataSource);
        long after = System.currentTimeMillis();
        long interval = after - before;
        logger.info("Connection[" + connection.hashCode() + "] obtained in " + interval + " milliseconds");
        logger.info("Use connection[" + connection.hashCode() + "]");
        return connection;
    }


    private void doExecute(String sql, Object[] objects, ResultSetCallBack callBack) throws DataAccessException
    {
        SqlExecutor executor = new SqlExecutor();
        try
        {
            executor.execute(getConnection(), sql, objects, callBack);
        } catch (Exception e)
        {
            throw new DataAccessException(e);
        }
    }

}
