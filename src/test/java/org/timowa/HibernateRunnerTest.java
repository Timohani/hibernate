package org.timowa;

import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.timowa.entity.Company;
import org.timowa.util.HibernateUtil;

class HibernateRunnerTest {
    @Test
    public void checkOneToMany() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        var company = session.get(Company.class, 2);
        System.out.println(company.getUsers());

        session.getTransaction().commit();
    }
}