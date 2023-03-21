package com.yarikonen.web4.Repositories;

import com.yarikonen.web4.Data.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    UserRepository tested;
    @Test
    void canUserByLogin(){
        User user = new User("aboba", "aboba");
        tested.save(user);

        assertThat(user).isEqualTo(tested.findUserByLogin("aboba"));

    }
    @Test
    void cantFindUserByLogin(){
        assertThat(tested.findUserByLogin("aboba")).isNull();
    }
    @Test
    void canExistByLogin(){
        User user = new User("aboba", "aboba");
        tested.save(user);

        assertThat(tested.existsByLogin("aboba"));

    }
    @Test
    void notExistUserByLogin(){
        assertThat(!tested.existsByLogin("aboba"));
    }

}