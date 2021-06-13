package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.crud.RuleRepository;

import java.util.List;

@Service
public class RuleService {

    private final RuleRepository repository;

    public RuleService(RuleRepository repository) {
        this.repository = repository;
    }

    public List<Rule> findAll() {
        return repository.findAll();
    }
}
