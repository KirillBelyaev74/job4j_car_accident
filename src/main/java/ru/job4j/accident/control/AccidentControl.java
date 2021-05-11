package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMemory;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Controller
public class AccidentControl {

    private final AccidentMemory accidentMemory;

    public AccidentControl(AccidentMemory accidents) {
        this.accidentMemory = accidents;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("accidentsType", accidentMemory.getAllAccidentType());
        model.addAttribute("rules", accidentMemory.getAllRules());
        return "/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest request) {
        int accidentTypeId = Integer.parseInt(request.getParameter("accidentsType"));
        String[] rulesId = request.getParameterValues("rules");

        AccidentType accidentType = accidentMemory.getAccidentTypeId(accidentTypeId);
        Set<Rule> rules = accidentMemory.getRulesId(rulesId);

        accident.setAccidentType(accidentType);
        accident.setRules(rules);

        accidentMemory.addOrReplace(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String edit(@RequestParam int id, Model model) {
        Accident accident = accidentMemory.getAccidentId(id);
        model.addAttribute("accidentsType", accidentMemory.getAllAccidentType());
        model.addAttribute("rules", accidentMemory.getAllRules());
        model.addAttribute("accident", accident);
        return "/update";
    }
}
