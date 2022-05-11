package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserDetailsServiceImpl;

import java.util.List;

@Service
@RequestMapping("/admin")
public class AdminController {
    public final UserDetailsServiceImpl userDetailsService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserDetailsServiceImpl userDetailsService, RoleService roleService) {
        this.userDetailsService = userDetailsService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getAllUsers (Model model) {
        model.addAttribute("allUsers", userDetailsService.listUser());
        return "admin";
    }
    @GetMapping ("/delete/{id}")
    public String deleteUser (@PathVariable ("id") int id) {
        userDetailsService.delete(id);
        return "redirect:admin";
    }
    @GetMapping ("/update/{id}")
    public String updateUserForm (@PathVariable ("id") int id, Model model) {
        model.addAttribute("update", userDetailsService.get(id));
        model.addAttribute("allRole", roleService.getRoleList());
        return "update";
    }
    @PostMapping ("/update")
    public String updateUser (@ModelAttribute ("update") User user, @RequestParam ("roleList") List<String> role) {
        user.setRoles(userDetailsService.getSetOfRoles(role));
        userDetailsService.update(user);
        return "redirect:admin";
    }

}
