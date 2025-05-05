package mapping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member("은민", "성남시", "한성대", "공학관");
            em.persist(member);

            Item item = new Item("왕감자", 4, 5);
            em.persist(item);

            OrderItem orderItem = new OrderItem();
            orderItem.setCount(3);
            orderItem.setOrderPrice(3*item.getPrice());
            orderItem.setItem(item);
            em.persist(orderItem);

            Order order = new Order();
            order.setMember(member);
            order.setOrderDate(LocalDateTime.now());
            order.setOrderStatus(OrderStatus.SUCCESS);
            order.addOrderItem(orderItem);
            em.persist(order);



            tx.commit();
        }catch (Exception e) {
            tx.rollback();
        } finally{
            em.close();
        }

        emf.close();
    }
}
