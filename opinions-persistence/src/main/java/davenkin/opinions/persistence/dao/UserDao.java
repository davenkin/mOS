package davenkin.opinions.persistence.dao;

import davenkin.opinions.domain.User;
import davenkin.opinions.persistence.DataAccessException;

public interface UserDao
{
    public void addUser(String name, String email, String password);
    public void deleteUser(int userId);
    public void updateUser(int userId, String name, String email, String password);
    public User findUserById(Long userId) throws DataAccessException;

}
