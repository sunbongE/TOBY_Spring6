package tobyspring.hellospring.payment;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.hellospring.DataConfig;
import tobyspring.hellospring.order.Order;

import java.math.BigDecimal;

public class DataClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        EntityManagerFactory emf = beanFactory.getBean(EntityManagerFactory.class);

        // em
        EntityManager em = emf.createEntityManager();
        // transaction
        em.getTransaction().begin();

        // em.persist
        Order order = new Order("100", BigDecimal.TEN);
        em.persist(order);// db에 넣기

        System.out.println(order);

        em.getTransaction().commit();
        em.close();
    }
}
