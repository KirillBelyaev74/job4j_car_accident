package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMemory {

    private final Map<Integer, Accident> accidents = new HashMap<>();
    private AtomicInteger index = new AtomicInteger(0);

    public Accident addOrReplace(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(index.incrementAndGet());
            accidents.putIfAbsent(accident.getId(), accident);
        } else {
            accidents.replace(accident.getId(), accident);
        }
        return accident;
    }

    public Collection<Accident> getAll() {
        return accidents.values();
    }

    public Accident getId(int id) {
        return accidents.get(id);
    }
}
