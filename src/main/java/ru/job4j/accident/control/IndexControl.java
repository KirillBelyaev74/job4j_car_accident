package ru.job4j.accident.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.service.AccidentService;

@Controller
public class IndexControl {

    private final AccidentService accidentService;

    @Autowired
    public IndexControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", accidentService.findAll());
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "index";
    }
}
