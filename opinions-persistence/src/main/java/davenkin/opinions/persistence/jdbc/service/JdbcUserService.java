package davenkin.opinions.persistence.jdbc.service;

import davenkin.opinions.domain.DataAccessException;
import davenkin.opinions.domain.User;
import davenkin.opinions.persistence.dao.UserDao;
import davenkin.opinions.persistence.jdbc.dao.JdbcUserDao;
import davenkin.opinions.persistence.service.UserService;

import javax.sql.DataSource;

public class JdbcUserService implements UserService
{
    private UserDao userDao;

    public JdbcUserService(DataSource dataSource)
    {
        userDao = new JdbcUserDao(dataSource);
    }


    @Override
    public User getUserById(long userId)
    {
        try
        {
            return userDao.findUserById(userId);
        } catch (DataAccessException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUserName(long userId, String name)
    {
        try
        {
            User userById = userDao.findUserById(userId);
            userDao.updateUser(userId, name, userById.getEmail(), userById.getPassword());
        } catch (DataAccessException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUserEmail(long userId, String email)
    {
        try
        {
            User userById = userDao.findUserById(userId);
            userDao.updateUser(userId, userById.getName(), email, userById.getPassword());
        } catch (DataAccessException e)
        {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateUserPassword(long userId, String password)
    {
        try
        {
            User userById = userDao.findUserById(userId);
            userDao.updateUser(userId, userById.getName(), userById.getEmail(), password);
        } catch (DataAccessException e)
        {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateUser(long userId, String name, String email, String password)
    {
        try
        {
            userDao.updateUser(userId, name, email, password);
        } catch (DataAccessException e)
        {
            throw new RuntimeException(e);
        }

    }

    @Override
    public long addNewUser(String name, String email, String password)
    {
        try
        {
            userDao.addUser(name, email, password);
        } catch (DataAccessException e)
        {
            throw new RuntimeException(e);
        }
        return 0L;
    }
}
