package org.timowa.dao;


import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.timowa.util.HibernateUtil;

class UserDaoTest {
    @Test
    public void findAllTest() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        System.out.println(UserDao.getInstance().findAll(session));

        session.getTransaction().commit();
    }

    @Test
    public void findAllByFirstnameTest() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        final String firstname = "John";

        System.out.println(UserDao.getInstance().findAllByFirstname(session, firstname));

        session.getTransaction().commit();
    }

    @Test
    public void findAllByCompanyNameTest() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        final String companyName = "Google";

        System.out.println(UserDao.getInstance().findAllByCompanyName(session, companyName));

        session.getTransaction().commit();
    }

    @Test
    public void findAllPaymentsByCompanyNameTest() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        final String companyName = "Google";

        System.out.println(UserDao.getInstance().findAllPaymentsByCompanyName(session, companyName));

        session.getTransaction().commit();
    }
}