package davenkin.opinions.repository;

import davenkin.opinions.domain.User;
import org.hibernate.Query;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 4/30/13
 * Time: 2:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class HibernateUserRepository extends HibernateRepository implements UserRepository {
    @Override
    public User getUser(long id) {
        return (User) getCurrentSession().load(User.class, id);
    }

    @Override
    public long addUser(User user) {
        return (Long) getCurrentSession().save(user);
    }

    @Override
    public void updateUser(User user) {
        getCurrentSession().update(user);
    }

    @Override
    public List<User> getAllUsers() {
        return getCurrentSession().createQuery("from User").list();
    }

    @Override
    public User getUserByEmail(String email) {
        Query query = getCurrentSession().createQuery("from User as user where user.email = :email");
        query.setString("email", email);
        return (User) query.uniqueResult();
    }

}
