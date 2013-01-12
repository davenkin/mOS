package davenkin.opinions.persistence.service.jdbc;

import davenkin.opinions.domain.DataAccessException;
import davenkin.opinions.persistence.DataSourceUtil;
import davenkin.opinions.persistence.jdbc.JdbcTemplate;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JdbcCommentServiceTest
{

    private JdbcCommentService jdbcCommentService;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() throws Exception
    {
        BasicDataSource dataSource = DataSourceUtil.createDataSource();
        jdbcCommentService = new JdbcCommentService(dataSource);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Test
    public void addCommentToSurvey() throws DataAccessException
    {
        String content = "test for adding comment to survey";
        long before = jdbcTemplate.queryForLong("SELECT COUNT(*) FROM COMMENT WHERE USER_ID = ?", new Object[]{2});
        jdbcCommentService.addCommentToSurvey(content, 4, 2);
        long expected = jdbcTemplate.queryForLong("SELECT COUNT(*) FROM COMMENT WHERE USER_ID = ?", new Object[]{2});
        assertEquals(expected, before + 1);
        jdbcTemplate.update("DELETE FROM COMMENT WHERE SURVEY_ID = ?", new Object[]{4});
    }


    @Test
    public void shouldNotAddCommentWhenException() throws DataAccessException
    {
        String content = "test for adding comment to survey";
        long before = jdbcTemplate.queryForLong("SELECT COUNT(*) FROM COMMENT WHERE USER_ID = ?", new Object[]{2});

        int notExistSurveyId = 20;
        jdbcCommentService.addCommentToSurvey(content, notExistSurveyId, 2);

        long expected = jdbcTemplate.queryForLong("SELECT COUNT(*) FROM COMMENT WHERE USER_ID = ?", new Object[]{2});
        assertEquals(expected, before);
    }

}
