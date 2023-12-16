package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //persistence.xml 파일에서 설정했던 persistence-unit name="hello""이름(hello) 적어준다
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 데이터를 변경하는 모든 작업은 트랜잭션 안에서 작업해야한다.
        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("hello");
            member.setHomeAddress(new Address("city", "street", "zipcode"));
            member.setWorkPeriod(new Period());

            em.persist(member);

            tx.commit();
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();

    }
}
