package biz.lci.springboot.redditclone.service;

import biz.lci.springboot.redditclone.domain.User;
import biz.lci.springboot.redditclone.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final MailService mailService;

    public UserService(MailService mailService, RoleService roleService, UserRepository userRepository) {
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.mailService = mailService;

        encoder = new BCryptPasswordEncoder();
    }

    public User register(User user) {
        // take form password and encode it
        String secret = "{bcrypt}" + encoder.encode(user.getPassword());
        user.setPassword(secret);
        user.setConfirmPassword(secret);

        // assign role to user
        user.addRole(roleService.findByName("ROLE_USER"));

        // set an activation code
        user.setActivationCode(UUID.randomUUID().toString());

        // disable user
        user.setEnabled(false);

        // save user
        save(user);

        // send activation email
        sendActivationEmail(user);

        // return user
        return user;
    }

    public void sendActivationEmail(User user) {
        mailService.sendActivationEmail(user);
    }

    public void sendWelcomeEmail(User user) {
        mailService.sendWelcomeEmail(user);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
