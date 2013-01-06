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

    public List<Option> findOptionsForSurvey(Long surveyId) throws DataAccessException
    {
        return jdbcTemplate.queryForList("SELECT * FROM SURVEY_OPTION WHERE SURVEY_ID = ?", new Object[]{surveyId}, new SurveyOptionRowMapper());
    }

    @Override
    public void increaseOptionCount(Long id) throws DataAccessException
    {
        Option option = findOption(id);
        if (option != null)
        {
            Long count = option.getCount();
            count++;
            jdbcTemplate.update("UPDATE SURVEY_OPTION SET COUNT = ? WHERE ID = ?", new Object[]{count, id});
        }
    }

    @Override
    public Option findOption(Long id) throws DataAccessException
    {
        List<Option> options = jdbcTemplate.queryForList("SELECT * FROM SURVEY_OPTION WHERE ID = ?", new Object[]{id}, new SurveyOptionRowMapper());
        if (options.size() > 0)
            return options.get(0);
        return null;
    }


}
