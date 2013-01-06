package davenkin.opinions.persistence.dao.jdbc.callback;

import davenkin.opinions.persistence.dao.jdbc.callback.ResultSetCallBack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TypeCallBack implements ResultSetCallBack
{
    private List list;
    private Class type;

    public TypeCallBack(List list, Class type)
    {
        this.list = list;
        this.type = type;
    }

    @Override
    public void callBack(ResultSet resultSet) throws SQLException
    {
        while (resultSet.next())
        {
            list.add(type.cast(resultSet.getObject(1)));
        }

    }
}
