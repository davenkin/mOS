package davenkin.opinions.persistence.service.jdbc;

import davenkin.opinions.domain.Comment;
import davenkin.opinions.domain.DataAccessException;
import davenkin.opinions.persistence.DataSourceUtil;
import davenkin.opinions.persistence.jdbc.JdbcTemplate;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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

    @Test
    public void getCommentsForSurvey()
    {
        List<Comment> commentsForSurvey = jdbcCommentService.getCommentsForSurvey(1);
        assertEquals(3, commentsForSurvey.size());
        List<Comment> commentsForNonSurvey = jdbcCommentService.getCommentsForSurvey(10);
        assertEquals(0, commentsForNonSurvey.size());
    }
    
    @Test
    public void removeComment() throws DataAccessException
    {
        jdbcCommentService.addCommentToSurvey("test comment", 2, 1);
        List<Comment> commentsForSurvey = jdbcCommentService.getCommentsForSurvey(2);
        assertEquals(1, commentsForSurvey.size());
        jdbcCommentService.removeCommentFromSurvey(commentsForSurvey.get(0).getId());
        assertEquals(0, jdbcCommentService.getCommentsForSurvey(2).size());
        jdbcTemplate.update("DELETE FROM COMMENT WHERE SURVEY_ID = ?", new Object[]{2});

    }

}
