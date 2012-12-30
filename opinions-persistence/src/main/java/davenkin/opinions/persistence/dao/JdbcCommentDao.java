package davenkin.opinions.persistence.dao;

import davenkin.opinions.domain.SurveyComment;
import davenkin.opinions.persistence.DataAccessException;
import davenkin.opinions.persistence.mapper.CommentRowMapper;

import javax.sql.DataSource;
import java.util.List;

public class JdbcCommentDao extends AbstractDao implements CommentDao
{
    private JdbcUserDao jdbcUserDao;

    public JdbcCommentDao(DataSource dataSource)
    {
        super(dataSource);
        jdbcUserDao = new JdbcUserDao(dataSource);
    }

    public List<SurveyComment> findCommentsForSurvey(Long surveyId)
    {
        try
        {
            return jdbcTemplate.queryForList("SELECT * FROM COMMENT WHERE SURVEY_ID = ?", new Object[]{surveyId}, new CommentRowMapper(jdbcUserDao));
        } catch (DataAccessException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<SurveyComment> findCommentsFromUser(Long userId)
    {
        try
        {
            return jdbcTemplate.queryForList("SELECT * FROM COMMENT WHERE USER_ID = ?", new Object[]{userId},new CommentRowMapper(jdbcUserDao));
        } catch (DataAccessException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
