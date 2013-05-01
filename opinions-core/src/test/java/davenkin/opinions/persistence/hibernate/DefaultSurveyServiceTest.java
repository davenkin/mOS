package davenkin.opinions.persistence.hibernate;

import com.google.common.collect.Lists;
import davenkin.opinions.domain.Category;
import davenkin.opinions.domain.Option;
import davenkin.opinions.domain.Survey;
import davenkin.opinions.domain.User;
import davenkin.opinions.service.SurveyService;
import davenkin.opinions.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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

public class DefaultSurveyServiceTest extends CommonTestFixture {

    @Autowired
    public UserService userService;

    @Autowired
    public SurveyService surveyService;

    private long userId;

    @Before
    public void setUp() throws Exception {
        userId = createUser();
    }

    @Test
    public void addSurvey() {
        Survey survey = new Survey(userId, "Do you like programming?", false, Category.SCIENCE, createOptionNames("Yes", "No"), newHashSet("COMMON_TAG", "TAG1"));
        long surveyId = surveyService.addSurvey(survey);
        Survey surveyFromDb = getSurveyFromDb(surveyId);

        assertThat(surveyFromDb.getContent(), is("Do you like programming?"));
        assertThat(surveyFromDb.getSurveyCategory(), is(Category.SCIENCE));
        assertThat(surveyFromDb.getOptions().size(), is(2));
    }

    @Test
    public void loadSurvey() {
        Survey survey = new Survey(userId, "Do you like programming?", false, Category.SCIENCE, createOptionNames("Yes", "No"), newHashSet("COMMON_TAG", "TAG1"));
        long surveyId = surveyService.addSurvey(survey);

        Survey surveyById = surveyService.getSurveyById(surveyId);
        assertThat(surveyById.getContent(), is("Do you like programming?"));
        assertThat(surveyById.getOptions().size(), is(2));
    }

    @Test
    public void findSurveyByTag() {
        List<String> optionNames = createOptionNames("Yes", "No");
        String content = "Do you like programming?";
        Set<String> tags1 = newHashSet("COMMON_TAG", "TAG1");
        Set<String> tags2 = newHashSet("COMMON_TAG", "TAG2");

        Survey survey = new Survey(userId, content, false, Category.SCIENCE, optionNames, tags1);
        surveyService.addSurvey(survey);

        Survey survey2 = new Survey(userId, content, false, Category.SCIENCE, optionNames, tags2);
        surveyService.addSurvey(survey2);

        assertEquals(1, surveyService.getSurveysByTag("TAG1").size());
        assertEquals(1, surveyService.getSurveysByTag("TAG2").size());
        assertEquals(2, surveyService.getSurveysByTag("COMMON_TAG").size());

    }

    @Test
    public void deleteSurvey() {
        List<String> optionNames = createOptionNames("Yes", "No");
        String content = "Do you like programming?";
        Survey survey1 = new Survey(userId, content, false, Category.SCIENCE, optionNames, newHashSet("COMMON_TAG", "TAG1"));
        long surveyId1 = surveyService.addSurvey(survey1);

        String content2 = "How do you love China?";
        Survey survey2 = new Survey(userId, content2, false, Category.SCIENCE, optionNames, newHashSet("COMMON_TAG", "TAG1"));
        surveyService.addSurvey(survey2);


        List<Survey> originalSurveys = surveyService.getAllSurveys();
        assertThat(originalSurveys.size(), is(2));

        surveyService.removeSurvey(surveyId1);

        List<Survey> allSurveys = getAllSurveysFromDb();
        assertThat(allSurveys.size(), is(1));
        assertThat(allSurveys.get(0).getContent(), is(content2));
    }


    @Test
    public void getSurveyByUser() {
        String content = "Do you like programming?";
        Survey survey = new Survey(userId, content, false, Category.SCIENCE, createOptionNames("Yes", "No"), newHashSet("COMMON_TAG", "TAG1"));
        surveyService.addSurvey(survey);

        Survey survey2 = new Survey(userId, "Do you like China?", false, Category.POLITICS, createOptionNames("Yes", "No"), newHashSet("COMMON_TAG", "TAG1"));
        surveyService.addSurvey(survey2);

        List<Survey> surveyList = surveyService.getSurveysCreatedByUser(userId);
        assertEquals(2, surveyList.size());
    }

    @Test
    public void getSurveyByCategory() {
        List<String> optionNames = createOptionNames("Yes", "No");
        String content = "Do you like programming?";

        Survey survey1 = new Survey(userId, content, false, Category.CULTURE, optionNames, newHashSet("COMMON_TAG", "TAG1"));
        surveyService.addSurvey(survey1);

        String content2 = "How do you love China?";
        Survey survey2 = new Survey(userId, content2, false, Category.SCIENCE, optionNames, newHashSet("COMMON_TAG", "TAG1"));
        surveyService.addSurvey(survey2);

        List<Survey> surveyList = surveyService.getSurveysByCategory(Category.CULTURE);
        assertEquals(1, surveyList.size());
    }


    @Test
    public void voteOptionWithoutUser() {
        List<String> optionNames = createOptionNames("Yes", "No");
        String content = "Do you like programming?";

        Survey survey = new Survey(userId, content, false, Category.CULTURE, optionNames, newHashSet("COMMON_TAG", "TAG1"));
        long surveyId = surveyService.addSurvey(survey);
        Option option = survey.getOptions().get(0);

        surveyService.voteSurveyOption(surveyId, option.getId());
        Survey surveyFromDb = getSurveyFromDb(surveyId);
        assertThat(surveyFromDb.getOption(option.getId()).getOptionCount(), is(1l));
    }

    @Test
    public void voteOptionWithUser() {
        List<String> optionNames = createOptionNames("Yes", "No");
        String content = "Do you like programming?";

        Survey survey = new Survey(userId, content, false, Category.CULTURE, optionNames, newHashSet("COMMON_TAG", "TAG1"));
        long surveyId = surveyService.addSurvey(survey);

        Option option = survey.getOptions().get(0);
        surveyService.voteSurveyOption(userId, surveyId, option.getId());
        Survey surveyFromDb = getSurveyFromDb(surveyId);
        assertThat(surveyFromDb.getOption(option.getId()).getOptionCount(), is(1l));

        User userFromDb = getUserFromDb(userId);
        assertThat(userFromDb.getVotes().size(), is(1));

    }

    @Test
    public void cannotVoteTheSameOptionWithUser() {
        List<String> optionNames = createOptionNames("Yes", "No");
        String content = "Do you like programming?";

        Survey survey = new Survey(userId, content, false, Category.CULTURE, optionNames, newHashSet("COMMON_TAG", "TAG1"));
        long surveyId = surveyService.addSurvey(survey);

        Option option = survey.getOptions().get(0);
        surveyService.voteSurveyOption(userId, surveyId, option.getId());
        surveyService.voteSurveyOption(userId, surveyId, option.getId());

        Survey surveyFromDb = getSurveyFromDb(surveyId);
        assertThat(surveyFromDb.getOption(option.getId()).getOptionCount(), is(1l));

        User userFromDb = getUserFromDb(userId);
        assertThat(userFromDb.getVotes().size(), is(1));
    }

    private List<String> createOptionNames(String... options) {
        return Lists.newArrayList(options);
    }

    private long createUser() {
        User user = new User("davenkin", "davenkin@163.com", "password");
        return userService.addUser(user);
    }


}
