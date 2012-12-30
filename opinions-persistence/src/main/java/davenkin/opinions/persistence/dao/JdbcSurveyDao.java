package davenkin.opinions.persistence.dao;

import davenkin.opinions.domain.Survey;
import davenkin.opinions.domain.SurveyComment;
import davenkin.opinions.domain.SurveyOption;
import davenkin.opinions.domain.User;
import davenkin.opinions.persistence.*;
import org.apache.log4j.Logger;

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
public class JdbcSurveyDao implements SurveyDao
{
    private DataSource dataSource;
    private DavenkinJdbcTemplate jdbcTemplate;
    private final Logger logger = Logger.getLogger(this.getClass());


    public JdbcSurveyDao(DataSource dataSource)
    {
        this.dataSource = dataSource;
        jdbcTemplate = new DavenkinJdbcTemplate(dataSource);
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

    public List<SurveyComment> findCommentsForSurvey(Long surveyId)
    {
        try
        {
            return jdbcTemplate.queryForList("SELECT * FROM COMMENT WHERE SURVEY_ID = ?", new Object[]{surveyId}, new CommentRowMapper(this));
        } catch (DataAccessException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public User findUserById(Long userId)
    {
        try
        {
            List<User> users = jdbcTemplate.queryForList("SELECT * FROM USER WHERE ID = ?", new Object[]{userId}, new UserRowMapper());
            if (users.size() > 0)
            {
                return users.get(0);
            }
        } catch (DataAccessException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;

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

    public void removeComment(Long commentId)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}