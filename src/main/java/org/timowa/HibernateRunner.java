package org.timowa;

import lombok.extern.slf4j.Slf4j;
import org.timowa.dao.PaymentRepository;
import org.timowa.util.HibernateUtil;

@Slf4j
public class HibernateRunner {
    public static void main(String[] args) {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            var paymentRepository = new PaymentRepository(sessionFactory);
            paymentRepository.findById(4L).ifPresent(System.out::println);

            session.getTransaction().commit();
        }
    }
}
