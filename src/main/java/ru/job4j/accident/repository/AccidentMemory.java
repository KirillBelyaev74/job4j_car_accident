package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccidentMemory {

    private final Map<Integer, Accident> accidents = new HashMap<>();
    private int index;

    public Accident add(Accident accident) {
        accident.setId(index++);
        accidents.putIfAbsent(accident.getId(), accident);
        return accident;
    }

    public List<Accident> getAll() {
        return (List<Accident>) accidents.values();
    }
}
