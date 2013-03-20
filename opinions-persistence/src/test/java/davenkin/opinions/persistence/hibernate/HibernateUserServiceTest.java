package davenkin.opinions.persistence.hibernate;

import davenkin.opinions.persistence.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 3/20/13
 * Time: 10:27 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:testHibernateApplicationContext.xml"})
public class HibernateUserServiceTest {

    @Autowired
    public UserService userService;

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Test
    public void test(){
        userService.addNewUser("davenkin","davenkin@163.com","123456");
        assertEquals(1, jdbcTemplate.queryForInt("SELECT COUNT(*) FROM USER"));
    }

}
