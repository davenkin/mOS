package davenkin.opinions.persistence.hibernate;

import davenkin.opinions.domain.User;
import davenkin.opinions.persistence.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.sql.Timestamp;

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
    @Transactional
    public User getUserById(long userId) {
        return (User) sessionFactory.getCurrentSession().load(User.class, userId);
    }

    @Override
    @Transactional
    public User getUserByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User as user where user.name = :name");
        query.setString("name", name);
        return (User) query.uniqueResult();
    }

    @Override
    @Transactional
    public User getUserByEmail(String email) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User as user where user.email = :email");
        query.setString("email", email);
        return (User) query.uniqueResult();

    }

    @Override
    @Transactional
    public void updateUserName(long userId, String name) {
        getUserById(userId).updateName(name);
    }

    @Override
    @Transactional
    public void updateUserEmail(long userId, String email) {
        getUserById(userId).updateEmail(email);
    }

    @Override
    @Transactional
    public void updateUserPassword(long userId, String password) {
        getUserById(userId).updatePassword(DigestUtils.md5Hex(password));
    }

    @Override
    @Transactional
    public void updateUser(long userId, String name, String email, String password) {
        User userById = getUserById(userId);
        userById.updateName(name);
        userById.updateEmail(email);
        userById.updatePassword(DigestUtils.md5Hex(password));
    }

    @Override
    @Transactional
    public long addUser(User user) {
        user.setRegisterTime(new Timestamp(System.currentTimeMillis()));
        Serializable save = sessionFactory.getCurrentSession().save(user);
        return ((Long) save).longValue();
    }

    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
