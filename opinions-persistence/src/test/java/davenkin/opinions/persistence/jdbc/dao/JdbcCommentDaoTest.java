package davenkin.opinions.persistence.jdbc.dao;

import davenkin.opinions.domain.Comment;
import davenkin.opinions.domain.DataAccessException;
import davenkin.opinions.persistence.DataSourceUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

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
        List<Comment> comments = commentDao.findCommentsForSurvey(1L);
        assertTrue(comments.size() == 3);
        assertEquals(comments.get(0).getContent(), "How time flies! 4 years!");
        assertEquals(comments.get(1).getUser().getName(), "davenkin");

    }

    @Test
    public void shouldReturnEmptySetIfNoCommentFound() throws DataAccessException
    {
        long noCommentId = 123L;
        assertTrue(commentDao.findCommentsForSurvey(noCommentId).size() == 0);
    }

    @Test
    public void testFindCommentFromUser() throws DataAccessException
    {
        List<Comment> comments = commentDao.findCommentsFromUser(3L);
        assertTrue(comments.size() == 2);
        assertThat(comments.get(0).getContent(), is("How time flies! 4 years!"));
    }

    @Test
    public void addCommentForSurvey() throws DataAccessException
    {
        int previousSize = commentDao.findCommentsForSurvey(9L).size();
        commentDao.addCommentForSurvey(9L, 2L, "test comment " + System.currentTimeMillis());
        int addedSize = commentDao.findCommentsForSurvey(9L).size();
        assertTrue((addedSize - previousSize) == 1);
    }
    
    @Test
    public void removeComment() throws DataAccessException
    {
        commentDao.addCommentForSurvey(4l, 1l, "this is a test comment");
        List<Comment> comments = commentDao.findCommentsForSurvey(4);
        assertThat(comments.size(), is(1));
        commentDao.removeComment(comments.get(0).getId());
        assertThat(commentDao.findCommentsForSurvey(4l).size(), is(0));
    }
}
