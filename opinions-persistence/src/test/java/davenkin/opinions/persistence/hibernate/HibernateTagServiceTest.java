package davenkin.opinions.persistence.hibernate;

import com.google.common.collect.Lists;
import davenkin.opinions.domain.Category;
import davenkin.opinions.domain.Survey;
import davenkin.opinions.domain.User;
import davenkin.opinions.persistence.service.SurveyService;
import davenkin.opinions.persistence.service.TagService;
import davenkin.opinions.persistence.service.UserService;
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

import java.util.List;
import java.util.Set;

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
@TransactionConfiguration(transactionManager = "transactionManager")
@Transactional
public class HibernateTagServiceTest {

    @Autowired
    public UserService userService;

    @Autowired
    public SurveyService surveyService;

    @Autowired
    public TagService tagService;

    @Test
    public void addTagToSurvey() {
        User user = new User("davenkin", "davenkin@163.com", "password");
        userService.addUser(user);

        List<String> optionNames = createOptionNames("Yes", "No");
        String content = "Do you like programming?";

        Survey survey = new Survey(content, false, Category.CULTURE, optionNames, newHashSet("COMMON_TAG", "TAG1"));
        long surveyId = surveyService.addSurvey(user, survey);

        tagService.addTagToSurvey(surveyId, "TAG2");
        assertEquals(3, tagService.getTagsForSurvey(surveyId).size());
    }


    @Test
    public void getTagsForSurvey() {
        User user = new User("davenkin", "davenkin@163.com", "password");
        userService.addUser(user);

        List<String> optionNames = createOptionNames("Yes", "No");
        String content = "Do you like programming?";

        Survey survey = new Survey(content, false, Category.CULTURE, optionNames, newHashSet("COMMON_TAG", "TAG1"));
        long surveyId = surveyService.addSurvey(user, survey);

        Set<String> tagsForSurvey = tagService.getTagsForSurvey(surveyId);
        assertEquals(2, tagsForSurvey.size());

    }

    @Test
    public void removeTagFromSurvey() {
        User user = new User("davenkin", "davenkin@163.com", "password");
        userService.addUser(user);

        List<String> optionNames = createOptionNames("Yes", "No");
        String content = "Do you like programming?";

        Survey survey = new Survey(content, false, Category.CULTURE, optionNames, newHashSet("COMMON_TAG", "TAG1"));
        long surveyId = surveyService.addSurvey(user, survey);

        tagService.removeTagFromSurvey(surveyId, "TAG1");
        Set<String> tagsForSurvey = tagService.getTagsForSurvey(surveyId);
        assertEquals(1, tagsForSurvey.size());


    }


    private List<String> createOptionNames(String... options) {
        return Lists.newArrayList(options);
    }


}
