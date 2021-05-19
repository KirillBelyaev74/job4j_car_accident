package ru.job4j.accident.repository.crud;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.accident.model.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, Integer> {

    @Modifying
    @Query("from Authority where authority =: authority")
    Authority findByAuthority(@Param("authority") String authority);
}
