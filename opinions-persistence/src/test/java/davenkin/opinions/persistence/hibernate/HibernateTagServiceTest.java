package davenkin.opinions.persistence.hibernate;

import davenkin.opinions.domain.Category;
import davenkin.opinions.domain.Survey;
import davenkin.opinions.domain.User;
import davenkin.opinions.persistence.service.SurveyService;
import davenkin.opinions.persistence.service.TagService;
import davenkin.opinions.persistence.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import java.util.ArrayList;
import java.util.Set;

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
        DependencyInjectionTestExecutionListener.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class HibernateTagServiceTest {

    @Autowired
    public UserService userService;

    @Autowired
    public SurveyService surveyService;

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Autowired
    public TagService tagService;

    @Test
    public void addTagToSurvey(){
        long surveyId = createUserAndSurvey();
        tagService.addTagToSurvey(surveyId,"TAG");
         assertEquals(1, getDbRecordCount("SURVEY_TAG"));
        surveyService.removeSurvey(surveyId);
        assertEquals(0, getDbRecordCount("SURVEY_TAG"));
    }

    @Test
    public void removeTagFromSurvey(){
        long surveyId = createUserAndSurvey();
        tagService.addTagToSurvey(surveyId,"TAG");
        assertEquals(1, getDbRecordCount("SURVEY_TAG"));
tagService.removeTagFromSurvey(surveyId,"TAG");
        assertEquals(0, getDbRecordCount("SURVEY_TAG"));
        assertEquals(1, getDbRecordCount("SURVEY"));

    }

        @Test
    public void getTagsForSurvey(){
        long surveyId = createUserAndSurvey();
        tagService.addTagToSurvey(surveyId,"TAG");
        tagService.addTagToSurvey(surveyId, "TAG2");
            Set<String> tagsForSurvey = tagService.getTagsForSurvey(surveyId);
            assertEquals(2, tagsForSurvey.size());

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

    private int getDbRecordCount(String tableName) {
        return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM " + tableName);
    }
}
