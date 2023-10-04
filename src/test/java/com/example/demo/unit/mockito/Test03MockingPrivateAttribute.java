package com.example.demo.unit.mockito;

import com.example.demo.legacy.Master;
import com.example.demo.legacy.Slave;
import com.example.demo.legacy.Slave2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 *  Mocking private fields, using Mockito and reflection API
 *
 *  - mocking private field value
 *  - mocking private final field value
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class Test03MockingPrivateAttribute {

    private static final String NOT_PRIVATE_ANYMORE = "notPrivateAnymore";

    @Mock
    private Slave slave;

    @Mock
    private Slave2 slave2;

    @InjectMocks
    private Master master;

    /**
     *  Class "Master" has private field with object "Slave" which has one attribute "Id" that we need to override,
     *  but no getter/setter is available for "Slave" instance in "Master"
     */

    @Test
    public void testMockingPrivateField() throws IllegalAccessException {

        // using reflection to access fields
        final Field field = ReflectionUtils
                .findFields(
                        Master.class,
                        f -> f.getName().equals("slave2"),
                        ReflectionUtils.HierarchyTraversalMode.TOP_DOWN)
                .get(0);
        field.setAccessible(true);
        field.set(master, slave2);

        // check that value was not set before
        assertNull(slave2.getId());

        when(slave2.getId()).thenReturn(NOT_PRIVATE_ANYMORE);

        // check that mock was sucessfull
        assertEquals(NOT_PRIVATE_ANYMORE, master.getSlave2Id());
    }

    @Test
    public void testMockingPrivateFinalField() throws IllegalAccessException {

        // using reflection to access fields
        final Field field = ReflectionUtils
                .findFields(
                        Master.class,
                        f -> f.getName().equals("slave"),
                        ReflectionUtils.HierarchyTraversalMode.TOP_DOWN)
                .get(0);
        field.setAccessible(true);
        field.set(master, slave);

        // check that value was not set before
        assertNull(slave.getId());

        when(slave.getId()).thenReturn(NOT_PRIVATE_ANYMORE);

        // check that mock was sucessfull
        assertEquals(NOT_PRIVATE_ANYMORE, master.getSlaveId());
    }
}
