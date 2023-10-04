package com.example.demo.unit.powermock;

import com.example.demo.legacy.Demo;
import com.example.demo.legacy.Slave;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *  Mocking using PowerMock tools
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Slave.class, Demo.class })
public class Test06MockingStaticFields {

    /**
     *  Change public static field
     */
    @Test
    public void testMockingPublicStaticField() {
        assertEquals("SOME_CONSTANT_PUBLIC", Slave.SOME_CONSTANT_PUBLIC);

        Whitebox.setInternalState(Slave.class, "SOME_CONSTANT_PUBLIC", "updated value");

        assertEquals("updated value", Slave.SOME_CONSTANT_PUBLIC);
    }

    /**
     *  Change private static field
     */
    @Test
    public void testMockingPrivateStaticField() {
        final Slave slave = new Slave();

        assertEquals("SOME_VALUE", slave.getPrivateStatic());

        Whitebox.setInternalState(Slave.class, "SOME_CONSTANT", "updated value");

        assertEquals("updated value", slave.getPrivateStatic());
    }

}
