package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.crud.AccidentTypeRepository;

import java.util.List;

@Service
public class AccidentTypeService {

    private final AccidentTypeRepository repository;

    public AccidentTypeService(AccidentTypeRepository repository) {
        this.repository = repository;
    }

    public List<AccidentType> findAll() {
        return repository.findAll();
    }

    public AccidentType findById(int id) {
        return repository.findById(id);
    }
}
