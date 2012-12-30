package davenkin.opinions.persistence.dao;

import davenkin.opinions.domain.User;
import davenkin.opinions.persistence.DataAccessException;
import davenkin.opinions.persistence.DavenkinJdbcTemplate;
import davenkin.opinions.persistence.mapper.UserRowMapper;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.util.List;

public class JdbcUserDao implements UserDao
{
    private DataSource dataSource;
    private DavenkinJdbcTemplate jdbcTemplate;
    private final Logger logger = Logger.getLogger(this.getClass());

    public JdbcUserDao(DataSource dataSource)
    {
        this.dataSource = dataSource;
        this.jdbcTemplate =  new DavenkinJdbcTemplate(dataSource);
    }

    public User findUserById(Long userId)
    {
        try
        {
            List<User> users = jdbcTemplate.queryForList("SELECT * FROM USER WHERE ID = ?", new Object[]{userId}, new UserRowMapper());
            if (users.size() > 0)
            {
                return users.get(0);
            }
        } catch (DataAccessException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;

    }
}
