package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;

@Repository
public class AccidentJdbcTemplate {

    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
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
        return jdbc.query("select * from job4j_cars_accident.public.accident",
                (result, row) -> {
                    Accident accident = new Accident(
                            result.getString("name"),
                            result.getString("text"),
                            result.getString("address"));
                    accident.setId(result.getInt("id"));
                    return accident;
                });
    }

    public Accident getAccidentId(int id) {
        return jdbc.queryForObject("select * from job4j_cars_accident.public.accident where id = ?",
                (result, row) -> {
                    Accident accident = new Accident(
                            result.getString("name"),
                            result.getString("text"),
                            result.getString("address"));
                    accident.setId(result.getInt("id"));
                    return accident;
                }, id);
    }

    public Collection<AccidentType> getAllAccidentType() {
        return jdbc.query("select * from job4j_cars_accident.public.accident_type",
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
        return jdbc.query("select * from job4j_cars_accident.public.rule",
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