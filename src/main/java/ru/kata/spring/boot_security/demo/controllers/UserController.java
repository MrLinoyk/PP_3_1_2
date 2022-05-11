package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserDetailsServiceImpl;

@Controller
public class UserController {
    public final UserDetailsServiceImpl userDetailsService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserDetailsServiceImpl userDetailsService, RoleService roleService) {
        this.userDetailsService = userDetailsService;
        this.roleService = roleService;
    }

    @GetMapping
    public String findUser (Model model) {
        model.addAttribute("user" , userDetailsService.loadUserByUsername(userDetailsService.getCurrentUsername()));
        return "show";
    }
    @GetMapping ("/updateUser/{id}")
    public String updateUserForm (@PathVariable ("id") int id, Model model) {
        model.addAttribute("user", userDetailsService.get(id));
        return "updateUser";
    }
    @PostMapping ("/updateUser")
    public String updateUser (@ModelAttribute ("user") User user) {
        userDetailsService.updateUser(user);
        return "redirect:/logout";
    }
}
