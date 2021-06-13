package ru.job4j.accident.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.User;
import ru.job4j.accident.service.AuthorityService;
import ru.job4j.accident.service.UserService;

@Controller
public class RegistrationControl {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AuthorityService authorityService;

    @Autowired
    public RegistrationControl(PasswordEncoder passwordEncoder, UserService userService, AuthorityService authorityService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.authorityService = authorityService;
    }

    @PostMapping("/registration")
    public String save(@ModelAttribute User user) {
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthority(authorityService.findByAuthority("ROLE_USER"));
        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
}
