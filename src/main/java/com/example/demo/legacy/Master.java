package com.example.demo.legacy;

import org.springframework.stereotype.Service;

@Service
public class Master {

    private static final Slave3 slave3 = new Slave3();
    private final Slave slave = new Slave();
    private Slave2 slave2 = new Slave2();

    public String getSlaveId() {
        return slave.getId();
    }

    public String getSlave2Id() {
        return slave2.getId();
    }

}