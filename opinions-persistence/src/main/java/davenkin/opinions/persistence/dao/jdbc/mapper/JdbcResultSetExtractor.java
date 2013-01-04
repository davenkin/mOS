package davenkin.opinions.persistence.dao.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface JdbcResultSetExtractor <T>
{
    public T extract(ResultSet rs) throws SQLException;
}
