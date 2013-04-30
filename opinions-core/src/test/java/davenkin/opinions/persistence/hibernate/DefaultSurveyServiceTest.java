package davenkin.opinions.persistence.hibernate;

import com.google.common.collect.Lists;
import davenkin.opinions.domain.Category;
import davenkin.opinions.domain.Option;
import davenkin.opinions.domain.Survey;
import davenkin.opinions.domain.User;
import davenkin.opinions.service.SurveyService;
import davenkin.opinions.service.TagService;
import davenkin.opinions.service.UserService;
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

import java.util.List;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 3/20/13
 * Time: 10:27 PM
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testHibernateApplicationContext.xml"})
@TestExecutionListeners({DirtiesContextTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class DefaultSurveyServiceTest {

    @Autowired
    public UserService userService;

    @Autowired
    public SurveyService surveyService;

    @Autowired
    public SessionFactory sessionFactory;

    @Autowired
    public TagService tagService;

    @Test
    public void addSurvey() {
        User user = new User("davenkin", "davenkin@163.com", "password");
        long userId = userService.addUser(user);
        Survey survey = user.createSurvey("Do you like programming?", false, Category.SCIENCE, createOptionNames("Yes", "No"), newHashSet("COMMON_TAG", "TAG1"));
        surveyService.addSurvey(survey);

        assertEquals(1, surveyService.getSurveysCreatedByUser(userId).size());
        List<Survey> allSurveys = surveyService.getAllSurveys();
        assertEquals(1, allSurveys.size());

        assertThat(allSurveys.get(0).getContent(), is("Do you like programming?"));
        assertThat(allSurveys.get(0).getSurveyCategory(), is(Category.SCIENCE));
        assertThat(allSurveys.get(0).getOptions().size(), is(2));
    }

    @Test
    public void loadSurvey() {
        User user = new User("davenkin", "davenkin@163.com", "password");
        userService.addUser(user);

        Survey survey = user.createSurvey("Do you like programming?", false, Category.SCIENCE, createOptionNames("Yes", "No"), newHashSet("COMMON_TAG", "TAG1"));
        long surveyId = surveyService.addSurvey(survey);

        Survey surveyById = surveyService.getSurveyById(surveyId);
        assertThat(surveyById.getContent(), is("Do you like programming?"));
        assertThat(surveyById.getOptions().size(), is(2));
    }

    @Test
    public void findSurveyByTag() {
        User user = new User("davenkin", "davenkin@163.com", "password");
        userService.addUser(user);

        List<String> optionNames = createOptionNames("Yes", "No");
        String content = "Do you like programming?";
        Set<String> tags1 = newHashSet("COMMON_TAG", "TAG1");
        Set<String> tags2 = newHashSet("COMMON_TAG", "TAG2");

        Survey survey = user.createSurvey(content, false, Category.SCIENCE, optionNames, tags1);
        surveyService.addSurvey(survey);

        Survey survey2 = user.createSurvey(content, false, Category.SCIENCE, optionNames, tags2);
        surveyService.addSurvey(survey2);

        assertEquals(1, surveyService.getSurveysByTag("TAG1").size());

        assertEquals(1, surveyService.getSurveysByTag("TAG2").size());

        assertEquals(2, surveyService.getSurveysByTag("COMMON_TAG").size());

    }


    @Test
    public void deleteSurvey() {
        User user = new User("davenkin", "davenkin@163.com", "password");
        userService.addUser(user);
        List<String> optionNames = createOptionNames("Yes", "No");
        String content = "Do you like programming?";
        Survey survey1 = user.createSurvey(content, false, Category.SCIENCE, optionNames, newHashSet("COMMON_TAG", "TAG1"));
        long surveyId1 = surveyService.addSurvey(survey1);
        String content2 = "How do you love China?";
        Survey survey2 = user.createSurvey(content2, false, Category.SCIENCE, optionNames, newHashSet("COMMON_TAG", "TAG1"));
        surveyService.addSurvey(survey2);


        List<Survey> originalSurveys = surveyService.getAllSurveys();
        assertThat(originalSurveys.size(), is(2));

        surveyService.removeSurvey(surveyId1);
        List<Survey> allSurveys = surveyService.getAllSurveys();
        assertThat(allSurveys.size(), is(1));
    }

    @Test
    public void getSurveyByUser() {
        User user = new User("davenkin", "davenkin@163.com", "password");
        long userId = userService.addUser(user);
        String content = "Do you like programming?";
        Survey survey = user.createSurvey(content, false, Category.SCIENCE, createOptionNames("Yes", "No"), newHashSet("COMMON_TAG", "TAG1"));
        surveyService.addSurvey(survey);

        Survey survey2 = user.createSurvey("Do you like China?", false, Category.SCIENCE, createOptionNames("Yes", "No"), newHashSet("COMMON_TAG", "TAG1"));
        surveyService.addSurvey(survey2);

        List<Survey> surveyList = surveyService.getSurveysCreatedByUser(userId);
        assertEquals(2, surveyList.size());
    }


    @Test
    public void getSurveyByCategory() {
        User user = new User("davenkin", "davenkin@163.com", "password");
        userService.addUser(user);

        List<String> optionNames = createOptionNames("Yes", "No");
        String content = "Do you like programming?";

        Survey survey1 = user.createSurvey(content, false, Category.CULTURE, optionNames, newHashSet("COMMON_TAG", "TAG1"));
        surveyService.addSurvey(survey1);

        String content2 = "How do you love China?";
        Survey survey2 = user.createSurvey(content2, false, Category.SCIENCE, optionNames, newHashSet("COMMON_TAG", "TAG1"));
        surveyService.addSurvey(survey2);

        List<Survey> surveyList = surveyService.getSurveysByCategory(Category.CULTURE);
        assertEquals(1, surveyList.size());
    }

    @Test
    public void voteOptionWithOutUser() {
        User user = new User("davenkin", "davenkin@163.com", "password");
        userService.addUser(user);

        List<String> optionNames = createOptionNames("Yes", "No");
        String content = "Do you like programming?";

        Survey survey = user.createSurvey(content, false, Category.CULTURE, optionNames, newHashSet("COMMON_TAG", "TAG1"));
        long surveyId = surveyService.addSurvey(survey);
        Option option = survey.getOptions().get(0);
        surveyService.voteSurveyOption(surveyId, option.getId());
        assertThat(option.getOptionCount(), is(1l));
    }

    @Test
    public void voteOptionWithUser() {
        User user = new User("davenkin", "davenkin@163.com", "password");
        long userId = userService.addUser(user);

        List<String> optionNames = createOptionNames("Yes", "No");
        String content = "Do you like programming?";

        Survey survey = user.createSurvey(content, false, Category.CULTURE, optionNames, newHashSet("COMMON_TAG", "TAG1"));
        long surveyId = surveyService.addSurvey(survey);
        Option option = survey.getOptions().get(0);
        surveyService.voteSurveyOption(userId, surveyId, option.getId());
        assertThat(option.getOptionCount(), is(1l));
        assertThat(user.getVotes().size(), is(1));

    }

    @Test
    public void cannotVoteTheSameOptionWithUser() {
        User user = new User("davenkin", "davenkin@163.com", "password");
        long userId = userService.addUser(user);

        List<String> optionNames = createOptionNames("Yes", "No");
        String content = "Do you like programming?";

        Survey survey = user.createSurvey(content, false, Category.CULTURE, optionNames, newHashSet("COMMON_TAG", "TAG1"));
        long surveyId = surveyService.addSurvey(survey);
        Option option = survey.getOptions().get(0);
        surveyService.voteSurveyOption(userId, surveyId, option.getId());
        surveyService.voteSurveyOption(userId, surveyId, option.getId());
        assertThat(option.getOptionCount(), is(1l));
        assertThat(user.getVotes().size(), is(1));

    }

    private List<String> createOptionNames(String... options) {
        return Lists.newArrayList(options);
    }


}
