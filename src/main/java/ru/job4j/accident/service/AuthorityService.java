package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Authority;
import ru.job4j.accident.repository.crud.AuthorityRepository;

@Service
public class AuthorityService {

    private final AuthorityRepository repository;

    @Autowired
    public AuthorityService(AuthorityRepository repository) {
        this.repository = repository;
    }

    public Authority findByAuthority(String authority) {
        return repository.findByAuthority(authority);
    }
}
