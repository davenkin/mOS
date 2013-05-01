package davenkin.opinions.persistence.hibernate;

import com.google.common.collect.Lists;
import davenkin.opinions.domain.Category;
import davenkin.opinions.domain.Comment;
import davenkin.opinions.domain.Survey;
import davenkin.opinions.domain.User;
import davenkin.opinions.service.CommentService;
import davenkin.opinions.service.SurveyService;
import davenkin.opinions.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.google.common.collect.Sets.newHashSet;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 4/1/13
 * Time: 1:34 PM
 * To change this template use File | Settings | File Templates.
 */

public class DefaultCommentServiceTest extends CommonTestFixture {

    @Autowired
    public UserService userService;

    @Autowired
    public SurveyService surveyService;

    @Autowired
    public CommentService commentService;
    private long userId;

    @Before
    public void setUp() throws Exception {
        userId = createUser();
    }

    @Test
    public void addCommentToSurvey() {
        List<String> optionNames = createOptionNames("Yes", "No");
        String content = "Do you like programming?";

        Survey survey = new Survey(userId, content, false, Category.CULTURE, optionNames, newHashSet("COMMON_TAG", "TAG1"));
        long surveyId = surveyService.addSurvey(survey);


        String commentContent = "this is a comment";
        commentService.addComment(commentContent, userId, surveyId);
        List<Comment> commentsFromDb = getCommentsFromDb();
        assertEquals(1, commentsFromDb.size());
        assertThat(commentsFromDb.get(0).getContent(), is(commentContent));
    }

    @Test
    public void getCommentsForSurvey() {
        List<String> optionNames = createOptionNames("Yes", "No");
        String content = "Do you like programming?";

        Survey survey = new Survey(userId, content, false, Category.CULTURE, optionNames, newHashSet("COMMON_TAG", "TAG1"));
        long surveyId = surveyService.addSurvey(survey);

        commentService.addComment("this is a comment", userId, surveyId);
        commentService.addComment("this is a comment 2", userId, surveyId);
        List<Comment> commentsForSurvey = commentService.getCommentsBySurvey(surveyId);
        assertEquals(2, commentsForSurvey.size());
    }

    @Test
    public void getCommentsFromUser() {
        List<String> optionNames = createOptionNames("Yes", "No");
        String content = "Do you like programming?";

        Survey survey = new Survey(userId, content, false, Category.CULTURE, optionNames, newHashSet("COMMON_TAG", "TAG1"));
        long surveyId = surveyService.addSurvey(survey);

        commentService.addComment("this is a comment", userId, surveyId);
        commentService.addComment("this is a comment 2", userId, surveyId);
        List<Comment> commentsFromUser = commentService.getCommentsByUser(userId);
        assertEquals(2, commentsFromUser.size());
    }

    @Test
    public void removeComment() {
        List<String> optionNames = createOptionNames("Yes", "No");
        String content = "Do you like programming?";

        Survey survey = new Survey(userId, content, false, Category.CULTURE, optionNames, newHashSet("COMMON_TAG", "TAG1"));
        long surveyId = surveyService.addSurvey(survey);

        commentService.addComment("this is a comment", userId, surveyId);
        commentService.addComment("this is a comment 2", userId, surveyId);

        List<Comment> commentsFromUser = commentService.getCommentsByUser(userId);
        assertEquals(2, commentsFromUser.size());

        commentService.removeComment(commentsFromUser.get(0).getId());
        List<Comment> commentsFromUser1 = commentService.getCommentsByUser(userId);
        assertEquals(1, commentsFromUser1.size());
    }

    private List<String> createOptionNames(String... options) {
        return Lists.newArrayList(options);
    }

    private long createUser() {
        User user = new User("davenkin", "davenkin@163.com", "password");
        return userService.addUser(user);
    }

}
