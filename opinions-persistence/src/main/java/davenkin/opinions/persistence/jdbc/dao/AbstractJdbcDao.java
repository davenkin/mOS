package davenkin.opinions.persistence.jdbc.dao;

import davenkin.opinions.persistence.jdbc.JdbcTemplate;
import org.apache.log4j.Logger;

import javax.sql.DataSource;

public class AbstractJdbcDao
{
    protected DataSource dataSource;
    protected JdbcTemplate jdbcTemplate;
    protected final Logger logger = Logger.getLogger(this.getClass());

    public AbstractJdbcDao(DataSource dataSource)
    {
        this.dataSource = dataSource;
        this.jdbcTemplate =  new JdbcTemplate(dataSource);
    }
}
