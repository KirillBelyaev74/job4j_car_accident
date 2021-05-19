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
import ru.job4j.accident.repository.hibernate.ActionAccident;
import ru.job4j.accident.repository.hibernate.ActionAccidentType;
import ru.job4j.accident.repository.hibernate.ActionRule;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class CrudControl {

    private final ActionAccident actionAccident;
    private final ActionAccidentType actionAccidentType;
    private final ActionRule actionRule;
    private final Map<Integer, AccidentType> allTypes = new HashMap<>();
    private final Map<Integer, Rule> allRules = new HashMap<>();

    @Autowired
    public CrudControl(ActionAccident accidentAccident, ActionAccidentType actionAccidentType, ActionRule actionRule) {
        this.actionAccident = accidentAccident;
        this.actionAccidentType = actionAccidentType;
        this.actionRule = actionRule;
    }

    @GetMapping("/create")
    public String create(Model model) {
        isEmptyTypesAndRules();
        model.addAttribute("accidentsType", allTypes.values());
        model.addAttribute("rules", allRules.values());
        return "/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest request) {
        int accidentTypeId = Integer.parseInt(request.getParameter("accidentsType"));
        String[] rulesId = request.getParameterValues("rules");

        AccidentType accidentType = actionAccidentType.findById(accidentTypeId);
        List<Rule> rules = null;
        if (rulesId != null) {
            rules = allRules.entrySet().stream().filter(r -> {
                boolean result = false;
                for (int i = 0; i < rulesId.length; i++) {
                    if (Integer.parseInt(rulesId[i]) == r.getKey()) {
                        result = true;
                    }
                }
                return result;
            }).map(Map.Entry::getValue).collect(Collectors.toList());
        }

        accident.setAccidentType(accidentType);
        accident.setRules(rules);

        actionAccident.save(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String edit(@RequestParam int id, Model model) {
        Accident accident = actionAccident.findById(id);
        isEmptyTypesAndRules();
        model.addAttribute("accidentsType", allTypes.values());
        model.addAttribute("rules", allRules.values());
        model.addAttribute("accident", accident);
        return "/update";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id) {
        actionAccident.deleteById(id);
        return "redirect:/";
    }

    private void isEmptyTypesAndRules() {
        if (allTypes.isEmpty() && allRules.isEmpty()) {
            actionAccidentType.findAll().forEach(a -> allTypes.put(a.getId(), a));
            actionRule.findAll().forEach(r -> allRules.put(r.getId(), r));
        }
    }
}
