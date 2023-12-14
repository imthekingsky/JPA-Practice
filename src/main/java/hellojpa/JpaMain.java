package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //persistence.xml 파일에서 설정했던 persistence-unit name="hello""이름(hello) 적어준다
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 데이터를 변경하는 모든 작업은 트랜잭션 안에서 작업해야한다.
        tx.begin();

        try {

            //팀 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);
            
            //회원 저장
            Member member = new Member();
            member.setUsername("member1");
//            member.changeTeam(team); // 연관관계의 주인에 값 설정 + 연관관계 편의 메서드
            em.persist(member);

            team.addMember(member); // 연관관계의 주인에 값 설정 + 연관관계 편의 메서드

            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

            System.out.println("=============== ");
            for (Member m : members) {
                System.out.println("m.getUsername() = " + m.getUsername()); // 이때 Member select 쿼리 날라가는듯
            }
            System.out.println("=============== ");


            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
