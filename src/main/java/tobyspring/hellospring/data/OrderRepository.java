package tobyspring.hellospring.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import tobyspring.hellospring.order.Order;

import java.math.BigDecimal;

public class OrderRepository {

    private final EntityManagerFactory emf;

    public OrderRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(Order order) {
        // em
        EntityManager em = emf.createEntityManager();
        // transaction
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            // em.persist
            em.persist(order);// db에 넣기
            em.flush();

            transaction.commit();
        }catch (RuntimeException e){
            if(transaction.isActive()) transaction.rollback();
            throw e;
        }finally {
            if(em.isOpen()) em.close();
        }

    }
}
