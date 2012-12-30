package davenkin.opinions.persistence.dao;

import davenkin.opinions.domain.Survey;
import davenkin.opinions.persistence.DataAccessException;
import davenkin.opinions.persistence.mapper.SurveyResultSetRowMapper;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 12/26/12
 * Time: 10:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class JdbcSurveyDao extends AbstractDao implements SurveyDao
{

    private JdbcUserDao jdbcUserDao;

    public JdbcSurveyDao(DataSource dataSource)
    {
        super(dataSource);
        jdbcUserDao = new JdbcUserDao(dataSource);
    }

    public Survey findSurveyById(Long surveyId)
    {
        try
        {
            List<Survey> surveys = jdbcTemplate.queryForList("SELECT SURVEY.*, CATEGORY.NAME AS CATEGORY_NAME FROM SURVEY LEFT JOIN CATEGORY ON SURVEY.CATEGORY_ID = CATEGORY.ID WHERE SURVEY.ID = ?", new Object[]{surveyId}, new SurveyResultSetRowMapper(dataSource));
            if (surveys.size() > 0)
            {
                return surveys.get(0);
            }
        } catch (DataAccessException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Survey> findAllSurveys()
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Survey> findSurveysCreatedByUser(Long userId)
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Survey> findSurveysByCategory(String category)
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Survey> findSurveysByTag(String tag)
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Survey> findSurveysCreatedBetween(Date fromDate, Date toDate)
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    public String findSurveyTagById(Long tagId)
    {
        try
        {
            return jdbcTemplate.queryForObject("SELECT NAME FROM TAG WHERE ID = ?", new Object[]{tagId}, String.class);
        } catch (DataAccessException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    public String findCategoryById(Long catId)
    {
        try
        {
            return jdbcTemplate.queryForObject("SELECT NAME FROM CATEGORY WHERE ID = ?", new Object[]{catId}, String.class);
        } catch (DataAccessException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void takeSurvey(Long surveyId, Long optionId)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void removeSurvey(Long surveyId)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}