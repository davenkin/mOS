package davenkin.opinions.repository;

import davenkin.opinions.domain.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 4/30/13
 * Time: 1:17 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UserRepository {
    public User getUser(long id);

    public long addUser(User user);

    public void updateUser(User user);

    public List<User> getAllUsers();

    public User getUserByEmail(String email);
}
