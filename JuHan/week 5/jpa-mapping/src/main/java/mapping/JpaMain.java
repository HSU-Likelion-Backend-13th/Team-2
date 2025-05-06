package mapping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("Team 1");
            em.persist(team);

            Member member = new Member();
            member.setUsername("Member1");
            // 위에 있는 팀을 받아줌
            member.setTeam(team);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("Member2");
            member2.setTeam(team);
            em.persist(member2);

            // 1차 캐시에 있는 데이터를 바로 DB로 보냄
            em.flush();
            // 남아있는 1차 캐시 데이터 정리
            em.clear();

            Member findMember = em.find(Member.class, member.getId());

            List<Member> members = findMember.getTeam().getMembers();

            for (Member m : members) {
                System.out.println("멤버 = " + m.getUsername());
                
            }

            tx.commit();
        }catch (Exception e) {
            tx.rollback();
        } finally{
            em.close();
        }

        emf.close();
    }
}
