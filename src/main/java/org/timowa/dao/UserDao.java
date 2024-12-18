package org.timowa.dao;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;
import org.timowa.entity.User;

import java.util.List;

import static org.timowa.entity.QUser.user;

public class UserDao {
    private static final UserDao INSTANCE = new UserDao();

    public List<User> findAll(Session session) {
        return new JPAQuery<User>(session).select(user).from(user).fetch();
    }

    public List<User> findAllByFirstname(Session session, String firstname) {
        return new JPAQuery<User>(session).select(user).from(user)
                .where(user.personalInfo().firstname.eq(firstname)).fetch();
    }

    public List<User> findLimitedUsersOrderedByBirthday(Session session, int limit) {
        return new JPAQuery<User>(session).select(user).from(user)
                .orderBy(new OrderSpecifier(Order.ASC, user.personalInfo().birthDate))
                .limit(limit).fetch();
    }


    public static UserDao getInstance() {
        return INSTANCE;
    }
}
