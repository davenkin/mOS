package davenkin.opinions.persistence.dao;

import davenkin.opinions.domain.User;

public interface UserDao
{
    public User findUserById(Long userId);

}
