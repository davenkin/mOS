package davenkin.opinions.persistence.hibernate;

import davenkin.opinions.domain.Category;
import davenkin.opinions.domain.Survey;
import davenkin.opinions.domain.User;
import davenkin.opinions.persistence.service.SurveyService;
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
        DependencyInjectionTestExecutionListener.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class HibernateSurveyServiceTest {

    @Autowired
    public UserService userService;

    @Autowired
    public SurveyService surveyService;

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Test
    public void addSurvey()
    {
        long userId = addNewUser();
        User user = userService.getUserById(userId);
        ArrayList<String> optionNames = new ArrayList<String>();
        optionNames.add("Yes");
        optionNames.add("No");
        Survey survey= user.createSurvey("Do you like programming?", false, Category.SCIENCE,optionNames);
        surveyService.addSurvey(survey);
                 assertEquals(1, getDbRecordCount("SURVEY")) ;
                 assertEquals(2, getDbRecordCount("SURVEY_OPTION")) ;

    }


    @Test
    public void loadSurvey()
    {
        long userId = addNewUser();
        User user = userService.getUserById(userId);
        ArrayList<String> optionNames = new ArrayList<String>();
        optionNames.add("Yes");
        optionNames.add("No");
        String content = "Do you like programming?";
        Survey survey= user.createSurvey(content, false, Category.SCIENCE,optionNames);
        long surveyId = surveyService.addSurvey(survey);
        Survey surveyById = surveyService.getSurveyById(surveyId);
        assertThat(surveyById.getContent(), is(content));
        assertThat(surveyById.getOptions().size(),is(2));
    }

    @Test
    public void takeSurvey()
    {
        long surveyId = createUserAndSurvey();
        Survey survey = surveyService.getSurveyById(surveyId);
        long optionId = survey.getOptions().get(0).getId();
        surveyService.takeSurvey(optionId);
        assertEquals(1,surveyService.getSurveyById(surveyId).getOptions().get(0).getOptionCount());
    }

    @Test
    public void deleteSurvey(){
        long surveyId = createUserAndSurvey();
        assertEquals(1,getDbRecordCount("SURVEY"));
        assertEquals(2,getDbRecordCount("SURVEY_OPTION"));
        surveyService.removeSurvey(surveyId);
        assertEquals(0,getDbRecordCount("SURVEY"));
        assertEquals(0,getDbRecordCount("SURVEY_OPTION"));
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


    private String getNameFromDB() {
        return jdbcTemplate.queryForObject("SELECT NAME FROM USER", String.class);
    }

    private int getDbRecordCount(String tableName) {
        return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM " + tableName);
    }

}
