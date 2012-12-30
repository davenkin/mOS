package davenkin.opinions.persistence.dao;

import davenkin.opinions.domain.SurveyComment;
import davenkin.opinions.persistence.DataSourceUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JdbcCommentDaoTest
{

    private JdbcCommentDao commentDao;

    @Before
    public void setUp() throws Exception
    {
        commentDao = new JdbcCommentDao(DataSourceUtil.createDataSource());
    }

    @Test
    public void testFindCommentsForSurvey() throws Exception
    {
        List<SurveyComment> comments = commentDao.findCommentsForSurvey(1L);
        assertTrue(comments.size() == 3);
        assertEquals(comments.get(0).getContent(), "How time flies! 4 years!");
        assertEquals(comments.get(1).getUser().getName(), "davenkin");

    }

    @Test
    public void shouldReturnEmptySetIfNoCommentFound()
    {
        Long noCommentId = 123L;
        assertTrue(commentDao.findCommentsForSurvey(noCommentId).size() == 0);
    }
}
