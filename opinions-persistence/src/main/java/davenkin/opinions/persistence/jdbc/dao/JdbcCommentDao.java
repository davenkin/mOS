package davenkin.opinions.persistence.jdbc.dao;

import davenkin.opinions.domain.Comment;
import davenkin.opinions.domain.DataAccessException;
import davenkin.opinions.persistence.dao.CommentDao;
import davenkin.opinions.persistence.jdbc.mapper.CommentRowMapper;

import javax.sql.DataSource;
import java.util.List;

public class JdbcCommentDao extends BaseJdbcDao implements CommentDao
{

    public JdbcCommentDao(DataSource dataSource)
    {
        super(dataSource);
    }

    public List<Comment> findCommentsForSurvey(long surveyId) throws DataAccessException
    {
        return jdbcTemplate.queryForList("SELECT * FROM COMMENT WHERE SURVEY_ID = ?", new Object[]{surveyId}, new CommentRowMapper(dataSource));
    }

    public List<Comment> findCommentsFromUser(long userId) throws DataAccessException
    {
        return jdbcTemplate.queryForList("SELECT * FROM COMMENT WHERE USER_ID = ?", new Object[]{userId}, new CommentRowMapper(dataSource));
    }

    public void removeComment(long commentId) throws DataAccessException
    {
        jdbcTemplate.update("DELETE FROM COMMENT WHERE ID = ?", new Object[]{commentId});
    }

    public void addCommentForSurvey(long surveyId, long userId, String content) throws DataAccessException
    {
        jdbcTemplate.update("INSERT INTO COMMENT (CONTENT, SURVEY_ID, USER_ID) VALUES (?, ?, ?)", new Object[]{content, surveyId, userId});
    }

}
