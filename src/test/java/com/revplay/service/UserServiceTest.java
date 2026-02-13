package com.revplay.service;

import com.revplay.model.User;
import com.revplay.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    private UserService service = new UserServiceImpl();

    @Test
    public void testUserModel() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");
        user.setEmail("test@test.com");
        user.setPassword("password");
        user.setRole("user");
        user.setSecurityQuestion("What is your name?");
        user.setSecurityAnswer("Test");

        assertNotNull(user);
        assertEquals(1, user.getId());
        assertEquals("testuser", user.getUsername());
        assertEquals("test@test.com", user.getEmail());
        assertEquals("user", user.getRole());
        assertEquals("What is your name?", user.getSecurityQuestion());
        assertEquals("Test", user.getSecurityAnswer());
    }

    @Test
    public void testUserDefaultConstructor() {
        User user = new User();
        assertNotNull(user);
    }

    @Test
    public void testUserToString() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");
        user.setEmail("test@test.com");
        user.setRole("user");

        String result = user.toString();
        assertNotNull(result);
        assertTrue(result.contains("testuser"));
    }

    @Test
    public void testUserRoles() {
        User user = new User();
        
        user.setRole("user");
        assertEquals("user", user.getRole());
        
        user.setRole("artist");
        assertEquals("artist", user.getRole());
        
        user.setRole("admin");
        assertEquals("admin", user.getRole());
    }

    @Test
    public void testUserEquality() {
        User user1 = new User();
        user1.setId(1);
        user1.setEmail("test@test.com");

        User user2 = new User();
        user2.setId(1);
        user2.setEmail("test@test.com");

        assertEquals(user1.getId(), user2.getId());
        assertEquals(user1.getEmail(), user2.getEmail());
    }
}
