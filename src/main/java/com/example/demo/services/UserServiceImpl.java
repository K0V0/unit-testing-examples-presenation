package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    public static final String PUBLIC_STATIC_METHOD_RETURN = "return from public static method";
    public static final String PRIVATE_STATIC_METHOD_RETURN = "return from private static method";
    public static final String PRIVATE_METHOD_RETURN = "return from private method";

    private final String varPrivateFinal = PRIVATE_METHOD_RETURN;
    private String varPrivate = PRIVATE_METHOD_RETURN;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String getUserFirstAndLastNameByUserId(final long userId) {
        final User user = userRepository.getUserById(userId);
        validate(user);
        return composeFullName(user.getFirstName(), user.getLastName());
    }

    @Override
    public String getUserFirstAndLastNameByUserName(final String userName) {
        final User user = userRepository.getUserByUserName(userName);
        validate(user);
        return composeFullName(user.getFirstName(), user.getLastName());
    }

    private String composeFullName(final String firstName, final String lastName) {
        return String.format("%s %s", firstName, lastName);
    }

    private void validate(User user) {
        if (user == null) {
            throw new UserNotFoundException();
        }
    }

    public static String methodPublicStatic() {
        return PUBLIC_STATIC_METHOD_RETURN;
    }

    private static String methodPrivateStatic() {
        return PRIVATE_STATIC_METHOD_RETURN;
    }

    public static String methodPrivateStaticWrapper() {
        return methodPrivateStatic();
    }

    private String methodPrivate() {
        return varPrivate;
    }

    public String methodPrivateWrapper() {
        return methodPrivate();
    }

    protected final String methodProtectedFinal() {
        return varPrivate;
    }

    public String methodProtectedFinalWrapper() {
        return methodProtectedFinal();
    }

}
