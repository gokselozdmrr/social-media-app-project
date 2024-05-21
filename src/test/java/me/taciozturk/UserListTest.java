package me.taciozturk;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserListTest {
    private UserList userList;

    @BeforeEach
    public void setUp() {
        userList = new UserList();
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setName("Alice");
        userList.add(user);
        List<User> users = userList.getAllUsers();
        assertEquals(1, users.size());
        assertEquals("Alice", users.get(0).getName());
    }

    @Test
    public void testRemoveUser() {
        User user1 = new User();
        user1.setName("Alice");
        User user2 = new User();
        user2.setName("Bob");
        userList.add(user1);
        userList.add(user2);
        userList.remove(user1);
        List<User> users = userList.getAllUsers();
        assertEquals(1, users.size());
        assertEquals("Bob", users.get(0).getName());
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setName("Alice");
        userList.add(user);
        User retrievedUser = userList.getUserById(user.getId());
        assertNotNull(retrievedUser);
        assertEquals("Alice", retrievedUser.getName());
    }

    @Test
    public void testAddConnection() {
        User user1 = new User();
        user1.setName("Alice");
        User user2 = new User();
        user2.setName("Bob");
        userList.add(user1);
        userList.add(user2);
        userList.addConnection(user1, user2);
        assertTrue(user1.getConnections().contains(user2.getId()));
        assertTrue(user2.getConnections().contains(user1.getId()));
    }

    @Test
    public void testRemoveConnection() {
        User user1 = new User();
        user1.setName("Alice");
        User user2 = new User();
        user2.setName("Bob");
        userList.add(user1);
        userList.add(user2);
        userList.addConnection(user1, user2);
        userList.removeConnection(user1, user2);
        assertFalse(user1.getConnections().contains(user2.getId()));
        assertFalse(user2.getConnections().contains(user1.getId()));
    }
}
