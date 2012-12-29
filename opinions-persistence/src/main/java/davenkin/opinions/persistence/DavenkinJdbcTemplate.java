package davenkin.opinions.persistence;

import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DavenkinJdbcTemplate
{
    private DataSource dataSource;
    private Logger logger = Logger.getLogger(DavenkinJdbcTemplate.class);

    public DavenkinJdbcTemplate(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    public <T> T queryForObject(String sql, Object[] objects, JdbcResultSetExtractor<T> extractor) throws DataLoadingException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            logger.info(preparedStatement);
            populatePreparedStatement(preparedStatement, objects);
            resultSet = preparedStatement.executeQuery();
            connection.commit();
            return extractor.extract(resultSet);
        } catch (Exception e)
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
            throw new DataLoadingException();
        } finally
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
                if (connection != null)
                {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (Exception e)
            {
                logger.error("Couldn't close database resources.");
            }

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
