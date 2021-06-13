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
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.AccidentTypeService;
import ru.job4j.accident.service.RuleService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class CrudControl {

    private final AccidentService accidentService;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;
    private final Map<Integer, AccidentType> allTypes = new HashMap<>();
    private final Map<Integer, Rule> allRules = new HashMap<>();

    @Autowired
    public CrudControl(AccidentService accidentService, AccidentTypeService accidentTypeService, RuleService ruleService) {
        this.accidentService = accidentService;
        this.accidentTypeService = accidentTypeService;
        this.ruleService = ruleService;
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

        AccidentType accidentType = accidentTypeService.findById(accidentTypeId);
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

        accidentService.save(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String edit(@RequestParam int id, Model model) {
        Accident accident = accidentService.findById(id);
        isEmptyTypesAndRules();
        model.addAttribute("accidentsType", allTypes.values());
        model.addAttribute("rules", allRules.values());
        model.addAttribute("accident", accident);
        return "/update";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id) {
        accidentService.deleteById(id);
        return "redirect:/";
    }

    private void isEmptyTypesAndRules() {
        if (allTypes.isEmpty() && allRules.isEmpty()) {
            accidentTypeService.findAll().forEach(a -> allTypes.put(a.getId(), a));
            ruleService.findAll().forEach(r -> allRules.put(r.getId(), r));
        }
    }
}
