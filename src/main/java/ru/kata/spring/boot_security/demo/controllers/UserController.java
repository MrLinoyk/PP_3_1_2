package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping ("/login")
    public String loginPage (ModelMap modelMap, Principal principal) {
//        modelMap.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        return "/login";
    }

    @GetMapping("/{id}")
    public String findUser (@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.getUserById(id));
        return "show";
    }
}
