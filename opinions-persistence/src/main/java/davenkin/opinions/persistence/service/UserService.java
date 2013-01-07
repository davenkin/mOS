package davenkin.opinions.persistence.service;

import davenkin.opinions.domain.User;

import java.util.List;

public interface UserService
{
    public List<User> getAllUsers();

    public User getUserById(long userId);

    public void updateUserName(long userId, String name);

    public void updateUserEmail(long userId, String email);

    public void updateUserPassword(long userId, String password);

    public void updateUser(long userId, String name, String email, String password);

    public void addNewUser(String name, String email, String password);

}
