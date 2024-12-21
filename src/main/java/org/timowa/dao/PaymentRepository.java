package org.timowa.dao;

import org.hibernate.SessionFactory;
import org.timowa.entity.Payment;

public class PaymentRepository extends BaseRepository<Long, Payment> {

    public PaymentRepository(SessionFactory sessionFactory) {
        super(Payment.class, sessionFactory);
    }
}
