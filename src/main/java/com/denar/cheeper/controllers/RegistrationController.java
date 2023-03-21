package com.denar.cheeper.controllers;

import com.denar.cheeper.datalayer.entities.User;
import com.denar.cheeper.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {
    @Autowired
    private final UserService userService;

    @GetMapping
    public String registration(Model model) {
        return "registration";
    }

    @PostMapping
    public String addUser(
            @RequestParam("regUsername") String username,
            @RequestParam("regPassword") String password,
                          Model model) {
        User userFromBD = userService.readByUsername(username);

        if (userFromBD != null) {
            model.addAttribute("message", "Choose a different name.");
            return "registration";
        }

        userService.save(username, password);

        return "redirect:/login";
    }
}
