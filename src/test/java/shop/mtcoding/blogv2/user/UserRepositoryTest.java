package shop.mtcoding.blogv2.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

// JpaRepository는 @Import 없어도 IoC에 등록된다.
// JpaRepository가 자체 findAll(), delete 메소드를 자체적으로 내장하고 있음.
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager em;

    @BeforeEach // 테스트 메소드 시작 전 마다 실행
    public void setUp() {
        User user = User.builder()
                .username("ssar")
                .password("1234")
                .email("ssar@nate.com")
                .build();
        userRepository.save(user);
        em.clear(); // 영속성 컨텍스트에는 없고, DB에는 없다
    }   // rollback 안 됨 -> 트랜잭션 전파 -> 1차 캐싱 -> PC 비워줘야 함 -> em.clear(); 반드시 필요

    @Test
    public void save_test() {
        // given

        // when

        // then
    }

    @Test
    public void findAll_test() {
        // given

        // when
        userRepository.findAll();

        // then
    }

    @Test
    public void login_test() {
        // given
        String username = "ssar";
        String password = "1234";

        // when
        User userPS = userRepository.login(username, password);
        if (userPS == null) {
            System.out.println("아이디 혹은 패스워드가 틀렸습니다.");
        } else {
            System.out.println("로그인 성공");
        }
        // 컨트롤러: 요청 처리
        // 서비스: 비즈니스 로직
        // 레포지토리: 데이터 처리

        // then
    }
}
