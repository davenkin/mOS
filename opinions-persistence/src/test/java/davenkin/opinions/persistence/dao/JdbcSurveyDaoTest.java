package davenkin.opinions.persistence.dao;

import davenkin.opinions.domain.Survey;
import davenkin.opinions.persistence.DataSourceUtil;
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
    public void testFindSurveyById()
    {
        Survey surveyById = surveyDao.findSurveyById(1L);
        assertNotNull(surveyById);
        assertThat(surveyById.getContent(), is("How many years have you been programming?"));
        assertFalse(surveyById.isCanMultipleChecked());
        assertTrue(surveyById.getSurveyComments().size() == 3);
        assertThat(surveyById.getSurveyComments().get(1).getContent(), is("More than 10 years! I am really an old man."));
        assertThat(surveyById.getSurveyCategory(), is("Science"));
        assertThat(surveyById.getCreatingUser().getName(), is("davenkin"));
        assertTrue(surveyById.getSurveyOptions().size() == 3);
        assertThat(surveyById.getSurveyOptions().get(0).getOption(), is("More than 10 years"));
        assertTrue(surveyById.getSurveyTags().size() == 2);
        assertThat(surveyById.getSurveyTags().get(0), is("computer"));
    }

    @Test
    public void shouldReturnNullWhenNoSurveyExist()
    {
        long nonExistSurveyId = 100L;
        assertNull(surveyDao.findSurveyById(nonExistSurveyId));
    }

}
