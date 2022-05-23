package ru.kata.spring.boot_security.demo.repo;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

@Repository
public interface RoleRepository {
    Role getRoleByName(String name);

    Role getRoleById(int id);

    List<Role> allRoles();

    Role getDefaultRole();
}
