package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repo.RoleRepository;

import java.util.Collection;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public Collection <Role> getRoleList () {
        return roleRepository.findAll();
    }
    public Role getRoleById (int id) {
       return roleRepository.getById(id);
    }
}
