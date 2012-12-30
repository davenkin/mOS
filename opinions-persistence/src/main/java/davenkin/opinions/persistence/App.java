package davenkin.opinions.persistence;

import davenkin.opinions.domain.SurveyComment;
import davenkin.opinions.domain.SurveyOption;
import davenkin.opinions.domain.User;
import davenkin.opinions.persistence.dao.*;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.SQLException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException
    {
        System.out.println( "Hello World!" );
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:mysql://localhost/OPINIONS");
        dataSource.setMaxActive(3);
        dataSource.setMaxIdle(2);
        dataSource.setInitialSize(2);

        JdbcSurveyDao jdbcSurveyDao = new JdbcSurveyDao(dataSource);
        JdbcUserDao jdbcUserDao = new JdbcUserDao(dataSource);
        User userById = jdbcUserDao.findUserById(2L);
        System.out.println(userById.getEmail());

        JdbcSurveyOptionDao jdbcSurveyOptionDao = new JdbcSurveyOptionDao(dataSource);
        List<SurveyOption> optionsForSurvey = jdbcSurveyOptionDao.findOptionsForSurvey(3L);
        System.out.println(optionsForSurvey.size());

        String surveyTagById = jdbcSurveyDao.findSurveyTagById(3L);
        System.out.println(surveyTagById);

//        List<String> tagsForSurvey = jdbcSurveyDao.findTagsForSurvey(3L);
//        System.out.println(tagsForSurvey.size());
//        System.out.println(tagsForSurvey.get(0));

        String categoryById = jdbcSurveyDao.findCategoryById(3L);
        System.out.println(categoryById);
        CommentDao commentDao = new JdbcCommentDao(dataSource);

        List<SurveyComment> commentsForSurvey = commentDao.findCommentsForSurvey(1L);
        System.out.println(commentsForSurvey.size());
        System.out.println(commentsForSurvey.get(1).getContent());
        System.out.println(commentsForSurvey.get(1).getUser().getName());


    }

}
