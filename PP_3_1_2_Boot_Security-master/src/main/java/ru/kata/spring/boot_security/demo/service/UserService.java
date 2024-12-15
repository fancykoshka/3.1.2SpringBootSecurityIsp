package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> getUsers();

    User getUserById(int id);

    void saveUser(User user);

    void updateUser(User user, Set<Long> roleIds);

    void deleteUserById(int id);

    User findByUsername(String username);
}
