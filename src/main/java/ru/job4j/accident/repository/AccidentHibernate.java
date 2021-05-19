package ru.job4j.accident.repository;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Repository
public class AccidentHibernate {

    private final Logger logger = Logger.getLogger(AccidentHibernate.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public AccidentHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private <T> T action(Function<Session, T> action) {
        T t = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            t = action.apply(session);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Method has arguments action " + action + "Сonnection error. Mistake save :", e);
        }
        return t;
    }

    public Accident addOrReplace(Accident accident) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            if (accident.getId() == 0) {
                session.save(accident);
            } else {
                session.update(accident);
            }
            session.update(accident);
            session.getTransaction().commit();
        }
        return accident;
    }

    public List<Accident> getAllAccident() {
        return action(session -> {
            Query<Accident> query = session.createQuery(
                    "select distinct a from Accident as a "
                            + "left join fetch a.accidentType "
                            + "left join fetch a.rules "
                            + "order by a.id");
            return query.getResultList();
        });
    }

    public Accident getAccidentId(int id) {
        return action(session -> {
            Query<Accident> query = session.createQuery(
                    "select distinct a from Accident as a "
                            + "left join fetch a.accidentType "
                            + "left join fetch a.rules "
                            + "where a.id =: id");
            query.setParameter("id", id);
            return query.uniqueResult();
        });
    }

    public void deleteAccident(int id) {
        action(session ->
                session.createQuery("delete from Accident where id =: id")
                        .setParameter("id", id).executeUpdate()
        );
    }

    public Collection<AccidentType> getAllAccidentType() {
        return action(session -> session.createQuery("from AccidentType order by id").list());
    }

    public AccidentType getAccidentTypeId(int id) {
        return action(session -> {
            Query<AccidentType> query = session.createQuery(
                    "from AccidentType where id =: id");
            query.setParameter("id", id);
            return query.uniqueResult();
        });
    }

    public Collection<Rule> getAllRules() {
        return action(session -> session.createQuery("from Rule order by id").list());
    }

    public Rule getRulesId(int id) {
        return action(session -> {
            Query<Rule> query = session.createQuery("from Rule where id =: id");
            query.setParameter("id", id);
            return query.uniqueResult();
        });
    }
}
