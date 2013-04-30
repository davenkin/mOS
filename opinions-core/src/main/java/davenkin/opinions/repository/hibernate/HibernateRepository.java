package davenkin.opinions.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 4/30/13
 * Time: 2:42 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class HibernateRepository {

    private SessionFactory sessionFactory;

    @Required
    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
