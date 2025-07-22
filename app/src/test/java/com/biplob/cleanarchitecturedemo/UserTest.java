// File: src/test/java/com/biplob/cleanarchitecturedemo/domain/model/UserTest.java

package com.biplob.cleanarchitecturedemo;

import org.junit.Test;
import static org.junit.Assert.*;

import com.biplob.cleanarchitecturedemo.domain.model.User;

public class UserTest {
    @Test
    public void testUserCreationAndGetters() {
        User user = new User(1, "Alice", "alice123", "alice@email.com");

        assertEquals(1, user.getId());
        assertEquals("Alice", user.getName());
        assertEquals("alice123", user.getUsername());
        assertEquals("alice@email.com", user.getEmail());
    }
}
