package davenkin.opinions.persistence;

import org.apache.commons.dbcp.BasicDataSource;

public class DataSourceUtil
{
    public static BasicDataSource createDataSource()
    {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:mysql://localhost/OPINIONS");
        dataSource.setMaxActive(3);
        dataSource.setMaxIdle(2);
        dataSource.setInitialSize(2);
        return dataSource;
    }
}
