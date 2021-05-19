package ru.job4j.accident.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.hibernate.ActionAuthority;
import ru.job4j.accident.repository.hibernate.ActionUser;

@Controller
public class RegistrationControl {

    private final PasswordEncoder passwordEncoder;
    private final ActionAuthority actionAuthority;
    private final ActionUser actionUser;

    @Autowired
    public RegistrationControl(PasswordEncoder passwordEncoder, ActionAuthority actionAuthority, ActionUser actionUser) {
        this.passwordEncoder = passwordEncoder;
        this.actionAuthority = actionAuthority;
        this.actionUser = actionUser;
    }

    @PostMapping("/registration")
    public String save(@ModelAttribute User user) {
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthority(actionAuthority.findByAuthority("ROLE_USER"));
        actionUser.save(user);
        return "redirect:/login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
}
