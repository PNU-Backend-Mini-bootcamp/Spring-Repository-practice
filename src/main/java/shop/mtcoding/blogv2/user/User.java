package shop.mtcoding.blogv2.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor // Select 시에 JPA가 기본 생성자를 호출해서 자바 객체를 만들어준다.
@Getter
@Table(name = "user_tb")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 20, unique = true)
    private String username;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false, length = 100)
    private String email;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public User(Integer id, String username, String password, String email, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
    }

    // 꼭 필요한 setter만 의미 있는 이름으로 만든다.
    public void updatePassword(String password){
        this.password = password;
    }
}
