package davenkin.opinions.persistence.dao;

import davenkin.opinions.domain.User;
import davenkin.opinions.persistence.DataAccessException;
import davenkin.opinions.persistence.mapper.UserRowMapper;

import javax.sql.DataSource;
import java.util.List;

public class JdbcUserDao extends AbstractDao implements UserDao
{

    public JdbcUserDao(DataSource dataSource)
    {
        super(dataSource);
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
