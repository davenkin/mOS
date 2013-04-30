package davenkin.opinions.persistence.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 3/22/13
 * Time: 10:54 PM
 * To change this template  use File | Settings | File Templates.
 */
public class StandaloneHibernateTest {

    @Test
    public void testStandaloneHibernate(){
        Configuration configure = new Configuration().configure("config/hibernate.cfg.xml");

        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configure.getProperties()).buildServiceRegistry();
        SessionFactory factory = configure.buildSessionFactory(serviceRegistry);
        Session session = factory.getCurrentSession();

        Transaction transaction = session.beginTransaction();
        List list = session.createSQLQuery("SELECT * FROM USER").list();
        transaction.commit();
        System.out.println(list.size());
    }
}
