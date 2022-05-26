package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

@Repository
public interface UserDAO {
    void addUser(User user);

    void deleteUser(int id);

    void editUser(User user);

    User getUserById(int id);

    List<User> getAllUsers();

    User getUserByUsername(String username);
}
