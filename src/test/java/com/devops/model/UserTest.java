package com.devops.model;

import org.junit.jupiter.api.Test;  // ✅ MUST be jupiter
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void testSettersAndGetters() {
        User user = new User();
        user.setUsername("ayush");
        user.setEmail("ayush@example.com");

        assertEquals("ayush", user.getUsername());
        assertEquals("ayush@example.com", user.getEmail());
    }

    @Test
    void testConstructor() {
        User user = new User("raj", "raj@example.com");

        assertEquals("raj", user.getUsername());
        assertEquals("raj@example.com", user.getEmail());
    }
}
