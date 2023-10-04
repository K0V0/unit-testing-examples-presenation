package com.example.demo.legacy;

public class Demo {

    public static final String PRIVATE_METHOD_RETURN = "private method result";

    private String methodPrivate() {
        return PRIVATE_METHOD_RETURN;
    }

    public String methodPrivateWrapper() {
        return this.methodPrivate();
    }

    public static String staticPublicMethod(String sth) {
        return "this is from static public method";
    }
}
