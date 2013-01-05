package davenkin.opinions.persistence.dao.jdbc;

import davenkin.opinions.domain.User;
import davenkin.opinions.persistence.DataAccessException;
import davenkin.opinions.persistence.dao.UserDao;
import davenkin.opinions.persistence.dao.jdbc.mapper.UserRowMapper;

import javax.sql.DataSource;
import java.util.List;

public class JdbcUserDao extends AbstractJdbcDao implements UserDao
{

    public JdbcUserDao(DataSource dataSource)
    {
        super(dataSource);
    }

    public void addUser(String name, String email, String password)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void deleteUser(int userId)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void updateUser(int userId, String name, String email, String password)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public User findUserById(Long userId) throws DataAccessException
    {
        List<User> users = jdbcTemplate.queryForList("SELECT * FROM USER WHERE ID = ?", new Object[]{userId}, new UserRowMapper());
        if (users.size() > 0)
        {
            return users.get(0);
        }

        return null;
    }
}
