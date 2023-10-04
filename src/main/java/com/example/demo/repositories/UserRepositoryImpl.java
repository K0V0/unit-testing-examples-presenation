package com.example.demo.repositories;

import com.example.demo.entities.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 *  Fake repository implementation for demonstration purposes
 */

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final List<User> USERS = new ArrayList<>();

    static {
        USERS.add(new User(1L, "reaktor4", "Anatoli", "Cigaretlov"));
        USERS.add(new User(2L, "pumpy", "Sergej", "Alkoholovič"));
        USERS.add(new User(3L, "naSatniJeNajlepsie", "Voloďa", "Nemakačenko"));
    }

    @Override
    public User getUserById(long id) {
        return USERS.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public User getUserByUserName(String userName) {
        return USERS.stream()
                .filter(user -> user.getUserName().equals(userName))
                .findFirst()
                .orElse(null);
    }

}
