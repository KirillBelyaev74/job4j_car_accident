package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMemory {

    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final Map<Integer, AccidentType> accidentsType = new HashMap<>();
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

    public Accident getAccidentId(int id) {
        return accidents.get(id);
    }

    public Collection<AccidentType> getAllAccidentType() {
        AccidentType one = new AccidentType("Машина и велосипед");
        one.setId(1);
        AccidentType two = new AccidentType("Машина и пешеход");
        two.setId(2);
        AccidentType three = new AccidentType("Две машины");
        three.setId(3);
        accidentsType.put(one.getId(), one);
        accidentsType.put(two.getId(), two);
        accidentsType.put(three.getId(), three);
        return accidentsType.values();
    }

    public AccidentType getAccidentTypeId(int id) {
        return accidentsType.get(id);
    }
}
