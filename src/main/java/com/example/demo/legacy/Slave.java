package com.example.demo.legacy;

import org.springframework.stereotype.Service;

@Service
public class Slave {

    public static final String SOME_CONSTANT_FINAL_PUBLIC = "SOME_FINAL_VALUE_PUBLIC";
    public static String SOME_CONSTANT_PUBLIC = "SOME_CONSTANT_PUBLIC";

    private static final String SOME_CONSTANT_FINAL = "SOME_FINAL_VALUE";
    private static String SOME_CONSTANT = "SOME_VALUE";
    private final String somethingPrivateFinal;
    private String Id;

    public Slave() {
        somethingPrivateFinal = null;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getPrivateStatic() {
        return SOME_CONSTANT;
    }

    public final String finalPublicMethod() {
        return "this is from public final method";
    }

    private final String finalPrivateMethod() {
        return "this is from private final method";
    }

    public static String staticPublicMethod() {
        return "this is from static public method";
    }
}
