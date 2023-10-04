package com.example.demo.unit.mockito;

import com.example.demo.services.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test04MockingStaticMethods {

    private static final String TEST_VALUE = "test value";


    /**
     *  Test mocking public static method.
     *  Mocking using new features of Mockito-Inline, Mocked value is scoped
     */
    @Test
    public void testMockingPublicStaticMethod() {
        assertEquals(UserServiceImpl.methodPublicStatic(), UserServiceImpl.PUBLIC_STATIC_METHOD_RETURN);

        try (final MockedStatic<UserServiceImpl> utilities = Mockito.mockStatic(UserServiceImpl.class)) {
            // Act
            utilities.when(UserServiceImpl::methodPublicStatic).thenReturn(TEST_VALUE);
            // Assert
            assertEquals(TEST_VALUE, UserServiceImpl.methodPublicStatic());
        }

        assertEquals(UserServiceImpl.methodPublicStatic(), UserServiceImpl.PUBLIC_STATIC_METHOD_RETURN);
    }
}
