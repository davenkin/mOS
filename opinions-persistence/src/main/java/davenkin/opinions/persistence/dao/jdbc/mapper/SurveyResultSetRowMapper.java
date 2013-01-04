package davenkin.opinions.persistence.dao.jdbc.mapper;

import davenkin.opinions.domain.Survey;
import davenkin.opinions.persistence.dao.*;
import davenkin.opinions.persistence.dao.jdbc.JdbcCommentDao;
import davenkin.opinions.persistence.dao.jdbc.JdbcSurveyOptionDao;
import davenkin.opinions.persistence.dao.jdbc.JdbcSurveyTagDao;
import davenkin.opinions.persistence.dao.jdbc.JdbcUserDao;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SurveyResultSetRowMapper implements JdbcResultSetRowMapper<Survey>
{

    private CommentDao commentDao;
    private UserDao userDao;
    private SurveyOptionDao surveyOptionDao;
    private SurveyTagDao surveyTagDao;

    public SurveyResultSetRowMapper(DataSource dataSource)
    {
        commentDao = new JdbcCommentDao(dataSource);
        userDao = new JdbcUserDao(dataSource);
        surveyOptionDao = new JdbcSurveyOptionDao(dataSource);
        surveyTagDao = new JdbcSurveyTagDao(dataSource);
    }

    public Survey map(ResultSet rs) throws SQLException
    {
        long id = rs.getLong("ID");
        Survey survey = new Survey(id);
        survey.setCreatedTime(rs.getTimestamp("CREATED_TIME"));
        survey.setContent(rs.getString("CONTENT"));
        survey.setSurveyCategory(rs.getString("CATEGORY_NAME"));
        survey.setCanMultipleChecked(rs.getString("IS_MULTI_OPTIONS").equals("Y"));
        survey.setCreatingUser(userDao.findUserById(rs.getLong("USER_ID")));
        survey.setSurveyOptions(surveyOptionDao.findOptionsForSurvey(id));
        survey.setSurveyComments(commentDao.findCommentsForSurvey(id));
        survey.setSurveyTags(surveyTagDao.findTagsForSurvey(id));

        return survey;
    }
}
