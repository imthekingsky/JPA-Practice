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

            Member member = em.find(Member.class, 150L);
            member.setName("ZZZZ");

//            JPA를 통해서 엔티티를를 가져오면 관리를 한다 그리고 트랜잭션을 커밋 하기 직전에 변경이(스냅샷과 비교) 되었으면 업데이트 커리를 날리고 커밋을 한다.
//            em.persist(member); 따라서 persist를 할 필요가 없다

            // 트랜잭션을 커밋하는 시점에 DB에 쿼리가 날라간다
            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
