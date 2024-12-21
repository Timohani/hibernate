package org.timowa.dao;

import org.timowa.entity.Payment;

import javax.persistence.EntityManager;
import java.util.List;

public class PaymentRepository extends BaseRepository<Long, Payment> {

    public PaymentRepository(EntityManager entityManager) {
        super(Payment.class, entityManager);
    }

    public List<Payment> findAllByReceiverId(Long receiverId) {
        return getEntityManager()
                .createQuery("select p from Payment p where p.receiver.id = :receiverId", Payment.class)
                .setParameter("receiverId", receiverId)
                .getResultList();
    }
}
