package davenkin.opinions.persistence.dao.jdbc;

import davenkin.opinions.persistence.DataSourceUtil;
import davenkin.opinions.persistence.dao.SurveyTagDao;
import davenkin.opinions.persistence.dao.jdbc.JdbcSurveyTagDao;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class JdbcSurveyTagDaoTest
{

    private SurveyTagDao surveyTagDao;

    @Before
    public void setUp() throws Exception
    {
        surveyTagDao = new JdbcSurveyTagDao(DataSourceUtil.createDataSource());
    }


    @Test
    public void findAllTagsForSurvey()
    {
        List<String> tags = surveyTagDao.findTagsForSurvey(9l);
        assertTrue(tags.size() == 3);
        assertThat(tags.get(0), is("city"));
    }

    @Test
    public void shouldReturnEmptyListWhenNoTagsExistForSurvey()
    {
        long noTagSurvey = 4L;
        assertTrue(surveyTagDao.findTagsForSurvey(noTagSurvey).size() == 0);
    }
}
