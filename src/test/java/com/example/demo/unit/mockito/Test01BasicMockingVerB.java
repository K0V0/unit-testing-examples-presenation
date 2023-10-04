package com.example.demo.unit.mockito;

import com.example.demo.entities.User;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

/**
 *  Test basic mocking scenario using jUnit and Mockito - mock public method on "repository" to get wanted user
 *
 *  - tests mocking basic public methods
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class Test01BasicMockingVerB {

    @Mock
    private UserRepository userRepository;

    /** Note concrete implementation here */
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testMockingPublicMethod() {

        // Arrange - data
        final User testUser = new User(1L, "testUser", "User", "Testovič");
        when(userRepository.getUserById(anyLong())).thenReturn(testUser);

        // Act
        final String userFullName1 = userService.getUserFirstAndLastNameByUserId(1L);
        final String userFullName2 = userService.getUserFirstAndLastNameByUserId(200L);

        // assert
        assertEquals("User Testovič", userFullName1);
        assertEquals("User Testovič", userFullName2);
    }

    /**
     *  Will fail because we do not mocked method getUserById() on UserRepository and by default it returns null
     *  - Mockito overrides implementations
     */
    @Test
    public void testMockingPublicMethodUsedByAnother() {

        // Arrange - important preparations
        MockitoAnnotations.openMocks(this);

        // Arrange - data
        final User testUser = new User(1L, "testUser", "User", "Testovič");
        when(userRepository.getUserById(anyLong())).thenReturn(testUser);

        // Act & assert
        assertThrows(UserNotFoundException.class, () -> userService.getUserFirstAndLastNameByUserName("testUser"));
    }
}
