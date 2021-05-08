package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;

import java.util.List;

@Controller
public class IndexControl {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", List.of(
                new Accident("one", "one", "one"),
                new Accident("two", "two", "two")));
        return "index";
    }
}
