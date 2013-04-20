package davenkin.opinions.persistence.hibernate;

import davenkin.opinions.domain.User;
import davenkin.opinions.persistence.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
        DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class HibernateUserServiceTest {

    @Autowired
    public UserService userService;

    @Autowired
    public SessionFactory sessionFactory;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void addUser() {
        User user = new User("name", "email", "PASSWORD");
        userService.addUser(user);
        Query sqlQuery = sessionFactory.getCurrentSession().createQuery("from User");
        User userFromDb = (User) sqlQuery.uniqueResult();
        assertThat(userFromDb.getName(), is("name"));
        assertThat(userFromDb.getEmail(), is("email"));
    }

    @Test
    public void getUserById() {
        User user = new User("name", "email", "PASSWORD");
        long userId = userService.addUser(user);
        User userById = userService.getUserById(userId);
        assertEquals("name", userById.getName());
        assertEquals("email", userById.getEmail());
    }


    @Test
    public void findUserByName() {
        User user = new User("name", "email", "PASSWORD");
        userService.addUser(user);
        User userByName = userService.getUserByName("name");
        assertEquals("name", userByName.getName());
        assertEquals("email", userByName.getEmail());
    }

    @Test
    public void findUserByEmail() {
        User user = new User("name", "email", "PASSWORD");
        userService.addUser(user);
        User userByEmail = userService.getUserByEmail("email");
        assertEquals("name", userByEmail.getName());
        assertEquals("email", userByEmail.getEmail());
    }


    @Test
    public void twoUsersShouldNotWithTheSameName() {
        thrown.expect(DataIntegrityViolationException.class);
        User user = new User("name", "email", "PASSWORD");
        userService.addUser(user);
        User anotherUserWithSameName = new User("name", "anotherEmail", "anotherPassword");
        userService.addUser(anotherUserWithSameName);
    }

    @Test
    public void twoUsersShouldNotWithTheSameEmail() {
        thrown.expect(DataIntegrityViolationException.class);
        User user = new User("name", "email", "PASSWORD");
        userService.addUser(user);
        User anotherUserWithSameName = new User("anotherName", "email", "anotherPassword");
        userService.addUser(anotherUserWithSameName);
    }

    @Test
    public void updateUserName() {
        User user = new User("oldName", "email", "PASSWORD");
        long userId = userService.addUser(user);
        userService.updateUserName(userId, "newName");
        User userById = userService.getUserById(userId);
        assertEquals("newName", userById.getName());
    }

    @Test
    public void updateUserEmail() {
        User user = new User("name", "oldEmail", "PASSWORD");
        long userId = userService.addUser(user);
        userService.updateUserEmail(userId, "newEmail");
        User userById = userService.getUserById(userId);
        assertEquals("newEmail", userById.getEmail());
    }

    @Test
    public void updateUserPassword() {
        User user = new User("name", "oldEmail", "PASSWORD");
        long userId = userService.addUser(user);
        userService.updateUserPassword(userId, "newPassword");
        User userById = userService.getUserById(userId);
        assertEquals(DigestUtils.md5Hex("newPassword"), userById.getPassword());
    }

    @Test
    public void updateUser() {
        User user = new User("oldName", "oldEmail", "oldPassword");
        long userId = userService.addUser(user);
        userService.updateUser(userId, "newName", "newEmail", "newPassword");
        User userById = userService.getUserById(userId);

        assertEquals("newName", userById.getName());
        assertEquals("newEmail", userById.getEmail());
        assertEquals(DigestUtils.md5Hex("newPassword"), userById.getPassword());
    }

}
