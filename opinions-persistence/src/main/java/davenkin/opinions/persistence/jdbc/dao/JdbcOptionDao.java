package davenkin.opinions.persistence.jdbc.dao;

import davenkin.opinions.domain.Option;
import davenkin.opinions.domain.DataAccessException;
import davenkin.opinions.persistence.dao.OptionDao;
import davenkin.opinions.persistence.jdbc.mapper.SurveyOptionRowMapper;

import javax.sql.DataSource;
import java.util.List;

public class JdbcOptionDao extends BaseJdbcDao implements OptionDao
{
    public JdbcOptionDao(DataSource dataSource)
    {
        super(dataSource);
    }

    public List<Option> findOptionsForSurvey(long surveyId) throws DataAccessException
    {
        return jdbcTemplate.queryForList("SELECT * FROM SURVEY_OPTION WHERE SURVEY_ID = ?", new Object[]{surveyId}, new SurveyOptionRowMapper());
    }


    public void increaseOptionCount(long id) throws DataAccessException
    {
        Option option = findOption(id);
        if (option != null)
        {
            long count = option.getCount();
            count++;
            jdbcTemplate.update("UPDATE SURVEY_OPTION SET COUNT = ? WHERE ID = ?", new Object[]{count, id});
        }
    }


    public Option findOption(long id) throws DataAccessException
    {
        List<Option> options = jdbcTemplate.queryForList("SELECT * FROM SURVEY_OPTION WHERE ID = ?", new Object[]{id}, new SurveyOptionRowMapper());
        if (options.size() > 0)
            return options.get(0);
        return null;
    }


}
