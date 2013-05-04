package davenkin.opinions.persistence.hibernate;

import davenkin.opinions.domain.Comment;
import davenkin.opinions.domain.Survey;
import davenkin.opinions.domain.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 5/1/13
 * Time: 3:09 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testHibernateApplicationContext.xml"})
@TestExecutionListeners({DirtiesContextTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
@Ignore
public class CommonTestFixture {
    @Autowired
    public SessionFactory sessionFactory;

    protected User getUserFromDb(long userId) {
        Query sqlQuery = sessionFactory.getCurrentSession().createQuery("from User user where user.id=:userId").setParameter("userId", userId);
        return (User) sqlQuery.uniqueResult();
    }

    protected Survey getSurveyFromDb(long surveyId) {
        Query sqlQuery = sessionFactory.getCurrentSession().createQuery("from Survey s where s.id=:surveyId").setParameter("surveyId", surveyId);
        return (Survey) sqlQuery.uniqueResult();
    }

    protected List<Survey> getAllSurveysFromDb() {
        return sessionFactory.getCurrentSession().createQuery("from Survey").list();
    }

    protected List<Comment> getCommentForSurveysFromDb(long surveyId) {
        return sessionFactory.getCurrentSession().createQuery("from Comment c where c.surveyId=:surveyId").setParameter("surveyId", surveyId).list();
    }


    protected List<Comment> getCommentsFromDb() {
        return sessionFactory.getCurrentSession().createQuery("from Comment").list();
    }


}
