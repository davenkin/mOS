package davenkin.opinions.persistence.hibernate;

import davenkin.opinions.domain.User;
import davenkin.opinions.persistence.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 3/20/13
 * Time: 10:27 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:testHibernateApplicationContext.xml"})
@TestExecutionListeners({DirtiesContextTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class HibernateUserServiceTest {

    @Autowired
    public UserService userService;

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Test
    public void addNewUser(){
        long id = userService.addNewUser("davenkin", "davenkin@163.com", "123456");
        assertTrue(1L==id);
        assertEquals(1, getDbRecordCount());
        assertEquals("davenkin", getNameFromDB());
        assertEquals(DigestUtils.md5Hex("123456"), jdbcTemplate.queryForObject("SELECT PASSWORD FROM USER", String.class));
    }

    @Test
    public void getUserById(){
        long id = userService.addNewUser("eudy", "davenkin@163.com", "123456");
        User userById = userService.getUserById(id);
        assertEquals("eudy",userById.getName());
        assertEquals("davenkin@163.com",userById.getEmail());
    }

    @Test
    public void updateUserName(){
        long id = userService.addNewUser("oldName", "email", "password");
        userService.updateUserName(id,"newName");
        User userById = userService.getUserById(id);
        assertEquals("newName",userById.getName());
    }


    private String getNameFromDB() {
        return jdbcTemplate.queryForObject("SELECT NAME FROM USER", String.class);
    }

    private int getDbRecordCount() {
        return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM USER");
    }

}
