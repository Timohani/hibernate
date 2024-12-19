package org.timowa;

import lombok.extern.slf4j.Slf4j;
import org.timowa.entity.*;
import org.timowa.util.HibernateUtil;

@Slf4j
public class HibernateRunner {
    public static void main(String[] args) {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.enableFetchProfile("withCompany");
            var user = session.get(User.class, 2L);
            System.out.println(user.getCompany().getName());

            session.getTransaction().commit();
        }
    }
}
