package davenkin.opinions.persistence.jdbc.callback;

import davenkin.opinions.persistence.jdbc.mapper.JdbcResultSetRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RowMapperCallBack implements ResultSetCallBack
{
    private List list;
    private JdbcResultSetRowMapper mapper;

    public RowMapperCallBack(List list, JdbcResultSetRowMapper mapper)
    {
        this.list = list;
        this.mapper = mapper;
    }

    public void callBack(ResultSet resultSet) throws SQLException
    {

        while (resultSet.next())
        {
            list.add(mapper.map(resultSet));
        }
    }
}
