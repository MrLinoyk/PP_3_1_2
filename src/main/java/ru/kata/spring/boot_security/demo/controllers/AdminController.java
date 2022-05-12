package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.Collection;
import java.util.List;

@Service
@RequestMapping("/admin")
public class AdminController {
    public final UserService userServiceService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userServiceService, RoleService roleService) {
        this.userServiceService = userServiceService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getAllUsers (Model model) {
        model.addAttribute("allUsers", userServiceService.listUser());
        return "/admin";
    }
    @GetMapping ("/delete/{id}")
    public String deleteUser (@PathVariable ("id") int id) {
        userServiceService.delete(id);
        return "redirect:/admin";
    }
    @GetMapping ("/update/{id}")
    public String updateUserForm (@PathVariable ("id") int id, Model model) {
        model.addAttribute("update", userServiceService.get(id));
        model.addAttribute("allRole", roleService.getRoleList());
        return "update";
    }
    @PostMapping ("/update")
    public String updateUser (@ModelAttribute ("update") User user, @RequestParam ("roleList") List<String> role) {
        user.setRoles(userServiceService.getSetOfRoles(role));
        userServiceService.update(user);
        return "redirect:/admin";
    }

    @GetMapping ("/registration")
    public String registration (Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.getRoleList());
        return "registration";
    }
    @PostMapping ("/registration")
    public String addUser (@ModelAttribute ("user") User user, @RequestParam ("role") List<String> role) {
        Collection<Role> roleList = userServiceService.getSetOfRoles(role);
        user.setRoles(roleList);
        userServiceService.add(user);
        return "redirect:/admin";
    }

}
