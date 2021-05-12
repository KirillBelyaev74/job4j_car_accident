package ru.job4j.accident.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import ru.job4j.accident.repository.AccidentMemory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
public class AccidentControl {

    private final AccidentJdbcTemplate accidentMemory;

    @Autowired
    public AccidentControl(AccidentJdbcTemplate accidents) {
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
        List<Rule> rules = new ArrayList<>();
        Arrays.stream(rulesId).forEach(id -> rules.add(accidentMemory.getRulesId(Integer.parseInt(id))));

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
