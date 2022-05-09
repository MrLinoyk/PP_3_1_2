package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.User;

public interface UserService {
    Iterable<User> getAllUsers ();
    User getUserById (int id);
    void addUser (User user);
    void removeUser (int id);
    void updateUser (User user);
}
