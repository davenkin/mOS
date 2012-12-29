package davenkin.opinions.persistence;

import davenkin.opinions.domain.SurveyComment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentRowMapper implements JdbcResultSetRowMapper<SurveyComment>
{
    public SurveyComment map(ResultSet rs) throws SQLException
    {
        SurveyComment surveyComment = new SurveyComment(rs.getLong("ID"));
        surveyComment.setContent(rs.getString("CONTENT"));
        surveyComment.setCreatedTime(rs.getTimestamp("CREATED_TIME"));
        surveyComment.setSurveyId(rs.getLong("SURVEY_ID"));
        surveyComment.setUserId(rs.getLong("USER_ID"));
        return surveyComment;
    }
}
