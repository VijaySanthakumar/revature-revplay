package com.revplay.service;

import com.revplay.model.User;
import com.revplay.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    private UserService service = new UserServiceImpl();

    @Test
    public void testLoginSuccess() throws Exception {
        User user = service.login("vijay@gmail.com", "vijay");
        assertNotNull(user);
        assertEquals("vijay@gmail.com", user.getEmail());
    }

    @Test
    public void testLoginFailure() throws Exception {
        User user = service.login("vijay@gmail.com", "wrongpassword");
        assertNull(user);
    }
}
