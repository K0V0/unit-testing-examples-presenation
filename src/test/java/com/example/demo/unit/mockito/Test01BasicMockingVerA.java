package com.example.demo.unit.mockito;

import com.example.demo.entities.User;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 *  Test basic mocking scenario using jUnit and Mockito - mock public method on "repository" to get wanted user.
 *  Lenient is enabled because of Mockito bitching around unused mocked methods
 *
 *  - tests mocking basic public methods
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class Test01BasicMockingVerA {

    @Mock
    private UserRepository userRepository;

    /** Note implementation class here (no interface) */
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testMockingPublicMethod() {

        // Arrange - data
        final User testUser = new User(1L, "testUser", "User", "Testovič");
        when(userRepository.getUserById(1)).thenReturn(testUser);

        // Act
        final String userFullName = userService.getUserFirstAndLastNameByUserId(1L);

        // assert
        assertEquals("User Testovič", userFullName);
    }


    /**
     *  Will fail because we do not mocked method getUserById() on UserRepository and by default it returns null
     *  - Mockito overrides implementations
     */
    @Test
    public void testCallingNotMockedMethod() {

        // Arrange - data
        final User testUser = new User(1L, "testUser", "User", "Testovič");
        when(userRepository.getUserById(1)).thenReturn(testUser);

        // Act & assert
        assertThrows(UserNotFoundException.class, () -> userService.getUserFirstAndLastNameByUserName("testUser"));
    }

    /**
     *  Note that user with ID 2 exists in repository, but Mocked repository will return null
     */
    @Test
    public void testMockingPublicMethodOtherUserId() {

        // Arrange - data
        final User testUser = new User(1L, "testUser", "User", "Testovič");
        when(userRepository.getUserById(1L)).thenReturn(testUser);

        // Act & assert
        assertThrows(UserNotFoundException.class, () -> userService.getUserFirstAndLastNameByUserId(2L));
    }
}
