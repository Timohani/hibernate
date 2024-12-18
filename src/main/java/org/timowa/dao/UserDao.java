package org.timowa.dao;

import org.hibernate.Session;
import org.timowa.entity.PersonalInfo_;
import org.timowa.entity.User;
import org.timowa.entity.User_;

import java.util.List;

public class UserDao {
    private static final UserDao INSTANCE = new UserDao();

    public List<User> findAll(Session session) {
        var cb = session.getCriteriaBuilder();
        var criteria = cb.createQuery(User.class);
        var user = criteria.from(User.class);

        criteria.select(user);

        return session.createQuery(criteria).list();
    }

    public List<User> findAllByFirstname(Session session, String firstname) {
        var cb = session.getCriteriaBuilder();
        var criteria = cb.createQuery(User.class);
        var user = criteria.from(User.class);

        criteria.select(user).where(cb.equal(user.get(User_.personalInfo).get(PersonalInfo_.firstname), firstname));

        return session.createQuery(criteria).list();
    }


    public static UserDao getInstance() {
        return INSTANCE;
    }
}
