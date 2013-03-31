package davenkin.opinions.persistence.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
* Created with IntelliJ IDEA.
* User: davenkin
* Date: 3/31/13
* Time: 6:42 PM
* To change this template use File | Settings | File Templates.
*/
public abstract class CurrentSessionTemplate {
    public void doInSessionFactory(SessionFactory sessionFactory)
   {
       doInCurrentSession(sessionFactory.getCurrentSession());
   }
  public abstract void doInCurrentSession(Session session);
}
