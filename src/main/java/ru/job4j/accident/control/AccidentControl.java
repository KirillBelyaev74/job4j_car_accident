package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentMemory;

@Controller
public class AccidentControl {

    private final AccidentMemory accidentMemory;

    public AccidentControl(AccidentMemory accidents) {
        this.accidentMemory = accidents;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("accidentsType", accidentMemory.getAllAccidentType());
        return "/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, @RequestParam int accidentsType) {
        AccidentType accidentType = accidentMemory.getAccidentTypeId(accidentsType);
        accident.setAccidentType(accidentType);
        accidentMemory.addOrReplace(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String edit(@RequestParam int id, Model model) {
        Accident accident = accidentMemory.getAccidentId(id);
        model.addAttribute("accidentsType", accidentMemory.getAllAccidentType());
        model.addAttribute("accident", accident);
        return "/update";
    }
}
