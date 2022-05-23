package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    public final UserService userServiceServiceImpl;
    private final RoleService roleServiceImpl;

    @Autowired
    public AdminController(UserServiceImpl userServiceServiceImpl, RoleServiceImpl roleServiceImpl) {
        this.userServiceServiceImpl = userServiceServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping
    public String getAllUsers (Model model) {
        model.addAttribute("allUsers", userServiceServiceImpl.getAllUsers());
        return "/admin";
    }

    @DeleteMapping ("/delete/{id}")
    public String deleteUser (@PathVariable ("id") int id) {
        userServiceServiceImpl.deleteUser(id);
        return "redirect:/admin";
    }


    @GetMapping ("/update/{id}")
    public String updateUserForm (@PathVariable ("id") int id, ModelMap model) {
        User user = userServiceServiceImpl.getUserById(id);
        Set<Role> roles = user.getRoles();
        for (Role role: roles) {
            if (role.equals(roleServiceImpl.getRoleByName("ROLE_ADMIN"))) {
                model.addAttribute("roleAdmin", true);
            }
        }
        model.addAttribute("user", user);
        model.addAttribute("allRole", roleServiceImpl.allRoles());

        return "update";
    }
    @PostMapping ("/update")
    public String updateUser (@ModelAttribute ("update") User user, @RequestParam ("roleList") String role) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleServiceImpl.getRoleByName("ROLE_USER"));
        if (role != null && role.equals("ROLE_ADMIN")) {
            roles.add(roleServiceImpl.getRoleByName("ROLE_ADMIN"));
        }
        user.setRoles(roles);
        userServiceServiceImpl.editUser(user);
        return "redirect:/admin";
    }



    @GetMapping ("/registration")
    public String registration (Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleServiceImpl.allRoles());
        return "registration";
    }
    @PostMapping ("/registration")
    public String addUser (@ModelAttribute ("user") User user, @RequestParam ("role") String role) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleServiceImpl.getRoleByName("ROLE_USER"));
        if (role != null && role.equals("ROLE_ADMIN")) {
            roles.add(roleServiceImpl.getRoleByName("ROLE_ADMIN"));
        }
        user.setRoles(roles);
        userServiceServiceImpl.addUser(user);
        return "redirect:/admin";
    }

}
