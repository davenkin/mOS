package davenkin.opinions.persistence.jdbc.mapper;

import davenkin.opinions.domain.Comment;
import davenkin.opinions.domain.DataAccessException;
import davenkin.opinions.persistence.jdbc.dao.JdbcUserDao;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentRowMapper implements JdbcResultSetRowMapper<Comment>
{
    private JdbcUserDao jdbcUserDao;

    public CommentRowMapper(DataSource dataSource)
    {
        super();
        jdbcUserDao = new JdbcUserDao(dataSource);
    }

    public Comment map(ResultSet rs) throws SQLException
    {
        Comment comment = new Comment(rs.getLong("ID"));
        comment.setContent(rs.getString("CONTENT"));
        comment.setCreatedTime(rs.getTimestamp("CREATED_TIME"));
        comment.setSurveyId(rs.getLong("SURVEY_ID"));
        try
        {
            comment.setUser(jdbcUserDao.findUserById(rs.getLong("USER_ID")));
        } catch (DataAccessException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return comment;
    }
}
