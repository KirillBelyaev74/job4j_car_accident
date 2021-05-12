package ru.job4j.accident.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

@Controller
public class IndexControl {

    private final AccidentJdbcTemplate accidentMemory;

    @Autowired
    public IndexControl(AccidentJdbcTemplate accident) {
        this.accidentMemory = accident;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", accidentMemory.getAllAccident());
        return "index";
    }
}
