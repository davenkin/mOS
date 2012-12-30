package davenkin.opinions.persistence.dao;

import davenkin.opinions.domain.SurveyOption;
import davenkin.opinions.persistence.DataAccessException;
import davenkin.opinions.persistence.mapper.SurveyOptionRowMapper;

import javax.sql.DataSource;
import java.util.List;

public class JdbcSurveyOptionDao extends AbstractDao implements SurveyOptionDao
{
    public JdbcSurveyOptionDao(DataSource dataSource)
    {
        super(dataSource);
    }

    public List<SurveyOption> findOptionsForSurvey(Long surveyId)
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
