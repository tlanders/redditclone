package biz.lci.springboot.redditclone.controller;

import biz.lci.springboot.redditclone.domain.User;
import biz.lci.springboot.redditclone.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class PublicProfileController {
    private final UserService userService;
    private final Logger log = LoggerFactory.getLogger(PublicProfileController.class);

    public PublicProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/u/{userAlias}")
    public String publicProfile(@PathVariable String userAlias, Model model) {
        log.info("GET publicProfile - user alias={}", userAlias);
        Optional<User> user = userService.findUserByAlias(userAlias);

        if(user.isPresent()) {
            model.addAttribute("user", user.get());
            return "auth/public_profile";
        } else {
            return "redirect:/";
        }
    }
}
