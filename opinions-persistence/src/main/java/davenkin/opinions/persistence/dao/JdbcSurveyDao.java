package davenkin.opinions.persistence.dao;

import davenkin.opinions.domain.Survey;
import davenkin.opinions.persistence.DataAccessException;

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

    public List<String> findTagsForSurvey(Long surveyId)
    {
        try
        {
            List<Integer> tagIds = jdbcTemplate.queryForList("SELECT TAG_ID FROM SURVEY_TAG WHERE SURVEY_ID = ?", new Object[]{surveyId}, Integer.class);
            ArrayList<String> strings = new ArrayList<String>();
            for (Integer anInt : tagIds)
            {
                strings.add(jdbcTemplate.queryForObject("SELECT NAME FROM TAG WHERE ID = ?", new Object[]{anInt}, String.class));
            }
            return strings;
        } catch (DataAccessException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
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