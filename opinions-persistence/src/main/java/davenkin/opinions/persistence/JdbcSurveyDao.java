package davenkin.opinions.persistence;

import davenkin.opinions.domain.Survey;
import davenkin.opinions.domain.SurveyComment;
import davenkin.opinions.domain.SurveyOption;
import davenkin.opinions.domain.User;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 12/26/12
 * Time: 10:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class JdbcSurveyDao implements SurveyDao {
    private DataSource dataSource;
    private final Logger logger = Logger.getLogger(this.getClass());
    ;

    public JdbcSurveyDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Survey findSurveyById(Long surveyId) {

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Survey> findAllSurveys() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Survey> findSurveysCreatedByUser(Long userId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Survey> findSurveysByCategory(String category) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Survey> findSurveysByTag(String tag) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Survey> findSurveysCreatedBetween(Date fromDate, Date toDate) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<String> findTagsForSurvey(Long surveyId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<SurveyOption> findOptionsForSurvey(Long surveyId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<SurveyComment> findCommentsForSurvey(Long surveyId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public User findUserById(Long userId) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            logger.info("Use connection: " + connection.hashCode());
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM USER WHERE ID = ?");
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            while (resultSet.next()) {
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

        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void takeSurvey(Long surveyId, Long optionId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void removeSurvey(Long surveyId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void removeComment(Long commentId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
