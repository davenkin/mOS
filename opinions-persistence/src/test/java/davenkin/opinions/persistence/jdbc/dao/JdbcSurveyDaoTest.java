package davenkin.opinions.persistence.jdbc.dao;

import davenkin.opinions.domain.Category;
import davenkin.opinions.domain.Survey;
import davenkin.opinions.domain.DataAccessException;
import davenkin.opinions.persistence.DataSourceUtil;
import davenkin.opinions.persistence.dao.SurveyDao;
import davenkin.opinions.persistence.jdbc.dao.JdbcSurveyDao;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class JdbcSurveyDaoTest
{
    private SurveyDao surveyDao;

    @Before
    public void setUp() throws Exception
    {
        surveyDao = new JdbcSurveyDao(DataSourceUtil.createDataSource());
    }


    @Test
    public void testFindSurveyById() throws DataAccessException
    {
        Survey surveyById = surveyDao.findSurveyById(1L);
        assertNotNull(surveyById);
        assertThat(surveyById.getContent(), is("How many years have you been programming?"));
        assertFalse(surveyById.isCanMultipleChecked());
        assertTrue(surveyById.getComments().size() == 3);
        assertThat(surveyById.getComments().get(1).getContent(), is("More than 10 years! I am really an old man."));
        assertThat(surveyById.getSurveyCategory(), is(Category.SCIENCE));
        assertThat(surveyById.getCreatingUser().getName(), is("davenkin"));
        assertTrue(surveyById.getOptions().size() == 3);
        assertThat(surveyById.getOptions().get(0).getOption(), is("More than 10 years"));
        assertTrue(surveyById.getSurveyTags().size() == 2);
        assertThat(surveyById.getSurveyTags().get(0), is("computer"));
    }

    @Test
    public void shouldReturnNullWhenNoSurveyExist() throws DataAccessException
    {
        long nonExistSurveyId = 100L;
        assertNull(surveyDao.findSurveyById(nonExistSurveyId));
    }

    @Test
    public void getAllSurveys() throws DataAccessException
    {
        List<Survey> allSurveys = surveyDao.findAllSurveys();
        assertThat(allSurveys.size(), is(9));
    }

    @Test
    public void getSurveysCreatedByUser() throws DataAccessException
    {
        List<Survey> surveysCreatedByUser = surveyDao.findSurveysCreatedByUser(2L);
        assertThat(surveysCreatedByUser.size(), is(3));
    }

    @Test
    public void getSurveysByCategory() throws DataAccessException
    {
        List<Survey> surveysByCategory = surveyDao.findSurveysByCategory(Category.SCIENCE);
        assertThat(surveysByCategory.size(), is(2));
    }

    @Test
    public void getSurveyByTag() throws DataAccessException
    {
        List<Survey> surveysByTag = surveyDao.findSurveysByTag("book");
        assertThat(surveysByTag.size(), is(2));
    }

    @Test
    public void getSurveyBetweenDate() throws ParseException, DataAccessException
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date fromDate = new Date(dateFormat.parse("12-1-2012").getTime());
        Date toDate = new Date(dateFormat.parse("1-4-2013").getTime());
        List<Survey> surveys = surveyDao.findSurveysCreatedBetween(fromDate, toDate);
        assertThat(surveys.size(), is(2));
    }
}
