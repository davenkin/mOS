package davenkin.opinions.persistence.service.jdbc;

import davenkin.opinions.domain.DataAccessException;
import davenkin.opinions.persistence.DataSourceUtil;
import davenkin.opinions.persistence.jdbc.JdbcTemplate;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

public class JdbcTagServiceTest
{

    private JdbcTagService jdbcTagService;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() throws Exception
    {
        BasicDataSource dataSource = DataSourceUtil.createDataSource();
        jdbcTagService = new JdbcTagService(dataSource);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Test
    public void addTagForSurvey() throws DataAccessException
    {
        long before = jdbcTemplate.queryForLong("SELECT COUNT(*) FROM SURVEY_TAG WHERE SURVEY_ID = ?", new Object[]{1});
        String tag = "learning";
        jdbcTagService.addTagToSurvey(1, tag);
        long after = jdbcTemplate.queryForLong("SELECT COUNT(*) FROM SURVEY_TAG WHERE SURVEY_ID = ?", new Object[]{1});
        assertEquals(after, before + 1);
        jdbcTemplate.update("DELETE FROM SURVEY_TAG WHERE TAG_NAME = ?", new Object[]{tag});
    }

    @Test
    public void shouldAddTagWhenException() throws DataAccessException
    {
        long before = jdbcTemplate.queryForLong("SELECT COUNT(*) FROM SURVEY_TAG WHERE SURVEY_ID = ?", new Object[]{1});
        String tag = "uniqueTag";
        jdbcTagService.addTagToSurvey(100, tag);
        long after = jdbcTemplate.queryForLong("SELECT COUNT(*) FROM SURVEY_TAG WHERE SURVEY_ID = ?", new Object[]{1});
        assertEquals(after, before);
    }

    @Test
    public void shouldNotAddWhenTagAlreadyExist() throws DataAccessException
    {
        long before = jdbcTemplate.queryForLong("SELECT COUNT(*) FROM SURVEY_TAG WHERE SURVEY_ID = ?", new Object[]{1});
        String tag = "uniqueTag2";
        jdbcTagService.addTagToSurvey(1, tag);
        jdbcTagService.addTagToSurvey(1, tag);
        long after = jdbcTemplate.queryForLong("SELECT COUNT(*) FROM SURVEY_TAG WHERE SURVEY_ID = ?", new Object[]{1});
        assertEquals(after, before + 1);
        jdbcTemplate.update("DELETE FROM TAG WHERE NAME = ?", new Object[]{tag});
    }

    @Test
    public void removeTagFromSurvey() throws DataAccessException
    {
        long before = jdbcTemplate.queryForLong("SELECT COUNT(*) FROM SURVEY_TAG WHERE SURVEY_ID = ?", new Object[]{2});
        String tag = "uniqueTag3";
        jdbcTagService.addTagToSurvey(2, tag);
        long after1 = jdbcTemplate.queryForLong("SELECT COUNT(*) FROM SURVEY_TAG WHERE SURVEY_ID = ?", new Object[]{2});
        assertEquals(before, after1 - 1);
        jdbcTagService.removeTagFromSurvey(2, tag);
        long after = jdbcTemplate.queryForLong("SELECT COUNT(*) FROM SURVEY_TAG WHERE SURVEY_ID = ?", new Object[]{2});
        assertEquals(before, after);
    }

    @Test
    public void getTagsForSurvey(){
        List<String> tagsForSurvey = jdbcTagService.getTagsForSurvey(9);
        assertEquals(tagsForSurvey.size(), 3);
    }
}
