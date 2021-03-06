package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService{
    void addUser(User user);

    void deleteUser(int id);

    void editUser(User user);

    User getUserById(int id);

    List<User> getAllUsers();
    Set<Role> getSetOfRoles(List<String> rolesId);
}
