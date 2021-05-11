package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMemory;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentControl {

    private final AccidentMemory accidentMemory;

    public AccidentControl(AccidentMemory accidents) {
        this.accidentMemory = accidents;
    }

    @GetMapping("/create")
    public String create() {
        return "/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        accidentMemory.add(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Accident accident = accidentMemory.getId(id);
        model.addAttribute("accident", accident);
        return "/edit";
    }

    @PostMapping("/saveEdit")
    public String saveEdit(@ModelAttribute Accident accident) {
        accidentMemory.replace(accident);
        return "redirect:/";
    }
}
