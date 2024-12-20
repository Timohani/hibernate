package org.timowa;

import lombok.extern.slf4j.Slf4j;
import org.timowa.entity.*;
import org.timowa.util.HibernateUtil;

import javax.persistence.LockModeType;

@Slf4j
public class HibernateRunner {
    public static void main(String[] args) {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession();
             var session1 = sessionFactory.openSession()) {
            session.beginTransaction();
            session1.beginTransaction();

            var payment = session.find(Payment.class, 3L, LockModeType.OPTIMISTIC);
            payment.setAmount(payment.getAmount() + 10);

            var theSamePayment = session.find(Payment.class, 3L, LockModeType.OPTIMISTIC);
            theSamePayment.setAmount(theSamePayment.getAmount() + 20);

            session.getTransaction().commit();
            session1.getTransaction().commit();
        }
    }
}
