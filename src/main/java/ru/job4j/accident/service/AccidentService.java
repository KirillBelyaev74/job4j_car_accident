package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.crud.AccidentRepository;

import java.util.List;

@Service
public class AccidentService {

    private final AccidentRepository repository;

    public AccidentService(AccidentRepository repository) {
        this.repository = repository;
    }

    public Accident save(Accident accident) {
        return repository.save(accident);
    }

    public List<Accident> findAll() {
        return repository.findAll();
    }

    public Accident findById(int id) {
        return repository.findById(id);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
