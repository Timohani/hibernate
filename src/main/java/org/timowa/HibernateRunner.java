package org.timowa;

import org.hibernate.cfg.Configuration;
import org.timowa.conventer.BirthdayConverter;
import org.timowa.entity.Birthday;
import org.timowa.entity.Role;
import org.timowa.entity.User;

import java.time.LocalDate;

public class HibernateRunner {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAttributeConverter(new BirthdayConverter(), true);

        try (var sessionFactory = configuration.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = User.builder()
                    .username("timowa@gmail.com")
                    .firstname("John")
                    .lastname("Harris")
                    .birthDate(
                            new Birthday(LocalDate.of(1488, 3, 27)))
                    .role(Role.ADMIN)
                    .build();

            User user1 = session.get(User.class, "timowa12@gmail.com");
            System.out.println(user1.getBirthDate().getAge());

            session.getTransaction().commit();
        }
    }
}
