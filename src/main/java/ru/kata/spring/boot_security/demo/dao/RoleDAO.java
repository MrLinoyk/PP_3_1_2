package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

@Repository
public interface RoleDAO {
    Role getRoleByName(String name);

    Role getRoleById(int id);

    List<Role> allRoles();

    Role getDefaultRole();
}
