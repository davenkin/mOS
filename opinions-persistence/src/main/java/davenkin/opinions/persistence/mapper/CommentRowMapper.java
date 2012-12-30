package davenkin.opinions.persistence.mapper;

import davenkin.opinions.domain.SurveyComment;
import davenkin.opinions.persistence.dao.JdbcUserDao;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentRowMapper implements JdbcResultSetRowMapper<SurveyComment>
{
    private JdbcUserDao jdbcUserDao;

    public CommentRowMapper(DataSource dataSource)
    {
        super();
        jdbcUserDao = new JdbcUserDao(dataSource);
    }

    public SurveyComment map(ResultSet rs) throws SQLException
    {
        SurveyComment surveyComment = new SurveyComment(rs.getLong("ID"));
        surveyComment.setContent(rs.getString("CONTENT"));
        surveyComment.setCreatedTime(rs.getTimestamp("CREATED_TIME"));
        surveyComment.setSurveyId(rs.getLong("SURVEY_ID"));
        surveyComment.setUser(jdbcUserDao.findUserById(rs.getLong("USER_ID")));
        return surveyComment;
    }
}
