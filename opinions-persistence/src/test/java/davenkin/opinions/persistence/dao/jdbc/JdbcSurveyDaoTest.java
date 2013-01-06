package davenkin.opinions.persistence.dao.jdbc;

import davenkin.opinions.domain.Category;
import davenkin.opinions.domain.Survey;
import davenkin.opinions.persistence.DataAccessException;
import davenkin.opinions.persistence.DataSourceUtil;
import davenkin.opinions.persistence.dao.SurveyDao;
import org.junit.Before;
import org.junit.Test;

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
        assertTrue(surveyById.getSurveyTags().size() == 1);
        assertThat(surveyById.getSurveyTags().get(0), is("computer"));
    }

    @Test
    public void shouldReturnNullWhenNoSurveyExist() throws DataAccessException
    {
        long nonExistSurveyId = 100L;
        assertNull(surveyDao.findSurveyById(nonExistSurveyId));
    }

}
