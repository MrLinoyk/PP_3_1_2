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
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
public class UserController {
    public final UserService userServiceService;


    @Autowired
    public UserController(UserService userServiceService) {
        this.userServiceService = userServiceService;
    }

    @GetMapping ("/login")
    public String loginPage () {
        return "/login";
    }

    @GetMapping("/user")
    public String findUser (Model model) {
        model.addAttribute("user" , userServiceService.loadUserByUsername(userServiceService.getCurrentUsername()));
        return "show";
    }
}
