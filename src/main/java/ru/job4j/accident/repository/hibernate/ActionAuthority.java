package ru.job4j.accident.repository.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Authority;

@Repository
public class ActionAuthority extends ActionHibernate {

    private final SessionFactory sessionFactory;

    @Autowired
    public ActionAuthority(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Authority findByAuthority(String authority) {
        return action(session -> {
            Query<Authority> query = session.createQuery("from Authority where authority =: authority");
            query.setParameter("authority", authority);
            return query.uniqueResult();
        }, sessionFactory);
    }
}
