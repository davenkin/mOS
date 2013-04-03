package davenkin.opinions.persistence.hibernate;

import davenkin.opinions.domain.User;
import davenkin.opinions.persistence.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 3/20/13
 * Time: 10:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class HibernateUserService implements UserService {
    private SessionFactory sessionFactory;

    @Override
    public User getUserById(long userId) {
        return (User) sessionFactory.getCurrentSession().load(User.class, userId);
    }

    @Override
    @Transactional
    public void updateUserName(long userId, String name) {
        getUserById(userId).setName(name);
    }

    @Override
    @Transactional
    public void updateUserEmail(long userId, String email) {
        getUserById(userId).setEmail(email);
    }

    @Override
    @Transactional
    public void updateUserPassword(long userId, String password) {
        getUserById(userId).setPassword(DigestUtils.md5Hex(password));
    }

    @Override
    @Transactional
    public void updateUser(long userId, String name, String email, String password) {
        User userById = getUserById(userId);
        userById.setName(name);
        userById.setEmail(email);
        userById.setPassword(DigestUtils.md5Hex(password));
    }

    @Override
    @Transactional
    public long addNewUser(String name, String email, String password) {
        User user = new User(name, email, DigestUtils.md5Hex(password), new Timestamp(new Date().getTime()));
        return (Long) sessionFactory.getCurrentSession().save(user);
    }

    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
