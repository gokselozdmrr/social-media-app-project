package me.taciozturk.Authentication;

import me.taciozturk.User;
import me.taciozturk.UserList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;


class LoginTest {
    Login userLogin = new Login();
    UserList userList = new UserList();

    @Test
    public void successfulLogin() throws FileNotFoundException, NoSuchAlgorithmException {
        assertEquals(userList.getUserById(1),userLogin.login("emma@example.com","Emma Brown1",userList));
    }

    @Test
    public void invalidLogin() throws FileNotFoundException, NoSuchAlgorithmException {
        assertNotEquals("Beklenmeyen durum!...",userLogin.login("emma@example.com","Emma Brown1",userList));
    }

    @Test
    public void testInvalidLoginWithWrongPassword() throws FileNotFoundException, NoSuchAlgorithmException {
        assertNull(userLogin.login("emma@example.com", "wrongpassword", userList));
    }

    @Test
    public void testInvalidLoginWithNonExistingEmail() throws FileNotFoundException, NoSuchAlgorithmException {
        assertNull(userLogin.login("nonexisting@example.com", "Emma Brown1", userList));
    }

    @Test
    public void testInvalidLoginWithNonExistingEmailAndWrongPassword() throws FileNotFoundException, NoSuchAlgorithmException {
        assertNull(userLogin.login("nonexisting@example.com", "wrongpassword", userList));
    }

}