package davenkin.opinions.persistence.dao.jdbc.mapper;

import davenkin.opinions.domain.SurveyOption;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SurveyOptionRowMapper implements JdbcResultSetRowMapper<SurveyOption>
{
    public SurveyOption map(ResultSet rs) throws SQLException
    {
        SurveyOption surveyOption = new SurveyOption(rs.getLong("ID"));
        surveyOption.setCount(rs.getLong("OPTION_COUNT"));
        surveyOption.setOption(rs.getString("SURVEY_OPTION"));
        surveyOption.setSurveyId(rs.getLong("SURVEY_ID"));
        return surveyOption;
    }
}
