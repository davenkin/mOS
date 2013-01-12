package davenkin.opinions.persistence.jdbc.dao;

import davenkin.opinions.domain.Category;
import davenkin.opinions.domain.Survey;
import davenkin.opinions.domain.DataAccessException;
import davenkin.opinions.persistence.dao.SurveyDao;
import davenkin.opinions.persistence.jdbc.mapper.SurveyResultSetRowMapper;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 12/26/12
 * Time: 10:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class JdbcSurveyDao extends AbstractJdbcDao implements SurveyDao
{


    public static final String BASIC_SURVEY_QUERY = "SELECT SURVEY.*, CATEGORY.NAME AS CATEGORY_NAME FROM SURVEY LEFT JOIN CATEGORY ON SURVEY.CATEGORY_CODE = CATEGORY.CODE";
    public static final String SURVEY_QUERY_BY_ID = BASIC_SURVEY_QUERY + " WHERE SURVEY.ID = ?";

    public JdbcSurveyDao(DataSource dataSource)
    {
        super(dataSource);
    }

    public Survey findSurveyById(long surveyId) throws DataAccessException
    {
        List<Survey> surveys = jdbcTemplate.queryForList(SURVEY_QUERY_BY_ID, new Object[]{surveyId}, new SurveyResultSetRowMapper(dataSource));
        if (surveys.size() > 0)
        {
            return surveys.get(0);
        }
        return null;
    }

    public List<Survey> findAllSurveys() throws DataAccessException
    {
        return jdbcTemplate.queryForList(BASIC_SURVEY_QUERY, null, new SurveyResultSetRowMapper(dataSource));
    }

    public List<Survey> findSurveysCreatedByUser(long userId) throws DataAccessException
    {
        return jdbcTemplate.queryForList(BASIC_SURVEY_QUERY + " WHERE SURVEY.USER_ID = ?", new Object[]{userId}, new SurveyResultSetRowMapper(dataSource));
    }

    public List<Survey> findSurveysByCategory(Category category) throws DataAccessException
    {
        String code = category.getCode();
        return jdbcTemplate.queryForList(BASIC_SURVEY_QUERY + " WHERE SURVEY.CATEGORY_CODE = ?", new Object[]{code}, new SurveyResultSetRowMapper(dataSource));
    }

    public List<Survey> findSurveysByTag(String tag) throws DataAccessException
    {
        ArrayList<Survey> surveys = new ArrayList<Survey>();
        List<Long> surveyIds = jdbcTemplate.queryForList("SELECT SURVEY_ID FROM SURVEY_TAG WHERE TAG_NAME = ? ", new Object[]{tag}, Long.class);

        for (Long id : surveyIds)
        {
            surveys.add(findSurveyById(id));
        }
        return surveys;
    }

    public List<Survey> findSurveysCreatedBetween(Date fromDate, Date toDate) throws DataAccessException
    {
        return jdbcTemplate.queryForList(BASIC_SURVEY_QUERY + " WHERE SURVEY.CREATED_TIME > ? AND SURVEY.CREATED_TIME < ?", new Object[]{fromDate, toDate}, new SurveyResultSetRowMapper(dataSource));
    }

}