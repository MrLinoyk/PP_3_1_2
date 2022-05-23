package ru.kata.spring.boot_security.demo.repo;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

@Repository
public interface UserRepository {
    void addUser(User user);

    void deleteUser(int id);

    void editUser(User user);

    User getUserById(int id);

    List<User> getAllUsers();

    User getUserByUsername(String username);
}
