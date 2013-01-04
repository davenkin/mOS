package davenkin.opinions.persistence.dao.jdbc;

import davenkin.opinions.domain.Option;
import davenkin.opinions.persistence.DataAccessException;
import davenkin.opinions.persistence.dao.OptionDao;
import davenkin.opinions.persistence.dao.jdbc.mapper.SurveyOptionRowMapper;

import javax.sql.DataSource;
import java.util.List;

public class JdbcOptionDao extends AbstractJdbcDao implements OptionDao
{
    public JdbcOptionDao(DataSource dataSource)
    {
        super(dataSource);
    }

    public List<Option> findOptionsForSurvey(Long surveyId)
    {
        try
        {
            return jdbcTemplate.queryForList("SELECT * FROM SURVEY_OPTION_COUNT WHERE SURVEY_ID = ?", new Object[]{surveyId}, new SurveyOptionRowMapper());
        } catch (DataAccessException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


}
