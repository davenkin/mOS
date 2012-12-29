package davenkin.opinions.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface JdbcResultSetRowMapper<T>
{
    public T map(ResultSet rs) throws SQLException;
}
