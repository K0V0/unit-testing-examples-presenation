package com.example.demo.unit.mockito;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.UserRepositoryImpl;
import com.example.demo.services.UserService;
import com.example.demo.services.UserServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

/**
 *  Test basic mocking scenario using jUnit and Mockito - mock public method on "repository" to get wanted user
 *
 *  - tests partial mocking of basic public methods using @Spy
 */

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class Test02BasicMockingUsingSpy {

    /**
     *  Note both deps are implementation classes, otherwise will not work
     *  - if "spied" service UserRepository is only interface, default implementations will not work
     *  - if service where mocks are injected (UserService) is interface, Mockito will be bitching
     */
    @Spy
    private UserRepositoryImpl userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testMockingPublicMethod() {

        // Arrange
        final User testUser = new User(1L, "testUser", "User", "Testovič");
        when(userRepository.getUserById(1L)).thenReturn(testUser);

        // Act
        final String userFullName1 = userService.getUserFirstAndLastNameByUserId(1L);

        // assert
        assertEquals("User Testovič", userFullName1);
    }

    @Test
    public void testCallingNotMockedMethod() {

        // Arrange
        final User testUser = new User(1L, "testUser", "User", "Testovič");
        when(userRepository.getUserById(1L)).thenReturn(testUser);
        // alternative
        doReturn(testUser).when(userRepository).getUserById(2L);

        // Act
        final String testUserName = userService.getUserFirstAndLastNameByUserId(1L);
        final String realUserName = userService.getUserFirstAndLastNameByUserName("pumpy");

        // assert
        assertEquals("User Testovič", testUserName);
        assertEquals("Sergej Alkoholovič", realUserName);
    }
}
