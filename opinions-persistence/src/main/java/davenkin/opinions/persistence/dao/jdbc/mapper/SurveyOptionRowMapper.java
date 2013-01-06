package davenkin.opinions.persistence.dao.jdbc.mapper;

import davenkin.opinions.domain.Option;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SurveyOptionRowMapper implements JdbcResultSetRowMapper<Option>
{
    public Option map(ResultSet rs) throws SQLException
    {
        Option option = new Option(rs.getLong("ID"));
        option.setCount(rs.getLong("COUNT"));
        option.setOption(rs.getString("CONTENT"));
        option.setSurveyId(rs.getLong("SURVEY_ID"));
        return option;
    }
}
