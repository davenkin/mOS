package davenkin.opinions.persistence.jdbc;

import davenkin.opinions.domain.DataAccessException;
import davenkin.opinions.persistence.jdbc.callback.ResultSetCallBack;
import davenkin.opinions.persistence.jdbc.callback.RowMapperCallBack;
import davenkin.opinions.persistence.jdbc.callback.TypeCallBack;
import davenkin.opinions.persistence.jdbc.mapper.JdbcResultSetRowMapper;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
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


    public <T> List<T> queryForList(String sql, Object[] objects, JdbcResultSetRowMapper<T> mapper) throws DataAccessException
    {
        ArrayList<T> list = new ArrayList<T>();
        doExecute(sql, objects, new RowMapperCallBack(list, mapper));
        return list;
    }


    public <T> List<T> queryForList(String sql, Object[] objects, final Class<T> type) throws DataAccessException
    {
        ArrayList<T> list = new ArrayList<T>();
        doExecute(sql, objects, new TypeCallBack(list, type));
        return list;
    }


    public <T> T queryForObject(String sql, Object[] objects, final Class<T> type) throws DataAccessException
    {
        List<T> list = queryForList(sql, objects, type);
        if (list.size() > 0)
            return list.get(0);
        return null;
    }


    private Connection getConnection() throws SQLException
    {
        Connection connection = SingleThreadConnectionFactory.getConnection(dataSource);
        logger.info("Use connection[" + connection.hashCode() + "]");
        return connection;
    }


    private void doExecute(String sql, Object[] objects, ResultSetCallBack callBack) throws DataAccessException
    {
        try
        {
            new SqlExecutor().execute(getConnection(), sql, objects, callBack);
        } catch (Exception e)
        {
            throw new DataAccessException(e);
        }
    }

}
