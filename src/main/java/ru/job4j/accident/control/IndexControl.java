package ru.job4j.accident.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.repository.AccidentHibernate;

@Controller
public class IndexControl {

    private final AccidentHibernate accidentHibernate;

    @Autowired
    public IndexControl(AccidentHibernate accidentHibernate) {
        this.accidentHibernate = accidentHibernate;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", accidentHibernate.getAllAccident());
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "index";
    }
}
