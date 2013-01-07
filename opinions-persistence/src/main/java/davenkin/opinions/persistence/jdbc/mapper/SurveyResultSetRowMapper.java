package davenkin.opinions.persistence.jdbc.mapper;

import davenkin.opinions.domain.Category;
import davenkin.opinions.domain.Survey;
import davenkin.opinions.domain.DataAccessException;
import davenkin.opinions.persistence.dao.CommentDao;
import davenkin.opinions.persistence.dao.OptionDao;
import davenkin.opinions.persistence.dao.TagDao;
import davenkin.opinions.persistence.dao.UserDao;
import davenkin.opinions.persistence.jdbc.dao.JdbcCommentDao;
import davenkin.opinions.persistence.jdbc.dao.JdbcOptionDao;
import davenkin.opinions.persistence.jdbc.dao.JdbcTagDao;
import davenkin.opinions.persistence.jdbc.dao.JdbcUserDao;

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
    private static Map<String, Category> categoryEnumMap = new HashMap<String, Category>();

    static
    {
        categoryEnumMap.put("Science", Category.SCIENCE);
        categoryEnumMap.put("Economy", Category.ECONOMY);
        categoryEnumMap.put("Culture", Category.CULTURE);
        categoryEnumMap.put("Politics", Category.POLITICS);
        categoryEnumMap.put("Education", Category.EDUCATION);
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
            survey.setComments(commentDao.findCommentsForSurvey(id));
            survey.setOptions(optionDao.findOptionsForSurvey(id));
            survey.setSurveyTags(tagDao.findTagsForSurvey(id));
        } catch (DataAccessException e)
        {
            e.printStackTrace();
            return null;
        }

        return survey;
    }
}
