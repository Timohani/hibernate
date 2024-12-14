package org.timowa;

import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.timowa.entity.*;
import org.timowa.util.HibernateUtil;

import java.time.Instant;

class HibernateRunnerTest {
    @Test
    public void checkHQL() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        final String name = "Timowa";
        final String companyName = "Google";

        var users = session.createNamedQuery("FindUserByNameAndCompany")
                .setParameter("firstname", name)
                .setParameter("company", companyName)
                .list();
        System.out.println(users);

        session.createQuery(
                "update User u set role = 'ADMIN' where u.personalInfo.lastname = 'gre'")
                .executeUpdate();

        session.getTransaction().commit();
    }

    @Test
    public void checkInheritance() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        /*Company company = Company.builder()
                .name("SberBank")
                .build();
        session.save(company);

        Programmer programmer = Programmer.builder()
                .username("tupoyIshak228@jmail.cum")
                .language(Language.PYTHON)
                .company(company)
                .build();
        session.save(programmer);

        Manager manager = Manager.builder()
                .username("megaSigma777@gmail.com")
                .project("amogus")
                .company(company)
                .build();
        session.save(manager);

        session.flush();
        session.clear();

        Programmer programmer1 = session.get(Programmer.class, 1L);
        Manager manager1 = session.get(Manager.class, 2L);
        System.out.println(programmer1);
        System.out.println(manager1);*/

        session.getTransaction().commit();
    }

    @Test
    public void checkH2() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Company company = Company.builder()
                .name("Raxoon")
                .build();
        session.save(company);

        session.getTransaction().commit();
    }

    @Test
    public void checkManyToMany() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Chat chat = session.get(Chat.class, 1L);
        User user = session.get(User.class, 4L);
        UserChat userChat = new UserChat();
        userChat.setCreatedAt(Instant.now());
        userChat.setCreatedBy("Andryha228");

        userChat.setChat(chat);
        userChat.setUser(user);

        session.save(userChat);

        session.getTransaction().commit();
    }

    @Test
    public void checkOneToOne() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = User.builder()
                .username("lidusikfox1@gmail.com")
                .build();
        Profile profile = Profile.builder()
                .language("RU")
                .street("Pushkinskaya 79")
                .build();

        session.save(user);
        profile.setUser(user);
        session.save(profile);

        session.getTransaction().commit();
    }

    @Test
    public void checkOrphanRemoval() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Company company = session.get(Company.class, 2);
        company.getUsers().removeIf(user -> user.getId().equals(3L));

        session.getTransaction().commit();
    }

    @Test
    public void addNewUserAndCompany() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Company company = Company.builder()
                .name("Yandex")
                .build();
        User user = User.builder()
                .username("persik3@gmail.com")
                .build();
        company.addUser(user);

        session.save(company);
        session.getTransaction().commit();
    }

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