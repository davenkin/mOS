package davenkin.opinions.persistence.dao.jdbc;

import davenkin.opinions.domain.DataAccessException;
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
    public void findAllTagsForSurvey() throws DataAccessException
    {
        List<String> tags = tagDao.findTagsForSurvey(9l);
        assertTrue(tags.size() == 3);
        assertThat(tags.get(1), is("city"));
    }

    @Test
    public void shouldReturnEmptyListWhenNoTagsExistForSurvey() throws DataAccessException
    {
        long noTagSurvey = 4L;
        assertTrue(tagDao.findTagsForSurvey(noTagSurvey).size() == 0);
    }

    @Test
    public void addNewTagForSurvey() throws DataAccessException
    {
        String tagName = "ddd";
        tagDao.addTagForSurvey(9l, tagName);
        List<String> tagsForSurvey = tagDao.findTagsForSurvey(9l);
       assertTrue(tagsForSurvey.contains(tagName));
        List<String> allTags = tagDao.findAllTags();
        assertTrue(allTags.contains(tagName));
        tagDao.removeTag(tagName);

    }

    @Test
    public void addExistingTagToSurvey() throws DataAccessException
    {
        tagDao.addTagForSurvey(9l, "business");
    }

    @Test(expected = DataAccessException.class)
    public void nonExistentUser() throws DataAccessException
    {
        tagDao.addTagForSurvey(120l, "ddd");
    }

    
}
