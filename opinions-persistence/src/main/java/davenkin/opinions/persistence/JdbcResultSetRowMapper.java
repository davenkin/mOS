package davenkin.opinions.persistence;

import java.sql.ResultSet;

public interface JdbcResultSetRowMapper<T>
{
    public T map(ResultSet rs);
}
