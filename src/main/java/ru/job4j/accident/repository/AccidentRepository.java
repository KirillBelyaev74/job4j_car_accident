package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.accident.model.Accident;

import java.util.List;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @Modifying
    @Query("select distinct a from Accident as a left join fetch a.accidentType left join fetch a.rules order by a.id")
    List<Accident> findAll();

    @Query("select distinct a from Accident as a left join fetch a.accidentType left join fetch a.rules where a.id = :id")
    Accident findById(@Param("id") int id);
}