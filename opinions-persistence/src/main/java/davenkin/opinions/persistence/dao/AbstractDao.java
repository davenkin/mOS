package davenkin.opinions.persistence.dao;

import davenkin.opinions.persistence.DavenkinJdbcTemplate;
import org.apache.log4j.Logger;

import javax.sql.DataSource;

public class AbstractDao
{
    protected DataSource dataSource;
    protected DavenkinJdbcTemplate jdbcTemplate;
    protected final Logger logger = Logger.getLogger(this.getClass());

    public AbstractDao(DataSource dataSource)
    {
        this.dataSource = dataSource;
        this.jdbcTemplate =  new DavenkinJdbcTemplate(dataSource);
    }
}
