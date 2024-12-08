package org.timowa;

import lombok.extern.slf4j.Slf4j;
import org.timowa.entity.Birthday;
import org.timowa.entity.PersonalInfo;
import org.timowa.entity.Role;
import org.timowa.entity.User;
import org.timowa.util.HibernateUtil;

import java.time.LocalDate;

@Slf4j
public class HibernateRunner {
    public static void main(String[] args) {
        User user = User.builder()
                .username("timowa@gmail.com")
                .personalInfo(PersonalInfo.builder()
                        .firstname("Marya")
                        .lastname("Babel'")
                        .birthDate(
                                new Birthday(LocalDate.of(2079, 3, 27)))
                        .build())
                .role(Role.ADMIN)
                .build();
        log.info("User object state: {}", user);

        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
        }
    }
}
