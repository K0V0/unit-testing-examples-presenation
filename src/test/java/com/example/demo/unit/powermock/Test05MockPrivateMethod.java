package com.example.demo.unit.powermock;

import com.example.demo.services.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.verifyPrivate;

/**
 *  - Mocking private and protected final methods using powermock
 *  - testing calling containing public method(s) to verify value
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(UserServiceImpl.class)
public class Test05MockPrivateMethod {

    private static final String TEST_STRING = "test string";

    /**
     *  Test private method mock
     */

    @Test
    public void testMockingPrivateMethod() throws Exception {
        UserServiceImpl mock = spy(new UserServiceImpl());

        assertEquals(UserServiceImpl.PRIVATE_METHOD_RETURN, mock.methodPrivateWrapper());

        // mock method
        PowerMockito.doReturn(TEST_STRING).when(mock, "methodPrivate");

        // get result
        final String result = mock.methodPrivateWrapper();

        // verify that method was invoked
        verifyPrivate(mock, times(2)).invoke("methodPrivate");

        // assert
        assertEquals(TEST_STRING, result);
    }

    @Test
    public void testMockingPrivateFinalMethod() throws Exception {
        final UserServiceImpl mock = spy(new UserServiceImpl());

        assertEquals(UserServiceImpl.PRIVATE_METHOD_RETURN, mock.methodProtectedFinalWrapper());

        // mock method
        PowerMockito.doReturn(TEST_STRING).when(mock, "methodProtectedFinal");

        // get result
        final String result = mock.methodProtectedFinalWrapper();

        // verify that method was invoked
        verifyPrivate(mock, times(2)).invoke("methodProtectedFinal");

        // assert
        assertEquals(TEST_STRING, result);
    }

    /**
     *  Test private method invocation
     */

    @Test
    public void testMockingPrivateMethodUsingWhitebox() throws Exception {
        final UserServiceImpl mock = spy(new UserServiceImpl());

        final String result = Whitebox.invokeMethod(mock, "methodPrivate");

        assertNotNull(result);
        assertEquals(UserServiceImpl.PRIVATE_METHOD_RETURN, result);
    }

}
