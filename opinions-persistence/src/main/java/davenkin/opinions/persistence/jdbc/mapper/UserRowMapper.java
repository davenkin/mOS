package davenkin.opinions.persistence.jdbc.mapper;

import davenkin.opinions.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class UserRowMapper implements JdbcResultSetRowMapper<User>
{
    public User map(ResultSet rs) throws SQLException
    {
        String string = rs.getString("NAME");
        String email = rs.getString("EMAIL");
        String password = rs.getString("PASSWORD");
        Timestamp register_time = rs.getTimestamp("REGISTER_TIME");
        User user = new User(string,email,password,register_time);
        return user;
    }
}
