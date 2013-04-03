package davenkin.opinions.persistence.hibernate;

import davenkin.opinions.domain.Category;
import davenkin.opinions.domain.Survey;
import davenkin.opinions.domain.User;
import davenkin.opinions.persistence.service.SurveyService;
import davenkin.opinions.persistence.service.TagService;
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
        DependencyInjectionTestExecutionListener.class,TransactionalTestExecutionListener.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TransactionConfiguration(transactionManager = "transactionManager")
@Transactional
public class HibernateSurveyServiceTest {

    @Autowired
    public UserService userService;

    @Autowired
    public SurveyService surveyService;

    @Autowired
    public SessionFactory sessionFactory;

    @Autowired
    public TagService tagService;

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
                 assertEquals(1, surveyService.getAllSurveys().size()) ;
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
    public void findSurveyByTag(){
        long surveyId = createUserAndSurvey();
        long surveyId1 = createUserAndSurvey();
        tagService.addTagToSurvey(surveyId,"TAG");
        tagService.addTagToSurvey(surveyId,"TAG1");
        tagService.addTagToSurvey(surveyId1,"TAG1");
        tagService.addTagToSurvey(surveyId1,"TAG2");
        List<Survey> surveysByTag = surveyService.getSurveysByTag("TAG1");
        assertEquals(2,surveysByTag.size());
        assertEquals(1,surveyService.getSurveysByTag("TAG").size());

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
        surveyService.getAllSurveys().size();
        assertEquals(1, getDbRecordCount("SURVEY"));
        assertEquals(2, getDbRecordCount("SURVEY_OPTION"));
        surveyService.removeSurvey(surveyId);
        surveyService.getAllSurveys().size();
        assertEquals(0, getDbRecordCount("SURVEY"));
        assertEquals(0, getDbRecordCount("SURVEY_OPTION"));
    }

    @Test
    public void getAllSurvey(){
        createUserAndSurvey();
        createUserAndSurvey();
        List<Survey> surveys = surveyService.getAllSurveys();
        assertEquals(2,surveys.size());
         }


    @Test
    public void getSurveyByUser()
    {
        long userId = addNewUser();
        User user = userService.getUserById(userId);
        ArrayList<String> optionNames = new ArrayList<String>();
        optionNames.add("Yes");
        optionNames.add("No");
        String content = "Do you like programming?";
        Survey survey= user.createSurvey(content, false, Category.SCIENCE, optionNames);

        surveyService.addSurvey(survey);
        Survey survey1 = user.createSurvey("fakeContent", true, Category.CULTURE, optionNames);
        surveyService.addSurvey(survey1);

        List<Survey> surveyList = surveyService.getSurveysCreatedByUser(userId);
        assertEquals(2, surveyList.size());
    }


    @Test
    public void getSurveyByCategory()
    {
        long userId = addNewUser();
        User user = userService.getUserById(userId);
        ArrayList<String> optionNames = new ArrayList<String>();
        optionNames.add("Yes");
        optionNames.add("No");
        String content = "Do you like programming?";
        Survey survey= user.createSurvey(content, false, Category.SCIENCE, optionNames);

        surveyService.addSurvey(survey);
        Survey survey1 = user.createSurvey("fakeContent", true, Category.CULTURE, optionNames);
        surveyService.addSurvey(survey1);

        List<Survey> surveyList = surveyService.getSurveysByCategory(Category.CULTURE);
        assertEquals(1, surveyList.size());
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
