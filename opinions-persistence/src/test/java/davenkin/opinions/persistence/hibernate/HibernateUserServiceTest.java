package davenkin.opinions.persistence.hibernate;

import davenkin.opinions.domain.User;
import davenkin.opinions.persistence.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

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
@ContextConfiguration(locations = {"classpath:testHibernateApplicationContext.xml"})
@TestExecutionListeners({DirtiesContextTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,TransactionalTestExecutionListener.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TransactionConfiguration(transactionManager = "transactionManager")
@Transactional
public class HibernateUserServiceTest {

    @Autowired
    public UserService userService;

    @Test
    public void addNewUser() {
        long id = userService.addNewUser("davenkin", "davenkin@163.com", "123456");
        assertTrue(1L == id);
        User userById = userService.getUserById(id);
        assertEquals("davenkin", userById.getName());
    }

    @Test
    public void getUserById() {
        long id = userService.addNewUser("eudy", "davenkin@163.com", "123456");
        User userById = userService.getUserById(id);
        assertEquals("eudy", userById.getName());
        assertEquals("davenkin@163.com", userById.getEmail());
    }

    @Test
    public void updateUserName() {
        long id = userService.addNewUser("oldName", "email", "password");
        userService.updateUserName(id, "newName");
        User userById = userService.getUserById(id);
        assertEquals("newName", userById.getName());
    }

    @Test
    public void updateUserEmail() {
        long id = userService.addNewUser("name", "oldEmail", "password");
        userService.updateUserEmail(id, "newEmail");
        User userById = userService.getUserById(id);
        assertEquals("newEmail", userById.getEmail());
    }

    @Test
    public void updateUserPassword() {
        long id = userService.addNewUser("name", "email", "oldPassword");
        userService.updateUserPassword(id, "newPassword");
        User userById = userService.getUserById(id);
        assertEquals(DigestUtils.md5Hex("newPassword"), userById.getPassword());
    }

    @Test
    public void updateUser() {
        long id = userService.addNewUser("oldName", "oldEmail", "oldPassword");
        userService.updateUser(id, "newName", "newEmail", "newPassword");
        User userById = userService.getUserById(id);

        assertEquals("newName", userById.getName());
        assertEquals("newEmail", userById.getEmail());
        assertEquals(DigestUtils.md5Hex("newPassword"), userById.getPassword());

    }

}
