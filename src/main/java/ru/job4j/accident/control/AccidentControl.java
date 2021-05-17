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
import ru.job4j.accident.repository.AccidentHibernate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class AccidentControl {

    private final AccidentHibernate accidentHibernate;

    @Autowired
    public AccidentControl(AccidentHibernate accidents) {
        this.accidentHibernate = accidents;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("accidentsType", accidentHibernate.getAllAccidentType());
        model.addAttribute("rules", accidentHibernate.getAllRules());
        return "/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest request) {
        int accidentTypeId = Integer.parseInt(request.getParameter("accidentsType"));
        String[] rulesId = request.getParameterValues("rules");

        AccidentType accidentType = accidentHibernate.getAccidentTypeId(accidentTypeId);
        List<Rule> rules = new ArrayList<>();
        if (rulesId != null) {
            Arrays.stream(rulesId).forEach(id -> rules.add(accidentHibernate.getRulesId(Integer.parseInt(id))));
        }

        accident.setAccidentType(accidentType);
        accident.setRules(rules);

        accidentHibernate.addOrReplace(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String edit(@RequestParam int id, Model model) {
        Accident accident = accidentHibernate.getAccidentId(id);
        model.addAttribute("accidentsType", accidentHibernate.getAllAccidentType());
        model.addAttribute("rules", accidentHibernate.getAllRules());
        model.addAttribute("accident", accident);
        return "/update";
    }
}
