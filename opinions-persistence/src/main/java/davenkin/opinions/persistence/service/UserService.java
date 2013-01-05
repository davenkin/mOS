package davenkin.opinions.persistence.service;

import davenkin.opinions.domain.User;

import java.util.List;

public interface UserService
{
    public List<User> getAllUsers();

    public User getUserById(int userId);

    public void updateUserName(int userId, String name);

    public void updateUserEmail(int userId, String email);

    public void updateUserPassword(int userId, String password);

    public void updateUser(int userId, String name, String email, String password);

    public void addNewUser(String name, String email, String password);

}
