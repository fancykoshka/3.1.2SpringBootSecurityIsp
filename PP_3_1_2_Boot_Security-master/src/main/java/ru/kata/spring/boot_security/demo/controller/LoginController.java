package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Collections;

@Controller
public class LoginController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public LoginController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/login")
    public String login() {
        return "/log/login";
    }


    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/log/login";
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "/log/register";
    }


    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, @RequestParam("passwordConfirm") String passwordConfirm) {
        // Проверяем, что пароли совпадают
        if (!user.getPassword().equals(passwordConfirm)) {
            return "/log/register"; // Можно добавить сообщение об ошибке
        }


        Role userRole = roleService.getRoleByName("ROLE_USER");
        user.setRoles(Collections.singleton(userRole)); // Устанавливаем роль пользователю


        userService.saveUser(user);


        return "redirect:/log/login";}


}
