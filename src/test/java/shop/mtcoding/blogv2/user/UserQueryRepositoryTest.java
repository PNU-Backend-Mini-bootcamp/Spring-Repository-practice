package shop.mtcoding.blogv2.user;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blogv2.DummyObject;

import javax.persistence.EntityManager;
import java.util.List;

@Import(UserQueryRepository.class)      // 얘도 메모리에 띄울 수 있음
@DataJpaTest                            // JPA 관련된 객체만 메모리에 띄움
public class UserQueryRepositoryTest extends DummyObject {
    @Autowired                          // 테스트 코드에서는 생성자가 메모리에 뜨지 않기 때문에 DI를 하기 위해 @Autowired
    private UserQueryRepository userQueryRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void save_test() {           // _ 는 테스트 코드 컨벤션, 메소드 위치와 이름 + _test, 매개 변수 작성 못함
        // given: 매개 변수로 받아야 할 값들을 직접 작성해야 함
        User user = User.builder()
                .username("ssar")
                .password("1234")
                .email("ssar@nate.com")
                .build();

        // when: 메소드의 핵심 로직을 실행해야 함
        userQueryRepository.save(user);

        // then: 실행 결과를 검증해야 함(행위 검증 기법, 상태 검증 기법, 눈으로 보기 ㅋㅋ(콘솔 창))
    }

    // @Order(1) // 테스트 코드 동작 순서 -> 테스트 코드 간 유기적 결합? -> 테스트 격리되지 않았음 -> 실패한 코드
    @Test
    public void findById_test() {
        // given
        User user = newUser("ssar");    // 비영속 객체
        userQueryRepository.save(user);
        em.clear();     // 영속성 컨텍스트를 비워버린다 -> select 쿼리 발동

        int id = 1;

        // when
        userQueryRepository.findById(id);

        // then
    }   // DB: roll-back 됨(auto_increment 초기화 안 됨)
        // PC는 유지됨 -> 하나의 테스트 코드 메소드는 완전해야 한다.

    /*
    테스트 커버리지: 본 코드에 대해서 얼마나 테스팅을 했는지 퍼센테이지로 나타낸 값.
                 보통 60% 이상 충족 시 준수한 테스트.

    테스트가 격리 되야 하는 이유
    - 책임 분할: 하나의 메소드는 하나의 책임
    - ex) 커피 제조
        - 커피콩 -> 1cm로 가는 기계 -> 1cm 가루만 받아 커피 제조
        - 합쳐서 테스트 하는 경우 책임 소재 파악이 힘들다.
          -> 커피 제조가 안되는 경우 가루 제조 기계의 잘못? 제조 기계의 잘못?
          -> 단위 테스트를 통해 책임을 확인해야 함
    - 가장 좋은 테스트는 고객이 참여하는 테스트 -> ex) 게임의 베타 테스트
    */

    @Test
    public void deleteById_test() {
        // given
        User user = newUser("ssar");
        int id = 1;
        userQueryRepository.save(user);
        em.clear();

        // when
        userQueryRepository.deleteById(id);
        em.flush(); // 영속성 컨텍스트 내용을 DB에 그대로 반영(commit)
        /**
         * 트랜잭션 개념
         * 원자성(Atomicity): 트랜잭션 내의 모든 연산은 하나의 단위로 취급되어야 합니다. 이는 모든 연산이 성공적으로 실행되거나, 하나라도 실패하면 전체 트랜잭션이 롤백되어야 함을 의미합니다.
         * 일관성(Consistency): 트랜잭션이 실행되기 전과 후에 데이터베이스의 무결성 제약 조건이 지켜져야 합니다. 즉, 트랜잭션은 항상 일관된 데이터베이스 상태를 유지해야 합니다.
         * 독립성(Isolation): 동시에 실행되는 여러 트랜잭션이 서로 영향을 주지 않고 독립적으로 수행되어야 합니다. 격리 수준에 따라 다양한 동시성 문제를 처리할 수 있지만, 완전한 격리는 성능 저하를 초래할 수 있습니다.
         * 지속성(Durability): 트랜잭션이 일단 커밋되면, 그 결과는 영구적으로 데이터베이스에 반영되어야 하며, 시스템에 장애가 발생하더라도 보존되어야 합니다.
         */

        // then
    }   // 트랜잭션 종료 시 commit 되어 delete 동작

    @Test
    public void findAll_test() {
        // given
        User ssar = User.builder()
                .username("ssar")
                .password("1234")
                .email("ssar@nate.com")
                .build();
        User cos = User.builder()
                .username("cos")
                .password("1234")
                .email("cos@nate.com")
                .build();
        User love = User.builder()
                .username("love")
                .password("1234")
                .email("love@nate.com")
                .build();
        userQueryRepository.save(ssar);
        userQueryRepository.save(cos);
        userQueryRepository.save(love);

        // when
        List<User> targetUsers = userQueryRepository.findAll();
        for (User user: targetUsers) {
            System.out.println("유저 네임: " + user.getUsername());
        }
        // then
    }
}
