package davenkin.opinions.persistence;

import davenkin.opinions.domain.Survey;
import davenkin.opinions.domain.SurveyComment;
import davenkin.opinions.domain.SurveyOption;
import davenkin.opinions.domain.User;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Map;

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
    ;

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
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<SurveyComment> findCommentsForSurvey(Long surveyId)
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public User findUserById(Long userId)
    {
//        return queryForListOfMapS(userId, null);
        return null;
    }

    private List<Map> queryForListOfMapS(Long userId, PreparedStatement preparedStatement)
    {
        Connection connection = null;
        ResultSet resultSet = null;
        try
        {
            connection = dataSource.getConnection();
            logger.info("Use connection: " + connection.hashCode());
            preparedStatement = connection.prepareStatement("SELECT * FROM USER WHERE ID = ?");
            preparedStatement.setLong(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
//                return createUser(resultSet);
            }

            closeResources(connection, resultSet, preparedStatement);
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    private User createUser(ResultSet resultSet) throws SQLException
    {
        long id = resultSet.getLong("ID");
        String name = resultSet.getString("NAME");
        String email = resultSet.getString("EMAIL");
        String password = resultSet.getString("PASSWORD");
        Timestamp register_time = resultSet.getTimestamp("REGISTER_TIME");
        User user = new User(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRegisterTime(register_time);
        logger.info("Load User: ID = " + id + ", Name = " + name);
        return user;
    }

    private void closeResources(Connection connection, ResultSet resultSet, PreparedStatement preparedStatement) throws SQLException
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
