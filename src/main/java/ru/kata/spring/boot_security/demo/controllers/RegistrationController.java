package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserDetailsServiceImpl;

import java.util.Collection;
import java.util.List;

@Controller
public class RegistrationController {
    private final UserDetailsServiceImpl userDetailsService;
    private final RoleService roleService;

    @Autowired
    public RegistrationController(UserDetailsServiceImpl userDetailsService, RoleService roleService) {
        this.userDetailsService = userDetailsService;
        this.roleService = roleService;
    }

    @GetMapping ("/registration")
    public String registration (Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.getRoleList());
        return "registration";
    }
    @PostMapping ("/registration")
    public String addUser (@ModelAttribute ("user") User user, @RequestParam ("role") List<String> role) {
        Collection<Role> roleList = userDetailsService.getSetOfRoles(role);
        user.setRoles(roleList);
        userDetailsService.add(user);
        return "redirect:/";
    }

    @GetMapping ("/login")
    public String loginPage () {
        return "login";
    }
}
