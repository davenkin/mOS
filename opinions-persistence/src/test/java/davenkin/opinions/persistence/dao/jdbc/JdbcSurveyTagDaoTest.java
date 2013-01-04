package davenkin.opinions.persistence.dao.jdbc;

import davenkin.opinions.persistence.DataSourceUtil;
import davenkin.opinions.persistence.dao.TagDao;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class JdbcSurveyTagDaoTest
{

    private TagDao tagDao;

    @Before
    public void setUp() throws Exception
    {
        tagDao = new JdbcTagDao(DataSourceUtil.createDataSource());
    }


    @Test
    public void findAllTagsForSurvey()
    {
        List<String> tags = tagDao.findTagsForSurvey(9l);
        assertTrue(tags.size() == 3);
        assertThat(tags.get(0), is("city"));
    }

    @Test
    public void shouldReturnEmptyListWhenNoTagsExistForSurvey()
    {
        long noTagSurvey = 4L;
        assertTrue(tagDao.findTagsForSurvey(noTagSurvey).size() == 0);
    }
}
