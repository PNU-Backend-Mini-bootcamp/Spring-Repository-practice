package shop.mtcoding.blogv2.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@RequiredArgsConstructor            // 생성자에 final 자동 삽입
@Repository                         // 컴포넌트 스캔 가능 -> 싱글톤으로 동작
public class UserQueryRepository {
    private final EntityManager em; // 영속성 객체 관리 -> DI

    public User save(User user) {   // user: 비영속 객체
        em.persist(user);           // user: 영속화(자동으로 SQL 쿼리가 DB에 전송) (빵에 커피 찍어 먹기 -> 다른 곳에 가져다 놓아도 커피가 묻어 있다!)
        return user;                // user: 영속화된 객체 리턴
    }

    // select * from user_tb where id = :id
    // 일차 캐시: 내가 찾으려는 객체(데이터)를 PC에서 찾음
    public User findById(int id) {
        return em.find(User.class, id);
        // Persistence Context에서 우선 찾고 없으면 DB에 select 쿼리를 날림. 있으면 바로.

        // R -> (find) -> PC -> (select) -> DB
        // R <- (영속화 객체) <- PC <- (result set) <- DB
        /*
        public abstract <T> T find(
            Class<T> entityClass,
            Object primaryKey
        )
        */
    }

    public void deleteById(int id) {
        User entity = em.find(User.class, id);  // 있으면 일차 캐싱, 없으면 조회
        em.remove(entity);
        // delete 쿼리 발동 X
        // remove 시 트랜잭션 종료 시에 delete query flush 한다.
    }

    public List<User> findAll() {
        Query query =
                em.createQuery("select u from User u order by u.id desc"); // -> 리턴 타입이 컬렉션이 되어야 함
        List<User> targetUsers = query.getResultList();
        return targetUsers;
        // JPQL(Java Persistence Query Language)
        // 엔티티 객체를 대상으로 쿼리, 객체 지향 쿼리 언어
        // SQL과 유사하지만 동일하지 않음
        // SQL이 달라지면 문법이 미세하게 달라짐 -> 마이그레이션(migration) 필요
    }
}
