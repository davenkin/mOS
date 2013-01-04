package davenkin.opinions.persistence.dao.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface JdbcResultSetRowMapper<T>
{
    public T map(ResultSet rs) throws SQLException;
}
