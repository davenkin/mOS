package davenkin.opinions.persistence.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetCallBack
{
    public void callBack(ResultSet resultSet) throws SQLException;
}
