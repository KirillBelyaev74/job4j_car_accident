package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.repository.AccidentMemory;

@Controller
public class IndexControl {

    private final AccidentMemory accidentMemory;

    public IndexControl(AccidentMemory accident) {
        this.accidentMemory = accident;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", accidentMemory.getAll());
        return "index";
    }
}
