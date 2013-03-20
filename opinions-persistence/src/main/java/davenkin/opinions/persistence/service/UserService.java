package davenkin.opinions.persistence.service;

import davenkin.opinions.domain.User;

public interface UserService
{
    public User getUserById(long userId);

    public void updateUserName(long userId, String name);

    public void updateUserEmail(long userId, String email);

    public void updateUserPassword(long userId, String password);

    public void updateUser(long userId, String name, String email, String password);

    public long addNewUser(String name, String email, String password);

}
