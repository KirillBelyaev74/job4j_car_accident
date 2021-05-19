package ru.job4j.accident.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.User;

@Repository
public class ActionUser extends ActionHibernate {

    private final SessionFactory sessionFactory;

    @Autowired
    public ActionUser(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User save(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
        return user;
    }
}
