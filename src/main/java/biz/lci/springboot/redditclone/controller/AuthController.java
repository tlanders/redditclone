package biz.lci.springboot.redditclone.controller;

import biz.lci.springboot.redditclone.domain.User;
import biz.lci.springboot.redditclone.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthController {
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

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
        //model.addAttribute("success", false);
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerNewUser(@Valid User user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            // show validation errors
            logger.info("validation errors found when registering new user");
            model.addAttribute("user", user);
            model.addAttribute("validationErrors", bindingResult.getAllErrors());
            return "auth/register";
        } else {
            // register new user
            User newUser = userService.register(user);
            redirectAttributes
                    .addAttribute("id", newUser.getId())
                    .addFlashAttribute("success", true);
        }
        return "redirect:/register";
    }

    @GetMapping("/activate/{email}/{activationCode}")
    public String activateUser(@PathVariable String email, @PathVariable String activationCode) {
        logger.info("activate request - email={}, code={}", email, activationCode);

        if(userService.activateUser(email, activationCode)) {
            return "auth/activated";
        } else {
            // TODO - display an error
            return "redirect:/";
        }
    }
}
