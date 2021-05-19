package ru.job4j.accident.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.List;

@Repository
public class ActionAccident extends ActionHibernate {

    private final SessionFactory sessionFactory;

    @Autowired
    public ActionAccident(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Accident save(Accident accident) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            if (accident.getId() == 0) {
                session.save(accident);
            } else {
                session.update(accident);
            }
            session.getTransaction().commit();
        }
        return accident;
    }

    public List<Accident> findAll() {
        return action(session -> {
            Query<Accident> query = session.createQuery(
                    "select distinct a from Accident as a "
                            + "left join fetch a.accidentType "
                            + "left join fetch a.rules "
                            + "order by a.id");
            return query.getResultList();
        }, sessionFactory);
    }

    public Accident findById(int id) {
        return action(session -> {
            Query<Accident> query = session.createQuery(
                    "select distinct a from Accident as a "
                            + "left join fetch a.accidentType "
                            + "left join fetch a.rules "
                            + "where a.id =: id");
            query.setParameter("id", id);
            return query.uniqueResult();
        }, sessionFactory);
    }

    public void deleteById(int id) {
        action(session ->
                session.createQuery("delete from Accident where id =: id")
                        .setParameter("id", id).executeUpdate(), sessionFactory);
    }
}
