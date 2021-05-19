package ru.job4j.accident.repository.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.Collection;

@Repository
public class ActionRule extends ActionHibernate {

    private final SessionFactory sessionFactory;

    @Autowired
    public ActionRule(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Collection<Rule> findAll() {
        return action(session -> session.createQuery("from Rule order by id").list(),
                sessionFactory);
    }

    public Rule findAllById(int id) {
        return action(session -> {
            Query<Rule> query = session.createQuery("from Rule where id =: id");
            query.setParameter("id", id);
            return query.uniqueResult();
        }, sessionFactory);
    }
}
