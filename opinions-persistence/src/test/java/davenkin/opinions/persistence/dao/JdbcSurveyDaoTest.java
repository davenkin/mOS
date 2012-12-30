package davenkin.opinions.persistence.dao;

import davenkin.opinions.persistence.DataSourceUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class JdbcSurveyDaoTest
{
    private SurveyDao surveyDao;

    @Before
    public void setUp() throws Exception
    {
        surveyDao = new JdbcSurveyDao(DataSourceUtil.createDataSource());
    }

    @Test
    public void findAllTagsForSurvey()
    {
        List<String> tags = surveyDao.findTagsForSurvey(9l);
        assertTrue(tags.size() == 3);
        assertThat(tags.get(0), is("city"));
    }

    @Test
    public void shouldReturnEmptyListWhenNoTagsExistForSurvey()
    {
        long noTagSurvey = 4L;
        assertTrue(surveyDao.findTagsForSurvey(noTagSurvey).size() == 0);
    }

}
