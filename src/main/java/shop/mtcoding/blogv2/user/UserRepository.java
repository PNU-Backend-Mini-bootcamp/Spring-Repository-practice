package shop.mtcoding.blogv2.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// UserQueryRepository 작성 및 테스트
// JpaRepository를 상속하면 컴포넌트 스캔이 자동으로 된다. -> @Repository 붙일 필요 X
// DI 실행할 때 UserRepository 타입으로 컴포넌트 스캔을 수행한다.
// Plain Java에서는 interface를 new 하지 못하지만 JpaRepository를 상속하면 자동으로 메모리에 생성됨
public interface UserRepository extends JpaRepository<User, Integer> {  // <관리하는 클래스, primary key 타입>

    // 복잡한 통계 쿼리 혹은 동적 쿼리를 작성할 때만 구현
    // select * from user_tb where username="ssar" and password="1234"
    @Query("select u from User u where u.username=:username and u.password=:password")
    User login(@Param("username") String username,
               @Param("password") String password);
}
