package davenkin.opinions.persistence.dao.jdbc;

import davenkin.opinions.domain.User;
import davenkin.opinions.persistence.DataAccessException;
import davenkin.opinions.persistence.DataSourceUtil;
import davenkin.opinions.persistence.dao.UserDao;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.Assert.*;

public class JdbcUserDaoTest
{
    @Test
    public void findSingleUserById() throws SQLException, DataAccessException
    {
        UserDao jdbcUserDao = new JdbcUserDao(DataSourceUtil.createDataSource());
        User user = jdbcUserDao.findUserById(3L);
        assertEquals(user.getName(), "kate");
        assertEquals(user.getEmail(), "kate@gmail.com");
        assertTrue(user.getUserId() == 3L);
    }

    @Test
    public void returnNullWhenNoUserFound() throws DataAccessException
    {
        UserDao jdbcUserDao = new JdbcUserDao(DataSourceUtil.createDataSource());
        long nonExistUserId = 100L;
        User user = jdbcUserDao.findUserById(nonExistUserId);
        assertNull(user);
    }

    



}
