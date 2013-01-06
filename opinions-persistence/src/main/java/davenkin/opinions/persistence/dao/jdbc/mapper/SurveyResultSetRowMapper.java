package davenkin.opinions.persistence.dao.jdbc.mapper;

import davenkin.opinions.domain.CategoryEnum;
import davenkin.opinions.domain.Survey;
import davenkin.opinions.persistence.DataAccessException;
import davenkin.opinions.persistence.dao.CommentDao;
import davenkin.opinions.persistence.dao.OptionDao;
import davenkin.opinions.persistence.dao.TagDao;
import davenkin.opinions.persistence.dao.UserDao;
import davenkin.opinions.persistence.dao.jdbc.JdbcCommentDao;
import davenkin.opinions.persistence.dao.jdbc.JdbcOptionDao;
import davenkin.opinions.persistence.dao.jdbc.JdbcTagDao;
import davenkin.opinions.persistence.dao.jdbc.JdbcUserDao;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SurveyResultSetRowMapper implements JdbcResultSetRowMapper<Survey>
{

    private CommentDao commentDao;
    private UserDao userDao;
    private OptionDao optionDao;
    private TagDao tagDao;
    private static Map<String, CategoryEnum> categoryEnumMap = new HashMap<String, CategoryEnum>();
    static {
        categoryEnumMap.put("Science", CategoryEnum.SCIENCE);
        categoryEnumMap.put("Economy", CategoryEnum.ECONOMY);
        categoryEnumMap.put("Culture", CategoryEnum.CULTURE);
        categoryEnumMap.put("Politics", CategoryEnum.POLITICS);
        categoryEnumMap.put("Education", CategoryEnum.EDUCATION);
    }

    public SurveyResultSetRowMapper(DataSource dataSource)
    {
        commentDao = new JdbcCommentDao(dataSource);
        userDao = new JdbcUserDao(dataSource);
        optionDao = new JdbcOptionDao(dataSource);
        tagDao = new JdbcTagDao(dataSource);
    }

    public Survey map(ResultSet rs) throws SQLException
    {
        long id = rs.getLong("ID");
        Survey survey = new Survey(id);
        survey.setCreatedTime(rs.getTimestamp("CREATED_TIME"));
        survey.setContent(rs.getString("CONTENT"));
        survey.setSurveyCategory(categoryEnumMap.get(rs.getString("CATEGORY_NAME")));
        survey.setCanMultipleChecked(rs.getString("MUL_OPT").equals("Y"));
        try
        {
            survey.setCreatingUser(userDao.findUserById(rs.getLong("USER_ID")));
        } catch (DataAccessException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        try
        {
            survey.setOptions(optionDao.findOptionsForSurvey(id));
        } catch (DataAccessException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        try
        {
            survey.setComments(commentDao.findCommentsForSurvey(id));
        } catch (DataAccessException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        try
        {
            survey.setSurveyTags(tagDao.findTagsForSurvey(id));
        } catch (DataAccessException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return survey;
    }
}
