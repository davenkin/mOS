package davenkin.opinions.persistence.dao.jdbc;

import davenkin.opinions.domain.Comment;
import davenkin.opinions.persistence.DataAccessException;
import davenkin.opinions.persistence.dao.CommentDao;
import davenkin.opinions.persistence.dao.jdbc.mapper.CommentRowMapper;

import javax.sql.DataSource;
import java.util.List;

public class JdbcCommentDao extends AbstractJdbcDao implements CommentDao
{

    public JdbcCommentDao(DataSource dataSource)
    {
        super(dataSource);
    }

    public List<Comment> findCommentsForSurvey(Long surveyId) throws DataAccessException
    {
        return jdbcTemplate.queryForList("SELECT * FROM COMMENT WHERE SURVEY_ID = ?", new Object[]{surveyId}, new CommentRowMapper(dataSource));
    }

    public List<Comment> findCommentsFromUser(Long userId) throws DataAccessException
    {
        return jdbcTemplate.queryForList("SELECT * FROM COMMENT WHERE USER_ID = ?", new Object[]{userId}, new CommentRowMapper(dataSource));
    }

    public void removeComment(Long commentId) throws DataAccessException
    {
        jdbcTemplate.update("DELETE FROM COMMENT WHERE ID = ?", new Object[]{commentId});
    }

    @Override
    public void addCommentForSurvey(Long suveyId, Long userId, String content) throws DataAccessException
    {
        jdbcTemplate.update("INSERT INTO COMMENT (CONTENT, SURVEY_ID, USER_ID) VALUES (?, ?, ?)", new Object[]{content, suveyId, userId});
    }

}
