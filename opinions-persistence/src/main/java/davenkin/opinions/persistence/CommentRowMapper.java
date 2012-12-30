package davenkin.opinions.persistence;

import davenkin.opinions.domain.SurveyComment;
import davenkin.opinions.persistence.dao.JdbcSurveyDao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentRowMapper implements JdbcResultSetRowMapper<SurveyComment>
{
    private JdbcSurveyDao jdbcSurveyDao;

    public CommentRowMapper(JdbcSurveyDao jdbcSurveyDao)
    {
        super();
        this.jdbcSurveyDao = jdbcSurveyDao;
    }

    public SurveyComment map(ResultSet rs) throws SQLException
    {
        SurveyComment surveyComment = new SurveyComment(rs.getLong("ID"));
        surveyComment.setContent(rs.getString("CONTENT"));
        surveyComment.setCreatedTime(rs.getTimestamp("CREATED_TIME"));
        surveyComment.setSurveyId(rs.getLong("SURVEY_ID"));
        surveyComment.setUser(jdbcSurveyDao.findUserById(rs.getLong("USER_ID")));
        return surveyComment;
    }
}
