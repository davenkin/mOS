package davenkin.opinions.persistence.hibernate;

import davenkin.opinions.domain.User;
import davenkin.opinions.persistence.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

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
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateUserName(long userId, String name) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateUserEmail(long userId, String email) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateUserPassword(long userId, String password) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateUser(long userId, String name, String email, String password) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    @Transactional
    public void addNewUser(String name, String email, String password) {
        Session session = sessionFactory.getCurrentSession();
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        session.save(user);
    }

    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
