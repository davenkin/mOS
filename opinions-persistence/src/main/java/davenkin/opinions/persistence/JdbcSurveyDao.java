package davenkin.opinions.persistence;

import davenkin.opinions.domain.Survey;
import davenkin.opinions.domain.SurveyComment;
import davenkin.opinions.domain.SurveyOption;
import davenkin.opinions.domain.User;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
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
    private final Logger logger = Logger.getLogger(this.getClass());
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    public JdbcSurveyDao(DataSource dataSource)
    {
        this.dataSource = dataSource;
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
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<SurveyOption> findOptionsForSurvey(Long surveyId)
    {
        try
        {
            ResultSet rs = queryForResultSet("SELECT * FROM SURVEY_OPTION_COUNT WHERE SURVEY_ID = ?", surveyId);
            return createSurveyOptions(rs);
        } catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (Exception e)
        {
            logger.error("Cannot load options for survey[" + surveyId + "].");
        } finally
        {
            closeResources();
        }

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private List<SurveyOption> createSurveyOptions(ResultSet rs) throws SQLException
    {
        List<SurveyOption> surveyOptions = new ArrayList<SurveyOption>();
        while (rs.next())
        {
            SurveyOption surveyOption = new SurveyOption(rs.getLong("ID"));
            surveyOption.setCount(rs.getLong("OPTION_COUNT"));
            String option = rs.getString("SURVEY_OPTION");
            surveyOption.setOption(option);
            long surveyId = rs.getLong("SURVEY_ID");
            surveyOption.setSurveyId(surveyId);
            surveyOptions.add(surveyOption);
            logger.info("Added option <" + option + "> for survey[" + surveyId + "].");
        }
        return surveyOptions;
    }

    public List<SurveyComment> findCommentsForSurvey(Long surveyId)
    {

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public User findUserById(Long userId)
    {
        try
        {
            ResultSet resultSet = queryForResultSet("SELECT * FROM USER WHERE ID = ?", userId);
            User user = createUser(resultSet);
            return user;

        } catch (SQLException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (Exception e)
        {
            logger.error("Cannot find user[" + userId + "]");
        } finally
        {
            closeResources();

        }

        return null;
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

    private ResultSet queryForResultSet(String sql, Object... objects) throws SQLException
    {
        connection = dataSource.getConnection();
        logger.info("Use connection: " + connection.hashCode());
        preparedStatement = connection.prepareStatement(sql);
        populatePreparedStatement(objects);
        logger.info(preparedStatement.toString());
        resultSet = preparedStatement.executeQuery();
        return resultSet;

    }

    private void populatePreparedStatement(Object... objects) throws SQLException
    {
        int index = 1;
        for (Object object : objects)
        {
            preparedStatement.setObject(index, object);
            index++;
        }
    }

    private User createUser(ResultSet resultSet) throws SQLException
    {
        User user = null;
        while (resultSet.next())
        {
            long id = resultSet.getLong("ID");
            String name = resultSet.getString("NAME");
            String email = resultSet.getString("EMAIL");
            String password = resultSet.getString("PASSWORD");
            Timestamp register_time = resultSet.getTimestamp("REGISTER_TIME");
            user = new User(id);
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setRegisterTime(register_time);
            logger.info("Load User: ID = " + id + ", Name = " + name);
        }
        return user;
    }

    private void closeResources()
    {
        try
        {
            if (resultSet != null)
            {
                resultSet.close();
            }
            if (preparedStatement != null)
            {
                preparedStatement.close();
            }
            if (connection != null)
            {
                connection.close();
            }
        } catch (Exception e)
        {
            logger.warn("Not able to close database resources");
            e.printStackTrace();
        }

    }


}
