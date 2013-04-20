package davenkin.opinions.persistence.service;

import davenkin.opinions.domain.User;

public interface UserService {
    public long addUser(User user);

    public User getUserById(long userId);

    public User getUserByName(String name);

    public User getUserByEmail(String email);

    public void updateUserName(long userId, String name);

    public void updateUserEmail(long userId, String email);

    public void updateUserPassword(long userId, String password);

    public void updateUser(long userId, String name, String email, String password);

}
