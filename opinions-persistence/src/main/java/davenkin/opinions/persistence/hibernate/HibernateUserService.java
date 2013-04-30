package davenkin.opinions.persistence.hibernate;

import com.google.common.base.Predicate;
import davenkin.opinions.domain.User;
import davenkin.opinions.repository.UserRepository;
import davenkin.opinions.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.google.common.collect.FluentIterable.from;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 3/20/13
 * Time: 10:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class HibernateUserService implements UserService {
    private UserRepository userRepository;

    @Override
    @Transactional
    public User getUserById(long userId) {
        return userRepository.getUser(userId);
    }

    @Override
    @Transactional
    public User getUserByName(final String name) {
        List<User> allUsers = userRepository.getAllUsers();
        return from(allUsers).firstMatch(new Predicate<User>() {
            @Override
            public boolean apply(User input) {
                return name.equals(input.getName());
            }
        }).get();
    }

    @Override
    @Transactional
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    @Transactional
    public void updateUserName(long userId, String name) {
        User user = userRepository.getUser(userId);
        user.setName(name);
        userRepository.saveUser(user);
    }

    @Override
    @Transactional
    public void updateUserEmail(long userId, String email) {
        User user = userRepository.getUser(userId);
        user.setEmail(email);
        userRepository.saveUser(user);

    }

    @Override
    @Transactional
    public void updateUserPassword(long userId, String password) {
        User user = userRepository.getUser(userId);
        user.setPassword(DigestUtils.md5Hex(password));
        userRepository.saveUser(user);
    }

    @Override
    @Transactional
    public void updateUser(long userId, String name, String email, String password) {
        User user = userRepository.getUser(userId);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(DigestUtils.md5Hex(password));
        userRepository.saveUser(user);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userRepository.saveUser(user);
    }

    @Required
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
