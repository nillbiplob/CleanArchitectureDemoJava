package com.biplob.cleanarchitecturedemo;

// File: src/test/java/com/biplob/cleanarchitecturedemo/application/usecase/GetUsersUseCaseTest.java

import com.biplob.cleanarchitecturedemo.application.usecase.GetUsersUseCase;
import com.biplob.cleanarchitecturedemo.domain.model.User;
import com.biplob.cleanarchitecturedemo.domain.repository.UserRepository;

import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GetUsersUseCaseTest {

    // A fake repo just for testing
    static class FakeUserRepository implements UserRepository {
        @Override
        public List<User> getUsers() {
            return Arrays.asList(
                    new User(1, "Alice", "alice123", "alice@email.com"),
                    new User(2, "Bob", "bob456", "bob@email.com")
            );
        }
    }

    @Test
    public void testGetUsersUseCaseReturnsList() throws Exception {
        UserRepository fakeRepo = new FakeUserRepository();
        GetUsersUseCase useCase = new GetUsersUseCase(fakeRepo);

        List<User> users = useCase.execute();

        assertEquals(2, users.size());
        assertEquals("Alice", users.get(0).getName());
        assertEquals("Bob", users.get(1).getName());
    }
}
