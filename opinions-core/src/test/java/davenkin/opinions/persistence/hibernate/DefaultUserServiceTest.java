package davenkin.opinions.persistence.hibernate;

import davenkin.opinions.domain.User;
import davenkin.opinions.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.HashMap;

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

public class DefaultUserServiceTest extends CommonTestFixture {

    @Autowired
    public UserService userService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void addUser() {
        long userId = userService.addUser(new User("name", "email", "PASSWORD"));

        User userFromDb = getUserFromDb(userId);

        assertThat(userFromDb.getName(), is("name"));
        assertThat(userFromDb.getEmail(), is("email"));
        assertThat(userFromDb.getPassword(), is(DigestUtils.md5Hex("PASSWORD")));

    }

    @Test
    public void getUserById() {
        long userId = userService.addUser(new User("name", "email", "PASSWORD"));

        User userById = userService.getUserById(userId);
        assertEquals("name", userById.getName());
        assertEquals("email", userById.getEmail());
        assertThat(userById.getPassword(), is(DigestUtils.md5Hex("PASSWORD")));
    }


    @Test
    public void findUserByName() {
        userService.addUser(new User("name", "email", "PASSWORD"));

        User userByName = userService.getUserByName("name");

        assertEquals("name", userByName.getName());
        assertEquals("email", userByName.getEmail());
        assertThat(userByName.getPassword(), is(DigestUtils.md5Hex("PASSWORD")));

    }

    @Test
    public void findUserByEmail() {
        userService.addUser(new User("name", "email", "PASSWORD"));

        User userByEmail = userService.getUserByEmail("email");
        assertEquals("name", userByEmail.getName());
        assertEquals("email", userByEmail.getEmail());
        assertThat(userByEmail.getPassword(), is(DigestUtils.md5Hex("PASSWORD")));

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
        long userId = userService.addUser(new User("oldName", "email", "PASSWORD"));

        userService.updateUserName(userId, "newName");

        User userById = getUserFromDb(userId);
        assertEquals("newName", userById.getName());
    }

    @Test
    public void updateUserEmail() {
        long userId = userService.addUser(new User("name", "oldEmail", "PASSWORD"));

        userService.updateUserEmail(userId, "newEmail");

        User userById = getUserFromDb(userId);
        assertEquals("newEmail", userById.getEmail());
    }

    @Test
    public void updateUserPassword() {
        long userId = userService.addUser(new User("name", "oldEmail", "PASSWORD"));

        userService.updateUserPassword(userId, "newPassword");

        User userById = getUserFromDb(userId);
        assertEquals(DigestUtils.md5Hex("newPassword"), userById.getPassword());
    }

    @Test
    public void updateUser() {
        long userId = userService.addUser(new User("oldName", "oldEmail", "oldPassword"));

        userService.updateUser(userId, "newName", "newEmail", "newPassword");

        User userById = getUserFromDb(userId);
        assertEquals("newName", userById.getName());
        assertEquals("newEmail", userById.getEmail());
        assertEquals(DigestUtils.md5Hex("newPassword"), userById.getPassword());
    }

}
