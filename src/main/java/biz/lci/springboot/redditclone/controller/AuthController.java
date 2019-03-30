package biz.lci.springboot.redditclone.controller;

import biz.lci.springboot.redditclone.domain.User;
import biz.lci.springboot.redditclone.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/profile")
    public String showProfile() {
        return "auth/profile";
    }

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("success", false);
        return "auth/register";
    }
}
