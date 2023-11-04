package shop.mtcoding.blogv2;

import shop.mtcoding.blogv2.user.User;

public class DummyObject {
    protected User newUser(String username) {
        return User.builder()
                .username("ssar")
                .password("1234")
                .email("ssar@nate.com")
                .build();
    }
}
