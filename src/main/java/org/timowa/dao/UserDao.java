package org.timowa.dao;

import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;
import org.timowa.entity.Payment;
import org.timowa.entity.User;

import java.util.List;

import static org.timowa.entity.QCompany.company;
import static org.timowa.entity.QPayment.payment;
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

    public List<User> findAllByCompanyName(Session session, String companyName) {
        return new JPAQuery<User>(session).select(user).from(company)
                .join(company.users, user)
                .where(company.name.eq(companyName))
                .fetch();
    }

    public List<Payment> findAllPaymentsByCompanyName(Session session, String companyName) {
        return new JPAQuery<User>(session).select(payment).from(company)
                .join(company.users, user)
                .join(user.payments, payment)
                .where(company.name.eq(companyName))
                .orderBy(user.personalInfo().firstname.asc(), payment.amount.asc())
                .fetch();
    }


    public static UserDao getInstance() {
        return INSTANCE;
    }
}
