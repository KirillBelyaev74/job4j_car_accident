package ru.job4j.accident.repository.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.function.Function;

public class ActionHibernate {

    private final Logger logger = Logger.getLogger(ActionHibernate.class);

    protected <T> T action(Function<Session, T> action, SessionFactory sessionFactory) {
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
}
