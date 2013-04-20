package davenkin.opinions.persistence.hibernate;

import com.google.common.collect.Lists;
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

import static com.google.common.collect.Sets.newHashSet;
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
        DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class HibernateCommentServiceTest {

    @Autowired
    public UserService userService;

    @Autowired
    public SurveyService surveyService;

    @Autowired
    public CommentService commentService;

    @Test
    public void addCommentToSurvey() {

        User user = new User("davenkin", "davenkin@163.com", "password");
        userService.addUser(user);

        List<String> optionNames = createOptionNames("Yes", "No");
        String content = "Do you like programming?";

        Survey survey = new Survey(content, false, Category.CULTURE, optionNames, newHashSet("COMMON_TAG", "TAG1"));
        long surveyId = surveyService.addSurvey(user, survey);


        commentService.addCommentToSurvey("this is a comment", user, surveyId);
        List<Comment> commentsFromUser = commentService.getCommentsFromUser(1);
        assertEquals(1, commentsFromUser.size());
    }

    @Test
    public void getCommentsForSurvey() {
        User user = new User("davenkin", "davenkin@163.com", "password");
        userService.addUser(user);

        List<String> optionNames = createOptionNames("Yes", "No");
        String content = "Do you like programming?";

        Survey survey = new Survey(content, false, Category.CULTURE, optionNames, newHashSet("COMMON_TAG", "TAG1"));
        long surveyId = surveyService.addSurvey(user, survey);

        commentService.addCommentToSurvey("this is a comment", user, surveyId);
        commentService.addCommentToSurvey("this is a comment 2", user, surveyId);
        List<Comment> commentsForSurvey = commentService.getCommentsForSurvey(surveyId);
        assertEquals(2, commentsForSurvey.size());
    }

    @Test
    public void getCommentsFromUser() {
        User user = new User("davenkin", "davenkin@163.com", "password");
        long userId = userService.addUser(user);

        List<String> optionNames = createOptionNames("Yes", "No");
        String content = "Do you like programming?";

        Survey survey = new Survey(content, false, Category.CULTURE, optionNames, newHashSet("COMMON_TAG", "TAG1"));
        long surveyId = surveyService.addSurvey(user, survey);

        commentService.addCommentToSurvey("this is a comment", user, surveyId);
        commentService.addCommentToSurvey("this is a comment 2", user, surveyId);
        List<Comment> commentsFromUser = commentService.getCommentsFromUser(userId);
        assertEquals(2, commentsFromUser.size());
    }

    @Test
    public void removeComment() {
        User user = new User("davenkin", "davenkin@163.com", "password");
        long userId = userService.addUser(user);

        List<String> optionNames = createOptionNames("Yes", "No");
        String content = "Do you like programming?";

        Survey survey = new Survey(content, false, Category.CULTURE, optionNames, newHashSet("COMMON_TAG", "TAG1"));
        long surveyId = surveyService.addSurvey(user, survey);

        long commentId = commentService.addCommentToSurvey("this is a comment", user, surveyId);
        commentService.addCommentToSurvey("this is a comment 2", user, surveyId);

        List<Comment> commentsFromUser = commentService.getCommentsFromUser(userId);
        assertEquals(2, commentsFromUser.size());

        commentService.removeCommentFromSurvey(surveyId,commentId);
        List<Comment> commentsFromUser1 = commentService.getCommentsFromUser(userId);
        assertEquals(1, commentsFromUser1.size());
    }

    private List<String> createOptionNames(String... options) {
        return Lists.newArrayList(options);
    }

}
