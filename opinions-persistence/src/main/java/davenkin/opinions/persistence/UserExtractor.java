package davenkin.opinions.persistence;

import davenkin.opinions.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserExtractor implements JdbcResultSetExtractor<User>
{
    public User extract(ResultSet rs) throws SQLException
    {
        while (rs.next())
        {
            User user = new User(rs.getLong("ID"));
            user.setName(rs.getString("NAME"));
            user.setEmail(rs.getString("EMAIL"));
            user.setPassword(rs.getString("PASSWORD"));
            user.setRegisterTime(rs.getTimestamp("REGISTER_TIME"));
            return user;
        }
        return null;
    }
}
