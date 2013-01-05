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

    public void addUser(String name, String email, String password) throws DataAccessException
    {
        jdbcTemplate.update("INSERT INTO USER (NAME, EMAIL, PASSWORD) VALUES (?, ?, ?)", new Object[]{name, email, password});
    }

    public void deleteUser(Long userId) throws DataAccessException
    {
       jdbcTemplate.update("DELETE FROM USER WHERE ID = ?", new Object[]{userId});
    }

    public void updateUser(Long userId, String name, String email, String password) throws DataAccessException
    {
        jdbcTemplate.update("UPDATE USER SET NAME=?, EMAIL=?, PASSWORD=? WHERE ID = ?", new Object[]{name, email, password, userId});
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

    public User findUserByName(String name) throws DataAccessException
    {
        List<User> users = jdbcTemplate.queryForList("SELECT * FROM USER WHERE NAME = ?", new Object[]{name}, new UserRowMapper());
        if (users.size() > 0)
        {
            return users.get(0);
        }

        return null;
    }
}
