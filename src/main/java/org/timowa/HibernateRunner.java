package org.timowa;

import lombok.extern.slf4j.Slf4j;
import org.timowa.entity.*;
import org.timowa.util.HibernateUtil;

import java.time.LocalDate;

@Slf4j
public class HibernateRunner {
    public static void main(String[] args) {
        Company company = Company.builder()
                .name("Yandex")
                .build();
        User user = User.builder()
                .username("timowa1@gmail.com")
                .personalInfo(PersonalInfo.builder()
                        .firstname("Marya")
                        .lastname("Babel'")
                        .birthDate(
                                new Birthday(LocalDate.of(2079, 3, 27)))
                        .build())
                .role(Role.ADMIN)
                .company(company)
                .build();

        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            var user1 = session.get(User.class, 2);
            user.setCompany(user1.getCompany());
            session.saveOrUpdate(user);

            session.getTransaction().commit();
        }
    }
}
