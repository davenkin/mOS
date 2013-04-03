package davenkin.opinions.persistence.hibernate;

import davenkin.opinions.domain.Category;
import davenkin.opinions.domain.Comment;
import davenkin.opinions.domain.Survey;
import davenkin.opinions.domain.User;
import davenkin.opinions.persistence.service.CommentService;
import davenkin.opinions.persistence.service.SurveyService;
import davenkin.opinions.persistence.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 4/1/13
 * Time: 1:34 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testHibernateApplicationContext.xml"})
@TestExecutionListeners({DirtiesContextTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,TransactionalTestExecutionListener.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TransactionConfiguration(transactionManager = "transactionManager")
@Transactional
public class HibernateCommentServiceTest {

    @Autowired
    public UserService userService;

    @Autowired
    public SurveyService surveyService;

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    public CommentService commentService;

    @Test
    public void addCommentToSurvey()
    {
        long surveyId = createUserAndSurvey();
        commentService.addCommentToSurvey("this is a comment",surveyId,1);
        commentService.getCommentsFromUser(1);
        assertEquals(1,getDbRecordCount("SURVEY_COMMENT"));
    }

   @Test
    public void getCommentsForSurvey()
    {
        long surveyId = createUserAndSurvey();
        commentService.addCommentToSurvey("this is a comment",surveyId,1);
        commentService.addCommentToSurvey("this is a comment 2", surveyId, 1);
        List<Comment> commentsForSurvey = commentService.getCommentsForSurvey(surveyId);
        assertEquals(2,commentsForSurvey.size());
    }

    @Test
    public void getCommentsFromUser()
    {
        long surveyId = createUserAndSurvey();
        long userId = addNewUser();
        commentService.addCommentToSurvey("this is a comment",surveyId,1);
        commentService.addCommentToSurvey("this is a comment 2", surveyId, userId);
        List<Comment> commentsFromUser = commentService.getCommentsFromUser(userId);
        assertEquals(1, commentsFromUser.size());
    }

    @Test
    public void removeComment()
    {
        long surveyId = createUserAndSurvey();
        long userId = addNewUser();
        commentService.addCommentToSurvey("this is a comment",surveyId,1);
        commentService.addCommentToSurvey("this is a comment 2", surveyId, userId);
        List<Comment> commentsFromUser = commentService.getCommentsFromUser(userId);
        assertEquals(1, commentsFromUser.size());
        long id = commentsFromUser.get(0).getId();
        commentService.removeCommentFromSurvey(id);
        commentService.getCommentsFromUser(userId);
        assertEquals(1,getDbRecordCount("SURVEY_COMMENT"));
    }


    private long createUserAndSurvey() {
        long userId = addNewUser();
        User user = userService.getUserById(userId);
        ArrayList<String> optionNames = new ArrayList<String>();
        optionNames.add("Yes");
        optionNames.add("No");
        String content = "Do you like programming?";
        Survey survey= user.createSurvey(content, false, Category.SCIENCE, optionNames);
        return surveyService.addSurvey(survey);
    }
    private long addNewUser() {
        return userService.addNewUser("davenkin", "davenkin@163.com", "123456");
    }

    private int getDbRecordCount(final String tableName) {
        Session currentSession = sessionFactory.getCurrentSession();
        BigInteger num = (BigInteger) currentSession.createSQLQuery("SELECT COUNT(*) FROM " + tableName).uniqueResult();
        return num.intValue();
    }

}
