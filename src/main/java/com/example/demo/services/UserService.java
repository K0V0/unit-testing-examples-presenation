package com.example.demo.services;

public interface UserService {
    String getUserFirstAndLastNameByUserId(final long userId);
    String getUserFirstAndLastNameByUserName(final String userName);
}
