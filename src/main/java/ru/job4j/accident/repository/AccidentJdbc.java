package ru.job4j.accident.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;

@Repository
public class AccidentJdbc {

    private final JdbcTemplate jdbc;

    @Autowired
    public AccidentJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident addOrReplace(Accident accident) {
        if (accident.getId() == 0) {
            jdbc.update("insert into job4j_cars_accident.public.accident (name, text, address, id) values (initcap(?), initcap(?), initcap(?))",
                    accident.getName(), accident.getText(), accident.getAddress());
        } else {
            jdbc.update("update job4j_cars_accident.public.accident "
                            + "set name = initcap(?), text = initcap(?), address = initcap(?) where id = ?",
                    accident.getName(), accident.getText(), accident.getAddress(), accident.getId());
        }
        return accident;
    }

    public List<Accident> getAllAccident() {
        return jdbc.query("select distinct a.id aId, a.name aName, a.text aText, a.address aAddress, t.id tId, t.name tName, r.id rId, r.name rName"
                        + " from job4j_cars_accident.public.accident as a"
                        + " left join job4j_cars_accident.public.accident_type as t on(a.id_accident_type = t.id)"
                        + " left join job4j_cars_accident.public.accident_rule as ar on(ar.id_accident = a.id)"
                        + " left join job4j_cars_accident.public.rule as r on (r.id = ar.id_rule) order by a.id",
                (result, row) -> {
                    List<Rule> rules = new ArrayList<>();
                    AccidentType accidentType = new AccidentType(result.getString("tName"));
                    accidentType.setId(result.getInt("tId"));
                    Rule rule = new Rule(result.getString("rName"));
                    rule.setId(result.getInt("rId"));
                    rules.add(rule);
                    Accident accident = new Accident(
                            result.getString("aName"),
                            result.getString("aText"),
                            result.getString("aAddress"));
                    accident.setId(result.getInt("aId"));
                    accident.setAccidentType(accidentType);
                    accident.setRules(rules);
                    return accident;
                });
    }

    public Accident getAccidentId(int id) {
        return jdbc.queryForObject("select distinct a.id aId, a.name aName, a.text aText, a.address aAddress, t.id tId, t.name tName, r.id rId, r.name rName"
                        + " from job4j_cars_accident.public.accident as a"
                        + " left join job4j_cars_accident.public.accident_type as t on(a.id_accident_type = t.id)"
                        + " left join job4j_cars_accident.public.accident_rule as ar on(ar.id_accident = a.id)"
                        + " left join job4j_cars_accident.public.rule as r on (r.id = ar.id_rule) where a.id = ?",
                (result, row) -> {
                    List<Rule> rules = new ArrayList<>();
                    AccidentType accidentType = new AccidentType(result.getString("tName"));
                    accidentType.setId(result.getInt("tId"));
                    Rule rule = new Rule(result.getString("rName"));
                    rule.setId(result.getInt("rId"));
                    rules.add(rule);
                    Accident accident = new Accident(
                            result.getString("aName"),
                            result.getString("aText"),
                            result.getString("aAddress"));
                    accident.setId(result.getInt("aId"));
                    accident.setAccidentType(accidentType);
                    accident.setRules(rules);
                    return accident;
                }, id);
    }

    public Collection<AccidentType> getAllAccidentType() {
        return jdbc.query("select * from job4j_cars_accident.public.accident_type order by id",
                (result, row) -> {
                    AccidentType accidentType = new AccidentType(result.getString("name"));
                    accidentType.setId(result.getInt("id"));
                    return accidentType;
                });
    }

    public AccidentType getAccidentTypeId(int id) {
        return jdbc.queryForObject("select * from job4j_cars_accident.public.accident_type where id = ?",
                ((result, i) -> {
                    AccidentType accidentType = new AccidentType(result.getString("name"));
                    accidentType.setId(result.getInt("id"));
                    return accidentType;
                }), id);
    }

    public Collection<Rule> getAllRules() {
        return jdbc.query("select * from job4j_cars_accident.public.rule order by id",
                (result, i) -> {
                    Rule rule = new Rule(result.getString("name"));
                    rule.setId(result.getInt("id"));
                    return rule;
                });
    }

    public Rule getRulesId(int id) {
        return jdbc.queryForObject("select * from job4j_cars_accident.public.rule where id = ?",
                (result, i) -> {
                    Rule rule = new Rule(result.getString("name"));
                    rule.setId(result.getInt("id"));
                    return rule;
                }, id);
    }
}