package mapping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Team team = new Team();
//            team.setName("teamB");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("member3");
//            member.setTeam(team);
//
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
//
//            Member findMember = em.find(Member.class, member.getId());
//
//            // team은 프록시 상태
//            System.out.println("Team 클래스 : " + findMember.getTeam().getClass());
//
//            //쿼리 나가는 지점
//            System.out.println("Team 이름 : " + findMember.getTeam().getName());

            List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();


            tx.commit();
        }catch (Exception e) {
            tx.rollback();
        } finally{
            em.close();
        }

        emf.close();
    }
}
