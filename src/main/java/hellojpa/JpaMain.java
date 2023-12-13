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

            Member member1 =new Member();
            member1.setUsername("A");
            Member member2 =new Member();
            member2.setUsername("B");
            Member member3 =new Member();
            member3.setUsername("C");

            // DB SEQ =  1 | 1
            // DB SEQ = 51 | 2
            // DB SEQ = 51 | 3

            em.persist(member1); // 1, 51
            em.persist(member2); // MEM
            em.persist(member3); // MEM

            System.out.println("member1 = " + member1.getId());
            System.out.println("member2 = " + member2.getId());
            System.out.println("member3 = " + member3.getId());

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
