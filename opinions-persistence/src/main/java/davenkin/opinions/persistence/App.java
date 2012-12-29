package davenkin.opinions.persistence;

import org.apache.commons.dbcp.BasicDataSource;

import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:mysql://localhost/OPINIONS");
        dataSource.setMaxActive(3);
        dataSource.setMaxIdle(2);
        dataSource.setInitialSize(2);
        JdbcSurveyDao jdbcSurveyDao = new JdbcSurveyDao(dataSource);
        for(int index =0; index<6;index++)
        {
        jdbcSurveyDao.findUserById(2L);
        }

        List<Map<String,Object>> mapList = jdbcSurveyDao.queryForListOfMap("SELECT * FROM USER WHERE NAME = ?", "tom");
        System.out.println(mapList.size());


    }
}
