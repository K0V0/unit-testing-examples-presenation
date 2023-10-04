package com.example.demo.repositories;

import com.example.demo.entities.User;

public interface UserRepository {

    User getUserById(long id);
    User getUserByUserName(String firstName);

}
