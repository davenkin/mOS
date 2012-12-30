package davenkin.opinions.persistence.dao;

import davenkin.opinions.domain.User;
import davenkin.opinions.persistence.DataSourceUtil;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

public class JdbcUserDaoTest
{
    @Test
    public void findSingleUserById() throws Exception
    {
        UserDao jdbcUserDao = new JdbcUserDao(DataSourceUtil.createDataSource());
        User user = jdbcUserDao.findUserById(3L);
        assertEquals(user.getName(), "kate");
        assertEquals(user.getEmail(), "kate@gmail.com");
        assertTrue(user.getUserId() == 3L);
    }

    @Test
    public void returnNullWhenNoUserFound()
    {
        UserDao jdbcUserDao = new JdbcUserDao(DataSourceUtil.createDataSource());
        long nonExistUserId = 100L;
        User user = jdbcUserDao.findUserById(nonExistUserId);
        assertNull(user);

    }
}
