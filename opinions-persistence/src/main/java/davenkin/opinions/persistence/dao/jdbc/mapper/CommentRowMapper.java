package davenkin.opinions.persistence.dao.jdbc.mapper;

import davenkin.opinions.domain.Comment;
import davenkin.opinions.persistence.dao.jdbc.JdbcUserDao;

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
        comment.setUser(jdbcUserDao.findUserById(rs.getLong("USER_ID")));
        return comment;
    }
}
