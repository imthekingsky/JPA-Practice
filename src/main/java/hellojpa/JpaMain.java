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
            member.setTeam(team); //단방향 연관관계 설정, 참조 저장
            em.persist(member);

            //조회
            Member findMember = em.find(Member.class, member.getId());
            
            //참조를 사용해서 연관관계 조회
            Team findTeam = findMember.getTeam();
            System.out.println("findTeam.getName() = " + findTeam.getName());

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
