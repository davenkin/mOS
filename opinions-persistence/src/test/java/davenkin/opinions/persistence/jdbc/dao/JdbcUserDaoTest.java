package davenkin.opinions.persistence.jdbc.dao;

import davenkin.opinions.domain.User;
import davenkin.opinions.domain.DataAccessException;
import davenkin.opinions.persistence.DataSourceUtil;
import davenkin.opinions.persistence.dao.UserDao;
import davenkin.opinions.persistence.jdbc.JdbcTransactionManager;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import static junit.framework.Assert.*;

public class JdbcUserDaoTest
{

    private UserDao jdbcUserDao;
    private JdbcTransactionManager jdbcTransactionManager;

    @Before
    public void setUp() throws Exception
    {
        BasicDataSource dataSource = DataSourceUtil.createDataSource();
        jdbcUserDao = new JdbcUserDao(dataSource);
        jdbcTransactionManager = new JdbcTransactionManager(dataSource);
    }

    @Test
    public void findSingleUserById() throws SQLException, DataAccessException
    {
        User user = jdbcUserDao.findUserById(3L);
        assertEquals(user.getName(), "kate");
        assertEquals(user.getEmail(), "kate@gmail.com");
        assertTrue(user.getId() == 3L);
    }

    @Test
    public void returnNullWhenNoUserFound() throws DataAccessException
    {
        long nonExistUserId = 100L;
        User user = jdbcUserDao.findUserById(nonExistUserId);
        assertNull(user);
    }

    @Test
    public void addAndDeleteUser() throws NoSuchAlgorithmException, DataAccessException
    {
        String password = DigestUtils.md5Hex("password");
        String userName = "testUserName";
        String email = "testUserEmail@163.com";
        jdbcUserDao.addUser(userName, email, password);
        User user = jdbcUserDao.findUserByName(userName);
        assertEquals(userName, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        jdbcUserDao.deleteUser(user.getId());
        User userByName = jdbcUserDao.findUserByName(userName);
        assertNull(userByName);
    }

    @Test
    public void rollbackWhenExceptionWhileAddingUser() throws SQLException, DataAccessException
    {
        jdbcTransactionManager.start();
        String password = DigestUtils.md5Hex("password");
        String userName = "testUserName";
        String email = "testUserEmail@163.com";
        try
        {
            jdbcUserDao.addUser(userName, email, password);
            throw new DataAccessException();
        } catch (DataAccessException e)
        {
            jdbcTransactionManager.rollback();
        }
        jdbcTransactionManager.commit();
        jdbcTransactionManager.close();
        User userByName = jdbcUserDao.findUserByName(userName);
        assertNull(userByName);

    }

    @Test
    public void updateUser() throws DataAccessException
    {
        String password = DigestUtils.md5Hex("password");
        String userName = "testUserName";
        String newName = "newName";
        String email = "testUserEmail@163.com";
        String newEmail = "newEmail@163.com";
        jdbcUserDao.addUser(userName, email, password);
        long userId = jdbcUserDao.findUserByName(userName).getId();
        jdbcUserDao.updateUser(userId, newName, newEmail, password);
        User userById = jdbcUserDao.findUserById(userId);
        assertEquals(newName, userById.getName());
        assertEquals(newEmail, userById.getEmail());
        jdbcUserDao.deleteUser(userId);
    }

}
