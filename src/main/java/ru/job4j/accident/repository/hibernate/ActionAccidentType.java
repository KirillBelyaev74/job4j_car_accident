package ru.job4j.accident.repository.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;

@Repository
public class ActionAccidentType extends ActionHibernate {

    private final SessionFactory sessionFactory;

    @Autowired
    public ActionAccidentType(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Collection<AccidentType> findAll() {
        return action(session -> session.createQuery("from AccidentType order by id").list(),
                sessionFactory);
    }

    public AccidentType findById(int id) {
        return action(session -> {
            Query<AccidentType> query = session.createQuery(
                    "from AccidentType where id =: id");
            query.setParameter("id", id);
            return query.uniqueResult();
        }, sessionFactory);
    }
}
