package davenkin.opinions.persistence.dao;

import davenkin.opinions.domain.User;
import davenkin.opinions.persistence.DataAccessException;

public interface UserDao
{
    public void addUser(String name, String email, String password) throws DataAccessException;

    public void deleteUser(long userId) throws DataAccessException;

    public void updateUser(long userId, String name, String email, String password) throws DataAccessException;

    public User findUserById(long userId) throws DataAccessException;

    public User findUserByName(String name) throws DataAccessException;

}
